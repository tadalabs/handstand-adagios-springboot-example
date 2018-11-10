package org.tadalabs.sample.web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.tadalabs.sample.adapter.web.api.TodoList
import org.tadalabs.sample.adapter.web.api.TodoListItem
import org.tadalabs.sample.core.boundary.enter.SessionService
import org.tadalabs.sample.core.boundary.enter.TodoService
import org.tadalabs.sample.core.domain.TodoState
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@WebMvcTest
class TodoControllerIntegrationTest extends Specification {

    @Shared
    MockMvc mockMvc

    @Autowired
    TodoService todoService = Stub(TodoService)

    @Autowired
    SessionService sessionService = Stub(SessionService)

    void setup() {
        mockMvc = standaloneSetup(new TodoController(todoService, sessionService)).build()
    }

    def "Spring Context should load Todo Endpoint"() {

        given: 'a sample TodoList object to test against'
            String testTodoId = 'hansels-list'
            String testValue = 'bake bread'
            String testSessionId = 'afternoon-chores'
            TodoState testTodoState = TodoState.COMPLETE
            TodoListItem testTodoListItem = new TodoListItem(testTodoId, testValue, testSessionId, testTodoState)
            Optional<TodoList> testTodoList = Optional.of(new TodoList([testTodoListItem]))

        and: 'a Stubbed TodoService that is set to always return a collection containing our sample TodoList'
            todoService.findAllWithSessionId(_ as String) >> testTodoList

        and: 'a Stubbed SessionService that is set to always return true for session checks'
            boolean isSessionValid = true
            sessionService.isSessionValid(_ as String, _ as String) >> isSessionValid

        expect: "controller is available and returns the expected resultant"
            mockMvc.perform(get('/rest/todos/'))
                .andExpect(status().isOk())
                .andExpect(content().string("" +
                    "{\"todos\":[" +
                    "{\"todoId\":\"${testTodoListItem.todoId}\"," +
                    "\"value\":\"${testTodoListItem.value}\"," +
                    "\"sessionId\":\"${testTodoListItem.sessionId}\"," +
                    "\"todoState\":\"${testTodoListItem.todoState.toString()}\"" +
                    "}" +
                    "],\"empty\":false}"))
    }

}
