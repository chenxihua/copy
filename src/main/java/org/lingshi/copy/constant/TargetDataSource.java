package org.lingshi.copy.constant;

import java.lang.annotation.*;

/**
 * @ClassName: TargetDataSource
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/18 9:42
 *
 * 动态切换数据源注解
 *
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {

    String value() default CommonContant.MASTER_DATASOURCE;

}
