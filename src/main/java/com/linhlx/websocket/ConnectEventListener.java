package com.linhlx.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class ConnectEventListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ActiveUsersRepository activeUsersRepository;
    private String loginDestination;
    private String logoutDestination;

    @EventListener
    private void handleConnecting(SessionConnectEvent event){
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = headers.getUser().getName();
        LoginEvent loginEvent = new LoginEvent(username);
        activeUsersRepository.addActiveUser(loginEvent);
        simpMessagingTemplate.convertAndSend("/topic/chat.login", loginEvent);
    }

    @EventListener
    private void handleDisconnecting(SessionDisconnectEvent event){
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = headers.getUser().getName();
        LoginEvent loginEvent = new LoginEvent(username);
        activeUsersRepository.removeActiveUser(loginEvent);
        simpMessagingTemplate.convertAndSend("/topic/chat.logout", loginEvent);
    }
}
