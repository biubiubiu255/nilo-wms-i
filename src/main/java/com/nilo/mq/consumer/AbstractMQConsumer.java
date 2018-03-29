package com.nilo.mq.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.nilo.mq.KryoMarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by admin on 2017/10/19.
 */
public abstract class AbstractMQConsumer implements MQConsumer {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected DefaultMQPushConsumer consumer;
    protected String consumerGroup;
    protected String namesrvAddr;
    protected String topic;
    protected String filterExpression;
    protected boolean consumeOrderly;
    protected final AtomicBoolean status = new AtomicBoolean();

    public AbstractMQConsumer() {
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFilterExpression() {
        return this.filterExpression;
    }

    public void setFilterExpression(String filterExpression) {
        this.filterExpression = filterExpression;
    }

    public void setConsumeOrderly(boolean consumeOrderly) {
        this.consumeOrderly = consumeOrderly;
    }

    public void startup() {

        if (this.status.compareAndSet(false, true)) {
            this.selfCheck();
            try {
                this.buildConsumer();
            } catch (Exception var2) {
                this.status.set(false);
                this.log.error("build mqConsumer", var2);
            }
        }

    }

    public void shutdown() {
        if (this.status.compareAndSet(true, false)) {
            this.consumer.shutdown();
            this.consumer = null;
        }

    }

    private void selfCheck() {
        Objects.requireNonNull(this.topic);
        if (this.filterExpression == null || this.filterExpression.length() == 0) {
            this.filterExpression = "*";
        }

    }

    private void buildConsumer() throws Exception {
        this.consumer = new DefaultMQPushConsumer(this.consumerGroup);
        this.consumer.setNamesrvAddr(this.namesrvAddr);
        this.consumer.subscribe(this.topic, this.filterExpression);
        this.consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try {
                    if (msgs != null) {
                        KryoMarshaller marshaller = new KryoMarshaller();
                        Iterator var3 = msgs.iterator();
                        while (var3.hasNext()) {
                            MessageExt messageExt = (MessageExt) var3.next();
                            Object obj = marshaller.unmarshal(messageExt.getBody());
                            handleMessage(messageExt, obj);
                        }
                    }
                } catch (Exception var4) {
                    log.error("ConsumeOrderly", var4);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        this.consumer.start();
    }

    public abstract void handleMessage(MessageExt messageExt, Object obj) throws Exception;

}
