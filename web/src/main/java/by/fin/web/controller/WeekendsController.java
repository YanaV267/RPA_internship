package by.fin.web.controller;

import by.fin.module.entity.Weekend;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/weekends")
public interface WeekendsController {
    @GetMapping("/")
    ResponseEntity<List<Weekend>> findAll();
}
