package com.nilo.mq.consumer;


/**
 * Created by admin on 2017/10/19.
 */
public interface MQConsumer {

    void setTopic(String var1);

    void setFilterExpression(String var1);
}
