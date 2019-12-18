package org.lingshi.copy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 哎，居然找了一天，终于解决了，是因为springboot会自动配置DataSourceAutoConfiguration，
 *  我如果要使用自己的配置，则必须要将它内置的自动配置给去掉。 这句话是救命的。
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CopyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopyApplication.class, args);
    }

}
