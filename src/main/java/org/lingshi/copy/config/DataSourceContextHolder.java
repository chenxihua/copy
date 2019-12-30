package org.lingshi.copy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DataSourceContextHolder
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/18 14:38
 **/
public class DataSourceContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 绑定当前线程数据源路由的key
     * 使用完成后，必须调用 removeRouteKey() 方法删除
     * @param dataSource
     */
    public static void setDataSource(String dataSource){
        contextHolder.set(dataSource);
    }

    public static String getDataSource(){
        logger.warn("线程： {}", contextHolder.get());
        return contextHolder.get();
    }

    public static void clearDataSource(){
        contextHolder.remove();
    }


}
