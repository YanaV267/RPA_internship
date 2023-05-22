package by.fin.processing.service;

import by.fin.processing.dto.ExchangeRateDto;

import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRateDto> findAll();
}
