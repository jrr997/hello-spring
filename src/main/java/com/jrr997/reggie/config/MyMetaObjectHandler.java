package com.jrr997.reggie.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jrr997.reggie.common.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
      log.info("插入");
      log.info(metaObject.toString());
        String empId = BaseContext.getCurrentId();
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", Long.parseLong(empId));
        metaObject.setValue("updateUser", Long.parseLong(empId));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新");
        log.info(metaObject.toString());
    }
}
