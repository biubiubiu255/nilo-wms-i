package com.nilo.wms.web.controller.config;

import com.nilo.wms.common.Principal;
import com.nilo.wms.common.SessionLocal;
import com.nilo.wms.common.annotation.RequiresPermissions;
import com.nilo.wms.dao.platform.ClientConfigDao;
import com.nilo.wms.dao.platform.InterfaceConfigDao;
import com.nilo.wms.dto.common.ClientConfig;
import com.nilo.wms.dto.common.InterfaceConfig;
import com.nilo.wms.dto.common.ResultMap;
import com.nilo.wms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController extends BaseController {
    @Autowired
    private ClientConfigDao clientConfigDao;

    @GetMapping
    @RequiresPermissions("50011")
    public String list() {

        List<ClientConfig> list = clientConfigDao.queryAll();
        return toLayUIData(list.size(), list);
    }

    @PutMapping
    @RequiresPermissions("50012")
    public String update(ClientConfig clientConfig) {

        clientConfigDao.update(clientConfig);
        return ResultMap.success().toJson();
    }


}
