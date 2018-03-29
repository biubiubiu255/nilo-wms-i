package com.nilo.mq.consumer;

import com.nilo.mq.model.ConsumerDesc;
import com.nilo.wms.common.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 2017/11/18.
 */
public class ConsumerStartup {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String namesrvAddr;

    private List<? extends MQConsumer> consumers;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public List<? extends MQConsumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<? extends MQConsumer> consumers) {
        this.consumers = consumers;
    }

    public void initial() {
        try {
            buildConsumer();
        } catch (Exception e) {
            logger.error("Consumer initial Failed.", e);
        }
    }

    public void destroy() {
        try {
            Iterator iterator = consumers.iterator();
            while (iterator.hasNext()) {
                AbstractMQConsumer consumer = (AbstractMQConsumer) iterator.next();
                consumer.shutdown();
            }
        } catch (Exception e) {
            logger.error("Consumer destroy Failed.", e);
        }
    }

    private void buildConsumer() throws Exception {

        Iterator iterator = consumers.iterator();
        while (iterator.hasNext()) {

            AbstractMQConsumer consumer = (AbstractMQConsumer) iterator.next();
            ConsumerDesc consumerDesc = BeanUtils.findAnnotation(consumer.getClass(), ConsumerDesc.class, true);
            String group = consumerDesc.group();
            String topic = consumerDesc.topic();
            String filterExpression = consumerDesc.filterExpression();
            boolean orderly = consumerDesc.consumeOrderly();
            consumer.setConsumerGroup(group);
            consumer.setFilterExpression(filterExpression);
            consumer.setTopic(topic);
            consumer.setNamesrvAddr(this.namesrvAddr);
            consumer.setConsumeOrderly(orderly);
            consumer.startup();

        }
    }
}
