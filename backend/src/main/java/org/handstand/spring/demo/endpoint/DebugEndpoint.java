package org.handstand.spring.demo.endpoint;
import org.handstand.spring.demo.entity.SessionEntity;
import org.handstand.spring.demo.service.DebugService;
import org.handstand.spring.demo.service.SessionService;
import org.handstand.spring.demo.entity.TodoEntity;
import org.handstand.spring.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DebugEndpoint {

    @Autowired
    SessionService sessionService;

    @Autowired
    TodoService todoService;

    @Autowired
    DebugService debugService;

    @RequestMapping(value = "/api/v1/debug/state")
    public Boolean state() {
        return debugService.DEBUG_STATE;
    }

    @RequestMapping(value = "/api/v1/debug/all-sessions")
    public List<SessionEntity> listSessions() {
        if (debugService.DEBUG_STATE) {
            return sessionService.list();
        } else {
            return new ArrayList<>();
        }
    }
    @RequestMapping("/api/v1/debug/all-todos")
    public List<TodoEntity> listTodos() {
        if (debugService.DEBUG_STATE) {
            ArrayList<TodoEntity> allTodos = new ArrayList<TodoEntity>();
            for (SessionEntity session : sessionService.list()) {
                for (TodoEntity todo : todoService.list(session.Id)) {
                    allTodos.add(todo);
                }
            }
            return allTodos;
        } else {
            return new ArrayList<>();
        }
    }
}