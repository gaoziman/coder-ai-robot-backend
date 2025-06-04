package org.leocoder.aiboot.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Objects;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-02 15:14
 * @description : 对接OpenAI
 */
@RestController
@RequestMapping("/v5/ai")
public class OpenAIController {

    @Resource
    private OpenAiChatModel chatModel;

    /**
     * 普通对话
     *
     * @param message 用户输入的消息
     * @return OpenAI的回复
     */
    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 一次性返回结果
        return chatModel.call(message);
    }


    /**
     * 流式对话
     *
     * @param message 用户输入的消息
     * @return OpenAI的回复流
     */
    @GetMapping(value = "/generateStream", produces = "text/html;charset=utf-8")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 构建提示词
        Prompt prompt = new Prompt(new UserMessage(message));

        // 流式输出
        return chatModel.stream(prompt)
                .mapNotNull(chatResponse -> {
                    Generation generation = chatResponse.getResult();
                    return Objects.nonNull(generation) ? generation.getOutput().getText() : null;
                });

    }


}
