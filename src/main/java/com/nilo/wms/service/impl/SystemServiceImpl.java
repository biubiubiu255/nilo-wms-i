package com.nilo.wms.service.impl;

import com.nilo.wms.dao.platform.ClientConfigDao;
import com.nilo.wms.dao.platform.FeeConfigDao;
import com.nilo.wms.dao.platform.InterfaceConfigDao;
import com.nilo.wms.dto.*;
import com.nilo.wms.service.SystemService;
import com.nilo.wms.service.config.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/7/6.
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private ClientConfigDao clientConfigDao;
    @Autowired
    private InterfaceConfigDao interfaceConfigDao;
    @Autowired
    private FeeConfigDao feeConfigDao;

    @Override
    public void loadingAndRefreshClientConfig() {

        List<ClientConfig> list = clientConfigDao.queryAll();

        Map<String, ClientConfig> clientConfigMap = new HashMap<>();

        Map<String, Map<String, InterfaceConfig>> interfaceConfigMap = new HashMap<>();

        for (ClientConfig c : list) {
            clientConfigMap.put(c.getClientCode(), c);
            List<InterfaceConfig> interfaceConfigList = interfaceConfigDao.queryByCode(c.getClientCode());
            Map<String, InterfaceConfig> map = new HashMap<>();
            for (InterfaceConfig i : interfaceConfigList) {
                map.put(i.getBizType(), i);
            }
            interfaceConfigMap.put(c.getClientCode(), map);
        }
        SystemConfig.setClientConfig(clientConfigMap);
        SystemConfig.setInterfaceConfig(interfaceConfigMap);
    }

    /**
     * 刷新 wms 费用配置
     */
    @Override
    public void loadingAndRefreshWMSFeeConfig() {

        Map<String, Map<String, FeePrice>> feeConfig = new HashMap<>();

        for (Map.Entry<String, ClientConfig> entry : SystemConfig.getClientConfig().entrySet()) {
            List<FeeConfig> list = feeConfigDao.queryByClientCode(entry.getValue().getClientCode());
            Map<String, FeePrice> feeConf = new HashMap<>();
            for (FeeConfig c : list) {
                FeePrice fee = new FeePrice();
                fee.setFirstPrice(new BigDecimal(c.getFirstPrice()));
                if (c.getSecondPrice() != null) {
                    fee.setNextPrice(new BigDecimal(c.getSecondPrice()));
                }
                feeConf.put(c.getFeeType() + c.getClassType(), fee);
            }

            feeConfig.put(entry.getKey(), feeConf);
        }
    }
}
