package com.example;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer retryer(){
        return new  Retryer.Default(
                100, // Początkowy Odstęp między próbami
                1000, //maksymalny odstęp
                3 //ilość prób
        );
    }

    @Bean
    public CustomErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }

}
