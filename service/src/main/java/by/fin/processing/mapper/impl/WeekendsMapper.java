package by.fin.processing.mapper.impl;

import by.fin.module.entity.Weekend;
import by.fin.processing.dto.WeekendDto;
import by.fin.processing.mapper.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeekendsMapper extends AbstractMapper<Weekend, WeekendDto> {
    WeekendDto mapToDto(Weekend weekend);

    Weekend mapToEntity(WeekendDto dto);
}