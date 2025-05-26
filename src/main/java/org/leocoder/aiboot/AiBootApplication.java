package org.leocoder.aiboot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-26 20:14
 * @description : 启动类
 */
@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
@MapperScan("org.leocoder.aiboot.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableScheduling // 开启定时任务支持
@EnableAsync // 开启异步支持
public class AiBootApplication {
    private final ServerProperties serverProperties;

    public static void main(String[] args) {
        SpringApplication.run(AiBootApplication.class, args);
    }
}
