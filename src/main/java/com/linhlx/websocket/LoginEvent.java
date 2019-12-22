package com.linhlx.websocket;

import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

@Getter
@Setter
public class LoginEvent {

    private String username;
    private LocalDate date;

    public LoginEvent(String username) {
        this.username = username;
        date = LocalDate.now();
    }
}
