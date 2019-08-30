package com.warm.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author xusheng
 * @Create 2019-08-30 23:08
 * @Version 1.0
 **/
@Component
@Aspect
@Order(-100)
@Slf4j
public class DataSourSourceAnnnotationAspect {

    @Pointcut("@annotation(com.warm.common.DataSourceSwitch)")
    public void dataSourcePoint() {

    }

    @Before("dataSourcePoint()")
    public void setDataSource(JoinPoint joinPoint){
        Object target  = joinPoint.getTarget();
        Class<?> clazz = target.getClass();

        DataSourceSwitch dataSourceSwitch = clazz.getAnnotation(DataSourceSwitch.class);
        if(dataSourceSwitch==null){
            MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();

            Method method = methodSignature.getMethod();
            dataSourceSwitch = method.getAnnotation(DataSourceSwitch.class);
        }

        if(dataSourceSwitch!=null){
            log.info("根据注解来切换数据源,注解值为:"+dataSourceSwitch.value());
            switch (dataSourceSwitch.value().getValue()) {
                case "db1":
                    DbContextHolder.setDbType(DBTypeEnum.db1);
                    break;
                case "db2":
                    DbContextHolder.setDbType(DBTypeEnum.db2);
                    break;
                default:
                    DbContextHolder.setDbType(DBTypeEnum.db1);
                    break;
            }
        }else{
            DbContextHolder.setDbType(DBTypeEnum.db1);
        }





    }

    @After("dataSourcePoint()")
    public void after(JoinPoint joinPoint) {
        DbContextHolder.clearDbType();
    }



}
