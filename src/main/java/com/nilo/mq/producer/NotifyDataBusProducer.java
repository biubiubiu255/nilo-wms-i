package com.nilo.mq.producer;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.nilo.mq.model.ProducerDesc;
import com.nilo.mq.producer.AbstractMQProducer;


/**
 * Created by admin on 2017/11/18.
 */
@ProducerDesc(topic = "api_notify", group = "api_group", tags = "notify")
public class NotifyDataBusProducer extends AbstractMQProducer {

    @Override
    protected void beforeSend(Object obj) {
    }
    @Override
    protected void afterSend(Object obj, SendResult result) {
    }
}
