/*
 * Kilimall Inc.
 * Copyright (c) 2016. All Rights Reserved.
 */

package com.nilo.wms.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;

/**
 */
public class SpringContext implements ApplicationContextAware, DisposableBean {

    private static volatile ApplicationContext applicationContext;

    private static volatile ServletContext servletContext;

    public static void setServletContext(ServletContext servletContext) {
        SpringContext.servletContext = servletContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * get bean by name
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * get bean by name and type
     *
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    /**
     * get bean by type
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * get resource as stream using servletContext or spring application context
     *
     * @param url
     * @return
     */
    public static InputStream getResourceAsStream(String url) {
        try {
            if (servletContext != null) {
                if (!url.startsWith("/")) {
                    url = "/WEB-INF/classes/" + url;
                }
                return servletContext.getResourceAsStream(url);
            } else {
                return applicationContext.getResource(url).getInputStream();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库连接
     * Note: 需要手动关闭
     *
     * @return
     */
    public static Connection getDbConnection() {
        try {
            DataSource dataSource = getBean(DataSource.class);
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("SpringContext==========destroy");
        servletContext = null;
        applicationContext = null;
    }
}
