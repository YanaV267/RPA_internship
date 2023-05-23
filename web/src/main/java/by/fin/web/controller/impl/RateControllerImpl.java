package by.fin.web.controller.impl;

import by.fin.processing.dto.BankRateDto;
import by.fin.processing.dto.RateDto;
import by.fin.processing.dto.RateWrapperDto;
import by.fin.processing.exception.BadRequestException;
import by.fin.processing.service.RateService;
import by.fin.web.controller.RateController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static by.fin.processing.exception.ExceptionResponseMessage.SERVER_NOT_RESPONDED;

@RestController
@Validated
@RequiredArgsConstructor
public class RateControllerImpl implements RateController {
    private static final String SERVER_EXCHANGE_RATES_URL =
            "https://api.nbrb.by/exrates/rates/{cur_abbreviation}?periodicity=1&parammode=2";
    private static final String SERVER_EXCHANGE_RATES_URL_BY_DATE = SERVER_EXCHANGE_RATES_URL + "&ondate={date}";
    private final RateService service;
    private final RestTemplate restTemplate;

    @Override
    public List<RateDto> create(RateWrapperDto wrapperDto) {
        service.checkDates(wrapperDto);
        List<BankRateDto> exchangeRates = retrieveDataFromBankServer(wrapperDto);
        return service.add(wrapperDto, exchangeRates);
    }

    @Override
    public List<RateDto> findAll() {
        return service.findAll();
    }

    @Override
    public List<RateDto> findByCurrency(String currencyType) {
        checkCurrencyType(currencyType);
        return service.findByCurrency(currencyType);
    }

    @Override
    public BigDecimal findAverageInMonth(String currencyType, int monthNumber) {
        checkCurrencyType(currencyType);
        return service.findAverageInMonth(currencyType, monthNumber);
    }

    private void checkCurrencyType(String currencyType) {
        restTemplate.getForObject(SERVER_EXCHANGE_RATES_URL, BankRateDto.class, currencyType);
    }

    private List<BankRateDto> retrieveDataFromBankServer(RateWrapperDto wrapper) {
        String currencyType = wrapper.getCurrencyType();
        List<BankRateDto> rates = new LinkedList<>();
        for (LocalDate date = wrapper.getStartDate(); date.isBefore(wrapper.getEndDate().plusDays(1));
             date = date.plusDays(1)) {
            BankRateDto serverRate =
                    restTemplate.getForObject(SERVER_EXCHANGE_RATES_URL_BY_DATE, BankRateDto.class, currencyType, date);
            if (serverRate == null) {
                throw new BadRequestException(SERVER_NOT_RESPONDED, BankRateDto.class);
            }
            rates.add(serverRate);
        }
        return rates;
    }
}
