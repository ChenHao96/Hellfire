package com.github.chenhao96.config;

import com.github.chenhao96.controller.interceptor.AuthLoginInterceptor;
import com.github.chenhao96.controller.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Value("${application.resource.locations}")
    private String resourceLocations;

    @Bean
    public HandlerInterceptor getRequestHandlerInterceptor() {
        return new RequestInterceptor();
    }

    @Bean
    public HandlerInterceptor getAuthLoginInterceptor() {
        return new AuthLoginInterceptor();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getRequestHandlerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(getAuthLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "logout");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ByteArrayHttpMessageConverter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profile/**").addResourceLocations(resourceLocations);
    }
}