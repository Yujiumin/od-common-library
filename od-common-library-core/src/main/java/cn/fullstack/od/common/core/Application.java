package cn.fullstack.od.common.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @date 2024/11/26
 */
@ComponentScan(
        basePackages = "cn.fullstack.od"
)
@SpringBootApplication
public class Application {

    static {
        // do something...
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
