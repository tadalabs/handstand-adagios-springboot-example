package org.handstand.spring.demo.endpoint;

import org.handstand.spring.demo.entity.SessionEntity;
import org.handstand.spring.demo.service.SessionService;
import org.handstand.spring.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SessionEndpoint {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/api/v1/session/initiate", method = RequestMethod.GET)
    public SessionEntity initiate(@RequestHeader(value = "Authorization",
            defaultValue="") String authToken, HttpServletRequest request) {
        SessionEntity session;
        if (!sessionService.validate(authToken, request.getRemoteAddr())) {
            session = new SessionEntity();
            sessionService.initiate(session, request);
            todoService.initialize(session.Id);
        } else {
            return sessionService.get(authToken);
        }
        return session;
    }
}
