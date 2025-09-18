package com.yassine.orderservice.dto;

public class DtoOrder {
    private String id ;
    private String message;

    public DtoOrder(){}

    public DtoOrder(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DtoOrder{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
