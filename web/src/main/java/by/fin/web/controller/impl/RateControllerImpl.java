package by.fin.web.controller.impl;

import by.fin.processing.dto.RateDto;
import by.fin.processing.dto.RateWrapperDto;
import by.fin.processing.dto.BankRateDto;
import by.fin.processing.exception.BadRequestException;
import by.fin.processing.service.RateService;
import by.fin.web.controller.RateController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static by.fin.processing.exception.ExceptionResponseMessage.SERVER_NOT_RESPONDED;

@RestController
@Validated
@RequiredArgsConstructor
public class RateControllerImpl implements RateController {
    private static final String SERVER_EXCHANGE_RATES_URL =
            "https://api.nbrb.by/exrates/rates/{cur_abbreviation}?periodicity=1&parammode=2&ondate={date}";
    private final RateService service;

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
    public List<BankRateDto> retrieveDataFromBankServer(RateWrapperDto wrapper) {
        RestTemplate restTemplate = new RestTemplate();
        String currencyType = wrapper.getCurrencyType();
        List<BankRateDto> exchangeRates = new LinkedList<>();
        for (LocalDate date = wrapper.getStartDate(); date.isBefore(wrapper.getEndDate().plusDays(1));
             date = date.plusDays(1)) {
            BankRateDto serverRate =
                    restTemplate.getForObject(SERVER_EXCHANGE_RATES_URL, BankRateDto.class, currencyType, date);
            if (serverRate == null) {
                throw new BadRequestException(SERVER_NOT_RESPONDED, BankRateDto.class);
            }
            exchangeRates.add(serverRate);
        }
        return exchangeRates;
    }
}
