package com.hzy.org.myspring.core;

/**
 * myspring 应用上下文接口
 */
public interface ApplicationContext {
    /**
     * 根据bean的名称获取bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
