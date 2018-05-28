package com.nilo.wms.web.controller.log;

import com.nilo.wms.common.annotation.RequiresPermissions;
import com.nilo.wms.common.util.BeanUtils;
import com.nilo.wms.common.util.DateUtil;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dao.platform.ApiLogDao;
import com.nilo.wms.dto.platform.ApiLog;
import com.nilo.wms.dto.platform.parameter.ApiLogParam;
import com.nilo.wms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/log/api_log_in")
public class ApiLogInController extends BaseController {
    @Autowired
    private ApiLogDao apiLogDao;

    @GetMapping
    @RequiresPermissions("4001")
    public String list(String searchValue, String searchKey, String dateRange) {

        ApiLogParam parameter = new ApiLogParam();
        if (StringUtil.isNotBlank(searchKey)) {
            BeanUtils.setProperty(parameter, searchKey, searchValue);
        }
        if (StringUtil.isNotBlank(dateRange)) {
            String[] date = dateRange.split(" - ");
            parameter.setStart_date(DateUtil.parse(date[0], "yyyy-MM-dd"));
            parameter.setEnd_date(DateUtil.parse(date[1], "yyyy-MM-dd") + 60 * 60 * 24 - 1);
        }
        parameter.setPageInfo(getPage());
        Integer count = apiLogDao.queryCountBy(parameter);
        List<ApiLog> list = new ArrayList<>();
        if (count > 0) {
            list = apiLogDao.queryBy(parameter);
        }

        return toLayUIData(count, list);
    }
}
