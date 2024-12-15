package cn.fullstack.od.common.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static cn.fullstack.od.common.core.config.BeanConditions.ENABLE_SPRING_SECURITY;

@Configuration
@ConditionalOnProperty(name = ENABLE_SPRING_SECURITY, havingValue = "true")
public class SecurityConfig {

    @Bean({"defaultPasswordEncoder", "passwordEncoder"})
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

}
