package by.fin.web.controller.impl;

import by.fin.processing.dto.WeekendDto;
import by.fin.processing.service.WeekendsService;
import by.fin.web.controller.WeekendsController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeekendsControllerImpl implements WeekendsController {
    private final WeekendsService weekendsService;

    @Override
    public List<WeekendDto> findAll() {
        return weekendsService.findAll();
    }
}
