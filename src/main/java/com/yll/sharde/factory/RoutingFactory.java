package com.yll.sharde.factory;

import com.yll.sharde.handler.RoutingHandler;
import com.yll.sharde.util.ShardingUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author：linlin.yang
 * @date：2017/9/16 17:40
 */
@Component
public class RoutingFactory implements ApplicationContextAware {
    private static ApplicationContext context;

    public static RoutingHandler getRoutingInterceptor(Object obj) {
        Class clazz = ShardingUtil.RoutingInterceptorPair.get(obj.getClass());
        if (clazz == null) {
            return null;
        }

        Object routingObject = context.getBean(clazz);
        if (routingObject == null) {
            return null;
        }

        return (RoutingHandler) routingObject;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
