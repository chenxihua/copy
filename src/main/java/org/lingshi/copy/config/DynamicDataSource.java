package org.lingshi.copy.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName: DynamicDataSource
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/18 9:56
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }





}
