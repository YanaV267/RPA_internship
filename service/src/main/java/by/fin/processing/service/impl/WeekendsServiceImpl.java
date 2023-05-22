package by.fin.processing.service.impl;

import by.fin.module.repository.WeekendsRepository;
import by.fin.processing.dto.WeekendDto;
import by.fin.processing.exception.NoDataFoundException;
import by.fin.processing.mapper.impl.WeekendsMapper;
import by.fin.processing.service.WeekendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.fin.processing.util.ParameterName.WEEKENDS;

@Service
@Transactional
@RequiredArgsConstructor
public class WeekendsServiceImpl implements WeekendsService {
    private final WeekendsRepository repository;
    private final WeekendsMapper mapper;

    @Override
    public List<WeekendDto> findAll() {
        List<WeekendDto> weekends = repository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
        if (weekends.isEmpty()) {
            throw new NoDataFoundException(WEEKENDS, WeekendDto.class);
        }
        return weekends;
    }
}
