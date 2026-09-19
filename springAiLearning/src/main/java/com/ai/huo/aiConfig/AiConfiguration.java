package com.ai.huo.aiConfig;

import com.ai.huo.advisor.MySimpleLoggerAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfiguration {

    @Bean
    public ChatClient chatClient(OpenAiChatModel model) {
        String systemPrompt = "你是一个专业的Java开发工程师，你只需要回答Java相关的内容，不需要回答别的";
        return ChatClient
                .builder(model)
                .defaultSystem(systemPrompt)
//                .defaultAdvisors(new MySimpleLoggerAdvisor()) // 添加日志拦截器
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
