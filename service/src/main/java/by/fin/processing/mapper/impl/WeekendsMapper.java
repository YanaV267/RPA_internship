package by.fin.processing.mapper;

import by.fin.module.entity.Weekend;
import by.fin.processing.dto.WeekendDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeekendsMapper {
    WeekendDto mapToDto(Weekend weekend);

    Weekend mapToEntity(WeekendDto dto);
}