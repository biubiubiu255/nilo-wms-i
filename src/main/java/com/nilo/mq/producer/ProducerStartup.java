package com.nilo.mq.producer;

import com.nilo.mq.model.ProducerDesc;
import com.nilo.wms.common.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 2017/11/18.
 */
public class ProducerStartup {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String namesrvAddr;

    private List<? extends MQProducer> producers;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public List<? extends MQProducer> getProducers() {
        return producers;
    }

    public void setProducers(List<? extends MQProducer> producers) {
        this.producers = producers;
    }

    public void initial() {
        try {
            buildProducer();
        } catch (Exception e) {
            logger.error("Producer initial Failed.", e);
        }
    }

    public void destroy() {
        try {
            Iterator iterator = producers.iterator();
            while (iterator.hasNext()) {
                AbstractMQProducer producer = (AbstractMQProducer) iterator.next();
                producer.shutdown();
            }
        } catch (Exception e) {
            logger.error("Producer destroy Failed.", e);
        }
    }

    private void buildProducer() throws Exception {

        Iterator iterator = producers.iterator();
        while (iterator.hasNext()) {

            AbstractMQProducer producer = (AbstractMQProducer) iterator.next();
            ProducerDesc producerDesc = BeanUtils.findAnnotation(producer.getClass(), ProducerDesc.class, true);
            String group = producerDesc.group();
            String topic = producerDesc.topic();
            String tags = producerDesc.tags();
            producer.setProducerGroup(group);
            producer.setTags(tags);
            producer.setTopic(topic);
            producer.setNamesrvAddr(this.namesrvAddr);
            producer.startup();

        }
    }
}
