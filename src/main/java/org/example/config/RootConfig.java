package org.example.config;

import org.example.dto.MemberDtoListV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RootConfig {
    @Bean
    public MemberDtoListV2 memberDtoListV2() {
        return new MemberDtoListV2();
    }
}
