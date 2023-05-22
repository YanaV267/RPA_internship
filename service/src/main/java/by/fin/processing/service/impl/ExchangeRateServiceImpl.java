package by.fin.processing.service.impl;

import by.fin.module.repository.ExchangeRateRepository;
import by.fin.processing.dto.ExchangeRateDto;
import by.fin.processing.exception.NoDataFoundException;
import by.fin.processing.mapper.impl.ExchangeRateMapper;
import by.fin.processing.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.fin.processing.util.ParameterName.EXCHANGE_RATES;

@Service
@Transactional
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateRepository repository;
    private final ExchangeRateMapper mapper;

    @Override
    public List<ExchangeRateDto> findAll() {
        List<ExchangeRateDto> exchangeRates = repository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
        if (exchangeRates.isEmpty()) {
            throw new NoDataFoundException(EXCHANGE_RATES, ExchangeRateDto.class);
        }
        return exchangeRates;
    }
}
