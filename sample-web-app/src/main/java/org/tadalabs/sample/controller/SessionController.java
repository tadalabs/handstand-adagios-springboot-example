package org.tadalabs.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tadalabs.sample.domain.Session;
import org.tadalabs.sample.model.SessionList;
import org.tadalabs.sample.service.SessionService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/rest/sessions")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Session> addSession(@Valid @RequestBody Session request) {

        Optional<Session> optionalSession = this.sessionService.addSession(request);

        if(!optionalSession.isPresent()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(optionalSession.get());
    }

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<SessionList> getAllSessions() {

        SessionList sessionList = this.sessionService.findAll();

        if(sessionList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sessionList, HttpStatus.OK);
    }

    @GetMapping(value = "/{sessionId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Session> getSessionById(@Valid @NotBlank @PathVariable("sessionId") String sessionId) {

        Optional<Session> optionalSession = this.sessionService.getSessionById(sessionId);

        if(!optionalSession.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(optionalSession.get());
    }

}
