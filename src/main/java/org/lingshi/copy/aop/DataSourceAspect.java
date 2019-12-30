package org.lingshi.copy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.lingshi.copy.config.DataSourceContextHolder;
import org.lingshi.copy.constant.TargetDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName: DataSourceAspect
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/18 9:45
 **/
@Slf4j
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(org.lingshi.copy.constant.TargetDataSource)")
    public void dataSourcePointCut() {}

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        TargetDataSource annotation = method.getAnnotation(TargetDataSource.class);
        // 通过判断 @TargetDataSource 里面的值，来设置数据源
        DataSourceContextHolder.setDataSource(annotation.value());
        try {
            return point.proceed();
        } finally {
            DataSourceContextHolder.clearDataSource();
            log.warn("^o^  清除了数据源");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
