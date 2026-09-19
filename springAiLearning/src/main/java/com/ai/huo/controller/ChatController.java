package com.ai.huo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

@Controller
public class ChatController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/ai")
    @ResponseBody
    public String chat(@RequestParam String question){
        return chatClient.prompt().user(question).call().content();
    }

    /**
     * 流式
     * @param question
     * @return
     */
    @GetMapping(value = "/fluxAi", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Flux<String> fluxChat(@RequestParam String question){
        return chatClient
                .prompt()
                .user(question)
                .stream()
                .content();
    }

    /**
     * 流式
     * 提示词模板
     * @param question
     * @return
     */
    @GetMapping(value = "/fluxPromptAi", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Flux<String> fluxPromptChat(@RequestParam String question){
        return chatClient
                .prompt()
                .user(question)
                .stream()
                .content();
    }
}
