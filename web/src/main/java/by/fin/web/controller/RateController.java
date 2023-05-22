package by.fin.web.controller;

import by.fin.processing.dto.RateDto;
import by.fin.processing.dto.RateWrapperDto;
import by.fin.processing.dto.ServerRateDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    List<ServerRateDto> retrieveDataFromBankServer(RateWrapperDto wrapper);
}
