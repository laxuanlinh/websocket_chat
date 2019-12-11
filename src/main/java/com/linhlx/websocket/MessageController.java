package com.linhlx.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
//@RestController
public class MessageController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void send(String message){
        String time = LocalDate.now().toString();
        simpMessagingTemplate.convertAndSend("/user/linh/topic/message", message);
    }

    @MessageMapping("/chat.private.{username}")
    public void sendToUser(String message, @DestinationVariable("username") String username){
        simpMessagingTemplate.convertAndSendToUser(username, "/topic/message", message);
    }

    @PostMapping("/channel")
    @ResponseBody
    public Channel createChannel(@RequestBody Channel channel){
        channel.setId(UUID.randomUUID());
        return channelRepository.save(channel);
    }

    @GetMapping("")
    public String chat(){
        return "index";
    }

}
