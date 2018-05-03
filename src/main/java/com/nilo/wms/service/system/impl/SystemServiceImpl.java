package com.nilo.wms.service.system.impl;

import com.nilo.wms.dao.platform.*;
import com.nilo.wms.dto.ClientConfig;
import com.nilo.wms.dto.fee.FeeConfig;
import com.nilo.wms.dto.fee.FeePrice;
import com.nilo.wms.dto.InterfaceConfig;
import com.nilo.wms.dto.parameter.RoleParameter;
import com.nilo.wms.dto.system.Permission;
import com.nilo.wms.dto.system.Role;
import com.nilo.wms.service.system.RedisUtil;
import com.nilo.wms.service.system.SystemService;
import com.nilo.wms.service.config.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

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
        SystemConfig.setFeeConfig(feeConfig);

    }

    @Override
    public void loadingAndRefreshRole() {

        List<Role> roles = roleDao.queryBy(new RoleParameter());
        for (Role r : roles) {
            List<Permission> list = permissionDao.queryByRoleId(r.getRoleId());
            Set<String> s = new HashSet<>();
            for (Permission p : list) {
                s.add(p.getPermissionId());
            }
            RedisUtil.sAdd(RedisUtil.getRoleKey(r.getRoleId()), s.toArray(new String[list.size()]));
        }

    }
}
