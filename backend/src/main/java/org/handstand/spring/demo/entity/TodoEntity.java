package org.handstand.spring.demo.entity;

public class TodoEntity {
    public String Id;
    public String SessionId;
    public TodoEntityStateEnum State = TodoEntityStateEnum.TODO;
    public String Value;
}