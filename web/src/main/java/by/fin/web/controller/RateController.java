package by.fin.web.controller;

import by.fin.processing.dto.RateDto;
import by.fin.processing.dto.RateWrapperDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping("api/exchange-rates")
public interface RateController {
    @PostMapping("/")
    @ResponseStatus(CREATED)
    List<RateDto> create(@RequestBody @Valid RateWrapperDto wrapperDto);

    @GetMapping("/")
    @ResponseStatus(OK)
    List<RateDto> findAll();

    @GetMapping("/currency")
    @ResponseStatus(OK)
    List<RateDto> findByCurrency(@RequestParam String currencyType);

    @GetMapping("/average")
    @ResponseStatus(OK)
    BigDecimal findAverageInMonth(@RequestParam String currencyType,
                                  @RequestParam @Min(1) @Max(12) int monthNumber,
                                  @RequestParam @Min(2022) @Max(2023) int yearNumber);
}
