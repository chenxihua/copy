package org.lingshi.copy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 哎，居然找了一天，终于解决了，是因为springboot会自动配置 DataSourceAutoConfiguration，
 *  我如果要使用自己的配置，则必须要将它内置的自动配置给去掉。 这句话是救命的。
 *
 *  @EnableTransactionManagement 这个注解，主要是启用事务管理，
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
@EnableTransactionManagement
public class CopyApplication {

    private static final Logger logger = LoggerFactory.getLogger(CopyApplication.class);

    /**
     * 这个方法，看看网上对 @EnableTransactionManagement 这个注释的解析
     * @param platformTransactionManager
     * @return
     */
    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
        // =====>>>>>> : org.springframework.orm.jpa.JpaTransactionManager
        logger.warn("=====>>>>>> : {}", platformTransactionManager.getClass().getName());
        return new Object();
    }

    public static void main(String[] args) {
        SpringApplication.run(CopyApplication.class, args);
    }

}
