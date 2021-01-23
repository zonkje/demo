package com.example.demo.logging;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

import java.util.Objects;

@Configuration
public class LoggingConfig implements WebMvcConfigurer {

    private final LoggerService loggerService;

    public LoggingConfig(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(new WebRequestInterceptor() {

            @Override
            public void preHandle(WebRequest request) throws Exception {
                if (request instanceof DispatcherServletWebRequest && (
                        Objects.equals(((DispatcherServletWebRequest) request).getHttpMethod(), HttpMethod.GET)
                                || Objects
                                .equals(((DispatcherServletWebRequest) request).getHttpMethod(), HttpMethod.DELETE))) {
                    loggerService.request(((DispatcherServletWebRequest) request).getRequest(), null);
                }
            }

            @Override
            public void postHandle(WebRequest request, ModelMap model) throws Exception {

            }

            @Override
            public void afterCompletion(WebRequest request, Exception ex) throws Exception {

            }
        });
    }
}
