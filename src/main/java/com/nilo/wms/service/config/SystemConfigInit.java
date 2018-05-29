package com.nilo.wms.service.config;

import com.nilo.wms.service.platform.SystemService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/9.
 */
@Component
public class SystemConfigInit implements InitializingBean {

    @Autowired
    private SystemService systemService;

    @Override
    public void afterPropertiesSet() {
        // 加载客户配置
        systemService.loadingAndRefreshClientConfig();

        systemService.loadingAndRefreshInterfaceConfig();

        // 加载 仓库计费配置文件
        systemService.loadingAndRefreshWMSFeeConfig();

        systemService.loadingAndRefreshRole();


    }
}
