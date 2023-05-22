package by.fin.processing.service.impl;

import by.fin.module.repository.WeekendsRepository;
import by.fin.processing.dto.WeekendDto;
import by.fin.processing.mapper.impl.WeekendsMapper;
import by.fin.processing.service.WeekendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WeekendsServiceImpl implements WeekendsService {
    private final WeekendsRepository repository;
    private final WeekendsMapper mapper;

    @Override
    public List<WeekendDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
    }
}
