package by.fin.web.controller.impl;

import by.fin.processing.dto.WeekendDto;
import by.fin.processing.service.WeekendsService;
import by.fin.web.controller.WeekendsController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeekendsControllerImpl implements WeekendsController {
    private final WeekendsService weekendsService;

    @Override
    public ResponseEntity<List<WeekendDto>> findAll() {
        List<WeekendDto> foundData = weekendsService.findAll();
        return new ResponseEntity<>(foundData, HttpStatus.OK);
    }
}
