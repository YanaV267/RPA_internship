package by.fin.processing.service.impl;

import by.fin.module.entity.Course;
import by.fin.module.repository.CourseRepository;
import by.fin.processing.dto.CourseDto;
import by.fin.processing.mapper.impl.CourseMapper;
import by.fin.processing.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;
    private final CourseMapper mapper;

    @Override
    public void add(CourseDto courseDto) {
        Course course = mapper.mapToEntity(courseDto);
        repository.save(course);
    }
}
