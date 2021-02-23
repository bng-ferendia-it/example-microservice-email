package com.ferendia.emailservice.dto;

public class EmailDTO {

    String email;
    String object;
    String body;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EmailDTO(String email, String object, String body) {
        this.email = email;
        this.object = object;
        this.body = body;
    }
}
