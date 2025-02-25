package com.eldar.config_server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {



    @GetMapping
    public String home() {
        log.info("START - handleException");
        log.info("END - handleException");
        return "Bienvenido al ConfigServer";

    }
}
