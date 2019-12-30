package org.lingshi.copy.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.lingshi.copy.constant.CommonContant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DynamicDataSourceConfig
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/18 10:06
 **/
@Configuration
public class DynamicDataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceConfig.class);


    @Bean(value = "masterDataSource")
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource(){
        logger.warn("注入主 1： master");
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(value = "slaveDataSource")
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource(){
        logger.warn("注入次 2： slave");
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {

        DynamicDataSource druidDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSource = new HashMap<>(2);
        targetDataSource.put(CommonContant.MASTER_DATASOURCE, masterDataSource);
        targetDataSource.put(CommonContant.SLAVE_DATASOURCE, slaveDataSource);

        logger.info("添加了一个数据源列表, {}", targetDataSource);
        druidDataSource.setDefaultTargetDataSource(masterDataSource);
        druidDataSource.setTargetDataSources(targetDataSource);

        return druidDataSource;
    }




}
