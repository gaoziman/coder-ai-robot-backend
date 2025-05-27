package org.leocoder.aiboot.config;

import org.springframework.ai.chat.client.ChatClient;
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
public class ChatClientConfig {

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
                .build();
    }
}
