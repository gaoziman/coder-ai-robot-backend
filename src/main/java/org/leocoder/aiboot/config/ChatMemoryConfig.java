package org.leocoder.aiboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-28 08:59
 * @description :
 */
@Configuration
@RequiredArgsConstructor
public class ChatMemoryConfig {

    /**
     * 记忆存储
     */
    private final ChatMemoryRepository chatMemoryRepository;

    /**
     * 初始化一个 ChatMemory 实例，并注入到 Spring 容器中
     * @return ChatMemory
     */
    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                // 最大消息窗口为 50，默认值为 20
                .maxMessages(50)
                // 记忆存储器
                .chatMemoryRepository(chatMemoryRepository)
                .build();
    }
}
