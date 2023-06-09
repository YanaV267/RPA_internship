package by.fin.web.controller;

import by.fin.processing.dto.WeekendDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("api/weekends")
public interface WeekendsController {
    @GetMapping("/")
    @ResponseStatus(OK)
    List<WeekendDto> findAll();
}
