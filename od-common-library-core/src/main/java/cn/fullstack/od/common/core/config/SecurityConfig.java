package cn.fullstack.od.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @date 2024/12/5
 */
@Configuration
public class SecurityConfig {

    @Bean({"defaultPasswordEncoder", "passwordEncoder"})
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

}
