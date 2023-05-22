package by.fin.web.controller;

import by.fin.processing.dto.ExchangeRateDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("api/exchange-rates")
public interface ExchangeRateController {
    @GetMapping("/")
    @ResponseStatus(OK)
    List<ExchangeRateDto> findAll();
}
