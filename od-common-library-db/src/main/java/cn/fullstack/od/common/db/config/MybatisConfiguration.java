package cn.fullstack.od.common.db.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @date 2024/12/5
 */
@Configuration
@MapperScan(basePackages = "cn.fullstack.od.common.db.mapper")
public class MybatisConfiguration {
}
