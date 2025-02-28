package com.eldar.config_server.Logstash;

import net.logstash.logback.appender.LogstashTcpSocketAppender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "logstash.enabled", havingValue = "true")
public class configuration {
    @Bean
    public LogstashTcpSocketAppender logstashAppender() {
        LogstashTcpSocketAppender appender = new LogstashTcpSocketAppender();
        appender.addDestination("localhost:5044");
        return appender;
    }

}
