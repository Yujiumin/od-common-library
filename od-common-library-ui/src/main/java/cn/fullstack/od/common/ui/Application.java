package cn.fullstack.od.common.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @date 2024/12/6
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.fullstack.od")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
