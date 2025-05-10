package com.taskgo.config;

import com.taskgo.constants.URLs;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class GlobalApiPrefixConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setPatternParser(new PathPatternParser());
        configurer.addPathPrefix(URLs.GLOBAL_API_PREFIX, clazz -> clazz.isAnnotationPresent(org.springframework.web.bind.annotation.RestController.class));
    }
}