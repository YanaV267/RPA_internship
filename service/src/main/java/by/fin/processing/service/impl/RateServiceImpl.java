package by.fin.processing.service.impl;

import by.fin.module.repository.RateRepository;
import by.fin.processing.dto.RateDto;
import by.fin.processing.dto.RateWrapperDto;
import by.fin.processing.dto.ServerRateDto;
import by.fin.processing.exception.BadRequestException;
import by.fin.processing.exception.NoDataFoundException;
import by.fin.processing.mapper.impl.RateMapper;
import by.fin.processing.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static by.fin.processing.exception.ExceptionResponseMessage.DATES_INTERVAL;
import static by.fin.processing.exception.ExceptionResponseMessage.DATES_MIX_UP;
import static by.fin.processing.exception.ExceptionResponseMessage.EXCHANGE_RATES;

@Service
@Transactional
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final RateRepository repository;
    private final RateMapper mapper;

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
    public List<RateDto> add(RateWrapperDto wrapperDto, List<ServerRateDto> exchangeRates) {

        return List.of();
    }

    @Override
    public List<RateDto> findAll() {
        List<RateDto> exchangeRates = repository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
        if (exchangeRates.isEmpty()) {
            throw new NoDataFoundException(EXCHANGE_RATES, RateDto.class);
        }
        return exchangeRates;
    }
}
