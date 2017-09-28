package com.yll.sharde.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author：linlin.yang
 * @date：2017/9/16 16:34
 */
public class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 利用反射给指定对象的指定属性赋值
     * @param obj
     * @param fieldName
     * @param fieldValue
     */
    public static void setFieldValue(Object obj, String fieldName, String fieldValue) {
        Field field = ReflectionUtils.findField(obj.getClass(), fieldName);//利用反射获取指定对象的指定属性
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(obj, fieldValue);
            } catch (IllegalAccessException e) {
                logger.error("setFieldValue IllegalArgumentException");
            }
        }
    }
}
