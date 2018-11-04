package org.tadalabs.sample.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tadalabs.sample.adapter.web.SessionFactory;
import org.tadalabs.sample.core.domain.Session;
import org.tadalabs.sample.adapter.web.api.SessionList;
import org.tadalabs.sample.core.boundary.enter.SessionService;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<SessionList> getAllSessions() {

        Optional<SessionList> optionalSessionList = this.sessionService.findAll();

        return optionalSessionList.map(sessionList -> new ResponseEntity<>(sessionList, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(value = "/initiate", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Session> initializeSession(
            @RequestHeader(value = "Authorization", defaultValue = "") String authorizationToken,
            HttpServletRequest httpServletRequest) {

        if(!sessionService.isSessionValid(authorizationToken, httpServletRequest.getRemoteAddr())) {
            // create a new session domain model
            Session newSession = SessionFactory.session();

            // set the remote address
            newSession.setAddress(httpServletRequest.getRemoteAddr());

            // pass it to the service for persistence
            Optional<Session> optionalSession = this.sessionService.addSession(newSession);

            if(!optionalSession.isPresent()) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(optionalSession.get());
        }

        Optional<Session> optionalSession = sessionService.getSessionById(authorizationToken);

        if(!optionalSession.isPresent()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(optionalSession.get());
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
