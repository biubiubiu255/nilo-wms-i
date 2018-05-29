package com.nilo.wms.service.platform.impl;

import com.nilo.wms.dao.platform.*;
import com.nilo.wms.dto.common.ClientConfig;
import com.nilo.wms.dto.common.InterfaceConfig;
import com.nilo.wms.dto.fee.FeeConfig;
import com.nilo.wms.dto.fee.FeePrice;
import com.nilo.wms.dto.platform.parameter.FeeConfigParam;
import com.nilo.wms.dto.platform.parameter.RoleParam;
import com.nilo.wms.dto.platform.system.Permission;
import com.nilo.wms.dto.platform.system.Role;
import com.nilo.wms.service.config.SystemConfig;
import com.nilo.wms.service.platform.RedisUtil;
import com.nilo.wms.service.platform.SystemService;
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
        for (ClientConfig c : list) {
            clientConfigMap.put(c.getClientCode(), c);
        }
        SystemConfig.setClientConfig(clientConfigMap);
    }

    @Override
    public void loadingAndRefreshInterfaceConfig() {

        Map<String, Map<String, InterfaceConfig>> interfaceConfigMap = new HashMap<>();

        for (Map.Entry<String, ClientConfig> entry : SystemConfig.getClientConfig().entrySet()) {

            ClientConfig clientConfig = entry.getValue();
            List<InterfaceConfig> interfaceConfigList = interfaceConfigDao.queryByClientCode(clientConfig.getClientCode());
            Map<String, InterfaceConfig> map = new HashMap<>();
            for (InterfaceConfig i : interfaceConfigList) {
                map.put(i.getBizType(), i);
            }
            interfaceConfigMap.put(clientConfig.getClientCode(), map);
        }
        SystemConfig.setInterfaceConfig(interfaceConfigMap);
    }

    /**
     * 刷新 wms 费用配置
     */
    @Override
    public void loadingAndRefreshWMSFeeConfig() {

        Map<String, Map<String, FeePrice>> feeConfig = new HashMap<>();

        for (Map.Entry<String, ClientConfig> entry : SystemConfig.getClientConfig().entrySet()) {

            FeeConfigParam param = new FeeConfigParam();
            param.setLimit(10000);
            param.setClientCode(entry.getValue().getClientCode());
            List<FeeConfig> list = feeConfigDao.queryBy(param);
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

        List<Role> roles = roleDao.queryBy(new RoleParam());
        for (Role r : roles) {
            List<Permission> list = permissionDao.queryByRoleId(r.getRoleId());
            RedisUtil.del(RedisUtil.getRoleKey(r.getRoleId()));
            if (list == null || list.size() == 0) continue;

            Set<String> s = new HashSet<>();
            for (Permission p : list) {
                s.add(p.getPermissionId());
            }

            RedisUtil.sAdd(RedisUtil.getRoleKey(r.getRoleId()), s.toArray(new String[list.size()]));
        }

    }

}
