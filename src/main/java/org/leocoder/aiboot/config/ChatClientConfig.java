package org.leocoder.aiboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-27 09:18
 * @description :
 */
@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    private final ChatMemory chatMemory;

    /**
     * 初始化 ChatClient 客户端
     *
     * @param chatModel 聊天模型
     * @return ChatClient 客户端
     */
    @Bean
    public ChatClient chatClient(DeepSeekChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem("请你扮演一名Leo哥的专属秘书")
                // 添加 Spring AI 内置的日志记录功能
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        // 自定义的日志记录功能
                        // new MyLoggerAdvisor(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
}
