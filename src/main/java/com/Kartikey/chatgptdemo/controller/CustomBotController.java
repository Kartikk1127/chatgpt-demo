package com.Kartikey.chatgptdemo.controller;

import com.Kartikey.chatgptdemo.dto.ChatGptRequest;
import com.Kartikey.chatgptdemo.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bot")
public class CustomBotController {


    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){
        ChatGptRequest request = new ChatGptRequest(model, prompt);
       ChatGptResponse chatGptResponse = template.postForObject(apiUrl,request,ChatGptResponse.class);
       return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}
