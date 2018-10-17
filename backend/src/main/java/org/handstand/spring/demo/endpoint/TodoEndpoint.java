package org.handstand.spring.demo.endpoint;

import org.handstand.spring.demo.service.SessionService;
import org.handstand.spring.demo.entity.TodoEntity;
import org.handstand.spring.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TodoEndpoint {

    @Autowired
    private TodoService todoService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/api/v1/todo", method = RequestMethod.PUT)
    public ResponseEntity<TodoEntity> put(@RequestHeader(value = "Authorization",
            defaultValue="") String authToken, @RequestBody TodoEntity todo,
                          HttpServletRequest request) {
        if (sessionService.validate(authToken, request.getRemoteAddr())) {
            todoService.save(authToken, todo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    @RequestMapping(value = "/api/v1/todo", method = RequestMethod.DELETE)
    public ResponseEntity<TodoEntity> delete(@RequestHeader(value = "Authorization",
            defaultValue="") String authToken, @RequestBody TodoEntity todo,
                                             HttpServletRequest request) {
        if (sessionService.validate(authToken, request.getRemoteAddr())) {
            todoService.remove(authToken, todo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}