package com.nilo.wms.service;

import com.alibaba.fastjson.JSON;
import com.nilo.wms.common.Principal;
import com.nilo.wms.common.SessionLocal;
import com.nilo.wms.dto.ReceiverInfo;
import com.nilo.wms.dto.outbound.OutboundHeader;
import com.nilo.wms.dto.outbound.OutboundItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by admin on 2018/3/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class OutboundServiceTest {

    @Autowired
    private OutboundService outboundService;

    @Test
    public void createOutbound() {

        OutboundHeader header = new OutboundHeader();
        header.setOrderNo("10000000567748");
        header.setCustomerId("KILIMALL");
        header.setWarehouseId("KE01");
        OutboundItem item1 = new OutboundItem();
        item1.setSku("188514");
        item1.setQty(1);
        item1.setLineNo(1);

        List<OutboundItem> list = new ArrayList<>();
        list.add(item1);
        header.setItemList(list);

        ReceiverInfo r = new ReceiverInfo();

        r.setReceiverAddress("Nairobi,Kilimani IBSA Offices, Elgeyo Marakwet Road, off Agwings Kodhek Road, Nairobi Kenya");
        r.setReceiverCity("Kilimani");
        r.setReceiverName("Kaitan Mbuya");
        r.setReceiverPhone("254743156598");

        header.setReceiverInfo(r);

        header.setChannel("0");
        header.setDeliveryNo("KE10113436");
        header.setOrderType("SELL");
        header.setIsCod("1");
        header.setOrderAmount(1359.0);

        Principal p = new Principal();
        p.setClientCode("kilimall");
        SessionLocal.setPrincipal(p);
        outboundService.createOutBound(header);

    }
}
