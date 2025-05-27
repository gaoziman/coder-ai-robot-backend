package org.leocoder.aiboot.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-27 11:44
 * @description : 自定义日志记录 Advisor
 */
@Slf4j
public class MyLoggerAdvisor implements CallAdvisor {

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        log.info("## 请求入参: {}", chatClientRequest);
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        log.info("## 请求出参: {}", chatClientResponse);
        return chatClientResponse;
    }

    @Override
    public int getOrder() {
        // order 值越小，越先执行
        return 1;
    }

    @Override
    public String getName() {
        // 获取类名称
        return this.getClass().getSimpleName();
    }
}
