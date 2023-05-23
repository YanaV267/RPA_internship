package by.fin.processing.mapper.impl;

import by.fin.module.entity.Rate;
import by.fin.processing.dto.RateDto;
import by.fin.processing.mapper.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RateMapper extends AbstractMapper<Rate, RateDto> {
    RateDto mapToDto(Rate rate);

    Rate mapToEntity(RateDto dto);
}