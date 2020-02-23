package com.github.chenhao96.config;

import com.github.chenhao96.controller.interceptor.RequestInterceptor;
import com.github.chenhao96.controller.interceptor.VerificationDeviceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SpringWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor requestHandlerInterceptor() {
        return new RequestInterceptor();
    }

    @Bean
    public HandlerInterceptor verificationDeviceInterceptor() {
        return new VerificationDeviceInterceptor();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/403").setViewName("403");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(verificationDeviceInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(VerificationDeviceInterceptor.VERIFICATION_DEVICE_URL)
                .excludePathPatterns(SpringWebSecurityConfig.IGNORE_PATTERNS);
        registry.addInterceptor(requestHandlerInterceptor()).addPathPatterns("/api/**");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ByteArrayHttpMessageConverter());
    }
}