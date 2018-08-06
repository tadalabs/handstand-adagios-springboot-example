package org.handstand.spring.demo.entity;

public class TodoEntity {
    public String id;
    public String sessionId;
    public TodoEntityStateEnum state = TodoEntityStateEnum.TODO;
    public String value;
}