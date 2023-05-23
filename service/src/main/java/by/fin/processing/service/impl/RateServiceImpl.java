package by.fin.processing.service.impl;

import by.fin.module.entity.Rate;
import by.fin.module.entity.Weekend;
import by.fin.module.repository.RateRepository;
import by.fin.processing.dto.BankRateDto;
import by.fin.processing.dto.RateDto;
import by.fin.processing.dto.RateWrapperDto;
import by.fin.processing.dto.WeekendDto;
import by.fin.processing.exception.BadRequestException;
import by.fin.processing.exception.NoDataFoundException;
import by.fin.processing.mapper.impl.RateMapper;
import by.fin.processing.service.RateService;
import by.fin.processing.service.WeekendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.fin.processing.exception.ExceptionResponseMessage.DATES_INTERVAL;
import static by.fin.processing.exception.ExceptionResponseMessage.DATES_MIX_UP;
import static by.fin.processing.exception.ExceptionResponseMessage.EXCHANGE_RATES;
import static by.fin.processing.exception.ExceptionResponseMessage.RATES_NOT_FOUND_IN_MONTH;

@Service
@Transactional
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final RateRepository repository;
    private final RateMapper mapper;
    private final WeekendsService weekendsService;

    @Override
    public void checkDates(RateWrapperDto wrapperDto) {
        if (wrapperDto.getStartDate().isBefore(LocalDate.of(2022, 12, 1)) ||
                wrapperDto.getEndDate().isAfter(LocalDate.of(2023, 5, 31))) {
            throw new BadRequestException(DATES_INTERVAL, RateWrapperDto.class);
        }
        if (wrapperDto.getStartDate().isAfter(wrapperDto.getEndDate())) {
            throw new BadRequestException(DATES_MIX_UP, RateWrapperDto.class);
        }
    }

    @Override
    public List<RateDto> add(RateWrapperDto wrapperDto, List<BankRateDto> exchangeRates) {
        String currencyType = wrapperDto.getCurrencyType();
        return exchangeRates.stream()
                .filter(r -> {
                    Weekend weekend = weekendsService.findWeekendEntityByDate(r.getDate());
                    Optional<Rate> foundRate = repository.findByWeekendAndCurrencyType(weekend, currencyType);
                    return foundRate.isEmpty();
                }).map(r -> {
                    WeekendDto weekend = weekendsService.findWeekendByDate(r.getDate());
                    RateDto rateDto = RateDto.builder().currencyType(currencyType)
                            .value(r.getRateValue())
                            .weekend(weekend)
                            .build();
                    Rate rate = mapper.mapToEntity(rateDto);
                    repository.save(rate);
                    rateDto.setExchangeRateId(rate.getExchangeRateId());
                    return rateDto;
                }).toList();
    }

    @Override
    public List<RateDto> findAll() {
        List<RateDto> rates = repository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
        if (rates.isEmpty()) {
            throw new NoDataFoundException(EXCHANGE_RATES, RateDto.class);
        }
        return rates;
    }

    @Override
    public List<RateDto> findByCurrency(String currencyType) {
        List<RateDto> rates = repository.findByCurrencyType(currencyType).stream()
                .map(mapper::mapToDto)
                .toList();
        if (rates.isEmpty()) {
            throw new NoDataFoundException(EXCHANGE_RATES, RateDto.class);
        }
        return rates;
    }

    @Override
    public BigDecimal findAverageInMonth(String currencyType, int monthNumber) {
        Optional<BigDecimal> resultAverage = repository.findByCurrencyTypeAndMonthNumber(currencyType, monthNumber);
        if (resultAverage.isEmpty()) {
            throw new BadRequestException(RATES_NOT_FOUND_IN_MONTH, RateDto.class);
        }
        return resultAverage.get();
    }
}
