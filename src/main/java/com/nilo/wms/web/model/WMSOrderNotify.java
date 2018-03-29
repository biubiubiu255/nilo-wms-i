package com.nilo.wms.web.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *Created by Administrator on 2017/7/27.
 */
@XmlRootElement(name="data")
public class WMSOrderNotify {

    private List<NotifyOrder> list;

    public List<NotifyOrder> getList() {
        return list;
    }

    @XmlElement(name = "orderinfo")
    public void setList(List<NotifyOrder> list) {
        this.list = list;
    }
}
