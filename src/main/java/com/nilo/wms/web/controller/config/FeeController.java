package com.nilo.wms.web.controller.config;

import com.nilo.wms.common.Principal;
import com.nilo.wms.common.SessionLocal;
import com.nilo.wms.common.annotation.RequiresPermissions;
import com.nilo.wms.common.util.BeanUtils;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dao.platform.FeeConfigDao;
import com.nilo.wms.dao.platform.InterfaceConfigDao;
import com.nilo.wms.dto.common.InterfaceConfig;
import com.nilo.wms.dto.common.ResultMap;
import com.nilo.wms.dto.fee.FeeConfig;
import com.nilo.wms.dto.platform.parameter.FeeConfigParam;
import com.nilo.wms.dto.platform.parameter.UserParam;
import com.nilo.wms.service.platform.SystemService;
import com.nilo.wms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fee")
public class FeeController extends BaseController {
    @Autowired
    private FeeConfigDao feeConfigDao;
    @Autowired
    private SystemService systemService;
    @GetMapping
    @RequiresPermissions("50031")
    public String list(String searchValue, String searchKey) {

        FeeConfigParam parameter = new FeeConfigParam();
        if (StringUtil.isNotBlank(searchKey)) {
            BeanUtils.setProperty(parameter, searchKey, searchValue);
        }
        parameter.setClientCode(SessionLocal.getPrincipal().getClientCode());
        parameter.setPageInfo(getPage());
        List<FeeConfig> list = new ArrayList<>();
        Integer count = feeConfigDao.queryCountBy(parameter);
        if (count != 0) {
            list = feeConfigDao.queryBy(parameter);
        }
        return toLayUIData(count, list);
    }

    @PostMapping
    @RequiresPermissions("50032")
    public String add(FeeConfig config) {

        config.setClientCode(SessionLocal.getPrincipal().getClientCode());
        feeConfigDao.insert(config);
        return ResultMap.success().toJson();
    }

    @PutMapping
    @RequiresPermissions("50033")
    public String update(FeeConfig config) {
        config.setClientCode(SessionLocal.getPrincipal().getClientCode());
        feeConfigDao.update(config);
        return ResultMap.success().toJson();
    }

    @DeleteMapping("/{feeType}/{classType}")
    @RequiresPermissions("50034")
    public String delete(@PathVariable("feeType") String feeType, @PathVariable("classType") String classType) {

        feeConfigDao.delete(SessionLocal.getPrincipal().getClientCode(), feeType, classType);

        return ResultMap.success().toJson();
    }

    @PostMapping("/refresh")
    @RequiresPermissions("50035")
    public String refresh() {
        systemService.loadingAndRefreshWMSFeeConfig();
        return ResultMap.success().toJson();
    }
}
