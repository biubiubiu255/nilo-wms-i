package com.nilo.mq.producer;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.nilo.wms.common.util.IdWorker;
import com.nilo.mq.KryoMarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by admin on 2017/10/19.
 */
public abstract class AbstractMQProducer implements MQProducer {

    private static final int MAX_BYTES = 131072;
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected DefaultMQProducer producer;
    private AtomicBoolean status = new AtomicBoolean();
    private String topic;
    private String tags;
    private String producerGroup;
    private String namesrvAddr;

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void startup() {
        if (this.status.compareAndSet(false, true)) {
            try {
                this.producer = new DefaultMQProducer(this.producerGroup);
                this.producer.setNamesrvAddr(this.namesrvAddr);
                this.producer.setRetryTimesWhenSendFailed(3);
                this.producer.start();
            } catch (Exception var2) {
                this.log.error("build producer error", var2);
            }
        }

    }

    public void shutdown() {
        if (this.status.compareAndSet(true, false)) {
            this.producer.shutdown();
            this.producer = null;
        }

    }

    private void checkStatus() {
        if (!this.status.get()) {
            throw new IllegalStateException("producer is shutdown");
        }
    }


    protected abstract void beforeSend(Object obj);

    protected abstract void afterSend(Object obj, SendResult result);

    public void sendMessage(Object obj) throws Exception {

        beforeSend(obj);
        byte[] body = this.marshal(obj);
        String key = "" + IdWorker.getInstance().nextId();
        Message msg = new Message(this.topic, this.tags, key, body);
        SendResult result;
        try {
            this.checkStatus();
            result = this.producer.send(msg, 1000);
        } catch (RemotingException | MQClientException var7) {
            throw new Exception("Sending exception obj: " + obj, var7);
        } catch (MQBrokerException var8) {
            this.log.error("Sending obj={}", obj, var8);
            throw new Exception(var8);
        } catch (InterruptedException var9) {
            throw new RuntimeException(var9);
        }

        afterSend(obj, result);
        if (result.getSendStatus() != SendStatus.SEND_OK) {
            throw new Exception("Unexpected result: " + result.getSendStatus() + " obj: " + obj);
        }
    }

    protected byte[] marshal(Object obj) throws Exception {
        byte[] body;
        try {
            KryoMarshaller marshaller = new KryoMarshaller();
            body = marshaller.marshal(obj);
        } catch (Exception var10) {
            throw new Exception("marshalEnvelope obj=" + obj, var10);
        } finally {
            //this.marshallerFactory.release(marshaller);
        }

        if (body.length > 0 && body.length <= 131072) {
            return body;
        } else {
            throw new IllegalArgumentException("invalid body length " + body.length);
        }
    }
}
