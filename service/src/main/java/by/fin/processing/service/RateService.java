package by.fin.processing.service;

import by.fin.processing.dto.RateDto;
import by.fin.processing.dto.RateWrapperDto;
import by.fin.processing.dto.BankRateDto;

import java.util.List;

public interface RateService {
    void checkDates(RateWrapperDto wrapperDto);

    List<RateDto> add(RateWrapperDto wrapperDto, List<BankRateDto> exchangeRates);

    List<RateDto> findAll();

    List<RateDto> findByCurrency(String currencyType);
}
