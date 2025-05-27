package org.leocoder.aiboot.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-27 09:19
 * @description :
 */
@RestController
@RequestMapping("/v2/ai")
public class ChatClientController {

    @Resource
    private ChatClient chatClient;

    /**
     * 普通对话
     * @param message 用户输入的消息
     * @return  机器人回复的消息
     */
    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 一次性返回结果
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    /**
     * 流式对话
     * @param message 用户输入的消息
     * @return  机器人回复的消息流
     */
    @GetMapping(value = "/generateStream", produces = "text/html;charset=utf-8")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        return chatClient.prompt()
                // 提示词
                .user(message)
                // 流式输出
                .stream()
                .content();

    }
}
