package org.tadalabs.sample.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tadalabs.sample.core.domain.Debug;
import org.tadalabs.sample.core.boundary.enter.DebugService;

@RestController
@RequestMapping("/rest/debug")
public class DebugController {

    private final DebugService debugService;

    @Autowired
    public DebugController(DebugService debugService) {
        this.debugService = debugService;
    }

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Debug> getDebug() {

        Debug debug = this.debugService.getDebug();
        return new ResponseEntity<>(debug, HttpStatus.OK);
    }

}
