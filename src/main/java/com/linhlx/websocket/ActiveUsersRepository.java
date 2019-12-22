package com.linhlx.websocket;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ActiveUsersRepository {

    private static ConcurrentHashMap<String, String> activeUsers = new ConcurrentHashMap<>();

    public void addActiveUser(LoginEvent loginEvent){
        activeUsers.put(loginEvent.getUsername(), loginEvent.getDate().toString());
    }

    public void removeActiveUser(LoginEvent loginEvent){
        activeUsers.remove(loginEvent.getUsername());
    }

    public static ConcurrentHashMap getActiveUsers(){
        return activeUsers;
    }

}
