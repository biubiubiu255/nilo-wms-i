package com.nilo.wms.common;

/**
 * Created by ronny on 2017/7/31.
 */
public interface BaseDao<K, T> {
    long deleteById(K var1);
    

    long insert(T var1);

    long update(T var1);

    T queryById(K var1);
}
