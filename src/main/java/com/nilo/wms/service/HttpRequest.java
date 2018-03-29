/**
 * KILIMALL.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.service;

/**
 * http请求引擎
 */
public interface HttpRequest<T,R> {

    /**
     * 请求
     * @param t
     * @return
     */
     R doRequest(T t);

}
