package by.fin.web.controller.impl;

import by.fin.processing.dto.ExchangeRateDto;
import by.fin.processing.service.ExchangeRateService;
import by.fin.web.controller.ExchangeRateController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class ExchangeRateControllerImpl implements ExchangeRateController {
    private final ExchangeRateService service;

    @Override
    public List<ExchangeRateDto> findAll() {
        return service.findAll();
    }
}
