package org.tadalabs.sample.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tadalabs.sample.core.domain.Version;
import org.tadalabs.sample.core.boundary.enter.VersionService;

@RestController
@RequestMapping("/rest/version")
public class VersionController {

    private final VersionService versionService;

    @Autowired
    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Version> getVersion() {

        Version version = this.versionService.getVersion();

        if(StringUtils.isEmpty(version.getVersion())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(version, HttpStatus.OK);
    }

}
