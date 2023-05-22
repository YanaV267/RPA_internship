package by.fin.processing.service;

import by.fin.module.entity.Weekend;
import by.fin.processing.dto.WeekendDto;

import java.time.LocalDate;
import java.util.List;

public interface WeekendsService {
    List<WeekendDto> findAll();

    WeekendDto findWeekendByDate(LocalDate date);

    Weekend findWeekendEntityByDate(LocalDate date);
}
