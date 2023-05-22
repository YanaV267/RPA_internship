package by.fin.processing.mapper.impl;

import by.fin.module.entity.Course;
import by.fin.processing.dto.CourseDto;
import by.fin.processing.mapper.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper extends AbstractMapper<Course, CourseDto> {
    CourseDto mapToDto(Course course);

    Course mapToEntity(CourseDto dto);
}