package by.fin.processing.service;

import by.fin.processing.dto.RateDto;
import by.fin.processing.dto.RateWrapperDto;
import by.fin.processing.dto.ServerRateDto;

import java.util.List;

public interface RateService {
    void checkDates(RateWrapperDto wrapperDto);

    List<RateDto> add(RateWrapperDto wrapperDto, List<ServerRateDto> exchangeRates);

    List<RateDto> findAll();
}
