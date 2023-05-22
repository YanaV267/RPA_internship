package by.fin.processing.mapper.impl;

import by.fin.module.entity.ExchangeRate;
import by.fin.processing.dto.ExchangeRateDto;
import by.fin.processing.mapper.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExchangeRateMapper extends AbstractMapper<ExchangeRate, ExchangeRateDto> {
    ExchangeRateDto mapToDto(ExchangeRate exchangeRate);

    ExchangeRate mapToEntity(ExchangeRateDto dto);
}