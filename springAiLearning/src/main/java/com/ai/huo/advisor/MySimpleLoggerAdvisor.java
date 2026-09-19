package com.ai.huo.advisor;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

/**
 * 实现流式和非流式
 */
public class MySimpleLoggerAdvisor implements CallAdvisor, StreamAdvisor {


    @Override
    public ChatClientResponse adviseCall(ChatClientRequest request, CallAdvisorChain chain) {
        System.out.println("发送请求前： " + request);
        ChatClientResponse response = chain.nextCall(request);
        System.out.println("接收到相应： " + response);
        return response;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest request, StreamAdvisorChain chain) {
        System.out.println("发送请求前: " + request);
        return chain.nextStream(request).doOnNext(response -> System.out.println("接收到流式响应片段： " + response));
    }

    @Override
    public String getName() {
        return "简单日志";
    }

    /**
     * 0优先级最高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
