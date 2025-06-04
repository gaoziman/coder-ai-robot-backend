package org.leocoder.aiboot.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-31 23:21
 * @description : ZhiPu AI聊天接口
 */
@RestController
@RequestMapping("/v4/ai")
public class ZhiPuController {

    @Resource
    private ZhiPuAiChatModel chatModel;

    /**
     * 普通对话
     *
     * @param message 用户输入的消息
     * @return AI回复的消息
     */
    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 一次性返回结果
        return chatModel.call(message);
    }


    /**
     * 流式对话
     * @param message 用户输入的消息
     * @return AI回复的消息流
     */
    @GetMapping(value = "/generateStream", produces = "text/html;charset=utf-8")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 构建提示词
        Prompt prompt = new Prompt(new UserMessage(message));

        // 流式输出
        return chatModel.stream(prompt)
                .mapNotNull(chatResponse -> chatResponse.getResult().getOutput().getText());

    }

}

