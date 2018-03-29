package com.nilo.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.nilo.mq.model.ConsumerDesc;
import com.nilo.mq.model.NotifyRequest;
import com.nilo.mq.model.NotifyResponse;
import com.nilo.wms.common.util.HttpUtil;
import com.nilo.wms.common.util.MailInfo;
import com.nilo.wms.common.util.SendEmailUtil;
import com.nilo.wms.dao.platform.NotifyDao;
import com.nilo.wms.dto.NotifyDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/10/18.
 */
@ConsumerDesc(topic = "api_notify", group = "api_group", filterExpression = "notify")
public class NotifyDataBusConsumer extends AbstractMQConsumer {

    private static Logger logger = LoggerFactory.getLogger(NotifyDataBusConsumer.class);

    @Autowired
    private NotifyDao notifyDao;

    @Override
    public void handleMessage(MessageExt messageExt, Object obj) throws Exception {

        String msgId = messageExt.getKeys();
        NotifyRequest request = null;
        String response = "";
        try {

            request = (NotifyRequest) obj;
            response = HttpUtil.post(request.getUrl(), request.getParam());
            NotifyResponse notifyResponse = JSON.parseObject(response, NotifyResponse.class);
            if (notifyResponse != null && notifyResponse.isSuccess()) {
                saveNotify(request, msgId, response, true);
            } else {
                saveNotify(request, msgId, response, false);
                logger.error("Notify Failed. NotifyID:{}, Response:{} , NotifyRequest:{}", msgId, response, request);
            }
        } catch (Exception e) {
            saveNotify(request, msgId, response, false);
            logger.error("Notify Failed. NotifyID:{}, Response:{} , NotifyRequest:{}", msgId, response, request);
            if (messageExt.getReconsumeTimes() == 4) {
                return;
            }
            throw e;
        }
    }

    private void saveNotify(NotifyRequest request, String notifyId, String response, boolean success) {

        NotifyDO query = notifyDao.queryByNotifyId(notifyId);
        if (query == null) {
            NotifyDO notifyDO = new NotifyDO();
            notifyDO.setUrl(request.getUrl());
            notifyDO.setStatus(success ? 1 : 0);
            notifyDO.setNum(1);
            notifyDO.setNotifyId(notifyId);
            notifyDO.setParam(request.getParam().toString());
            notifyDO.setResult(response);
            notifyDao.insert(notifyDO);
        } else {
            NotifyDO update = new NotifyDO();
            update.setNotifyId(notifyId);
            update.setStatus(success ? 1 : 0);
            update.setNum(query.getNum() + 1);
            update.setResult(response);
            notifyDao.update(update);
        }
    }

}
