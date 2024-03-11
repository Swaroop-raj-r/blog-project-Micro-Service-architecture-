package com.micro_services.Comment.config;

import com.micro_services.Comment.payload.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }


}
