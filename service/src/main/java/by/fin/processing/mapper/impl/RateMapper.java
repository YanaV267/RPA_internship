package by.fin.processing.mapper;

import by.fin.module.entity.Rate;
import by.fin.processing.dto.RateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RateMapper {
    RateDto mapToDto(Rate rate);

    Rate mapToEntity(RateDto dto);
}