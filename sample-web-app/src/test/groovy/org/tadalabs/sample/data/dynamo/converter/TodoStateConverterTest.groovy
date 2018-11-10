package org.tadalabs.sample.data.dynamo.converter

import org.tadalabs.sample.core.domain.TodoState
import spock.lang.Specification
import spock.lang.Unroll

class TodoStateConverterTest extends Specification {

    @Unroll
    def "convert: Should Properly Map from Enumeration to String"(TodoState testTodoState, String expectedString) {

        expect: "the Correct String to be Returned"
            new TodoStateConverter().convert(testTodoState) == expectedString

        where:
            testTodoState           | expectedString
            TodoState.UNKNOWN       | "UNKNOWN"
            TodoState.COMPLETE      | "COMPLETE"
            TodoState.NOT_STARTED   | "NOT_STARTED"
            TodoState.IN_PROGRESS   | "IN_PROGRESS"
    }

    @Unroll
    def "unconvert: Should Properly Map from String to Enumeration"(String testString, TodoState expectedTodoState) {

        expect: "the Correct TodoState to be Returned"
            new TodoStateConverter().unconvert(testString) == expectedTodoState

        where:
            testString              | expectedTodoState
            "UNKNOWN"               | TodoState.UNKNOWN
            "COMPLETE"              | TodoState.COMPLETE
            "NOT_STARTED"           | TodoState.NOT_STARTED
            "IN_PROGRESS"           | TodoState.IN_PROGRESS
    }

}
