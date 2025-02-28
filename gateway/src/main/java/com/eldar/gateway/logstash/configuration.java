package com.eldar.gateway.logstash;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configuration {
    @Value("${logstash.enable}")
    private boolean enabledLogstash;

    @Value("${spring.application.name}")
    private String serviceName;

    @Bean
    public LoggerContext loggerContext() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset();  // Reset previous configuration

        // Configurar el appender de consola
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setContext(context);
        consoleAppender.setName("CONSOLE");

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - [" + serviceName + "] - %msg%n");
        encoder.start();
        consoleAppender.setEncoder(encoder);
        consoleAppender.start();

        // Configurar el appender para Logstash si está habilitado
        if (enabledLogstash) {
            LogstashTcpSocketAppender logstashAppender = new LogstashTcpSocketAppender();
            logstashAppender.setContext(context);
            logstashAppender.setName("LOGSTASH");

            logstashAppender.addDestination("localhost:5044"); // Dirección de Logstash

            LogstashEncoder logstashEncoder = new LogstashEncoder();
            logstashEncoder.setContext(context);
            logstashEncoder.start();
            logstashAppender.setEncoder(logstashEncoder);
            logstashAppender.start();

            // Añadir ambos appenders: Logstash y Consola
            context.getLogger("ROOT").addAppender(logstashAppender);
        }

        // Añadir el appender de consola siempre
        context.getLogger("ROOT").addAppender(consoleAppender);

        // Configurar el nivel de log
        context.getLogger("ROOT").setLevel(ch.qos.logback.classic.Level.INFO);

        return context;
    }

}