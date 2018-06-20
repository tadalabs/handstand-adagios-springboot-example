package org.handstand.spring.demo.endpoint;

import org.handstand.spring.demo.entity.TodoEntity;
import org.handstand.spring.demo.service.SessionService;
import org.handstand.spring.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TodosEndpoint {

    @Autowired
    private TodoService todoService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/api/v1/todos", method = RequestMethod.GET)
    public ResponseEntity<List<TodoEntity>> list(@RequestHeader(value = "Authorization",
            defaultValue="") String authToken, HttpServletRequest request) {
        if (sessionService.validate(authToken, request.getRemoteAddr())) {
            return new ResponseEntity<>(todoService.list(authToken), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/api/v1/todos/{id}", method = RequestMethod.GET)
    public ResponseEntity<TodoEntity> get(@RequestHeader(value = "Authorization",
            defaultValue="") String authToken, @PathVariable String id,
                          HttpServletRequest request) {
        if (sessionService.validate(authToken, request.getRemoteAddr())) {
            return new ResponseEntity<>(todoService.list(authToken).stream()
                    .filter(e -> e.Id == id).findFirst().get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}