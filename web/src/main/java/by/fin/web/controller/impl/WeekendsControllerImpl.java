package by.fin.web.controller.impl;

import by.fin.module.entity.Weekend;
import by.fin.service.WeekendService;
import by.fin.web.controller.WeekendsController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeekendsControllerImpl implements WeekendsController {
    private final WeekendService weekendService;

    @Override
    public ResponseEntity<List<Weekend>> findAll() {
        List<Weekend> foundData = weekendService.findAll();
        return new ResponseEntity<>(foundData, HttpStatus.OK);
    }
}
