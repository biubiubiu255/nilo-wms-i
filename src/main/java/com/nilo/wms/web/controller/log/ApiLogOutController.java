package com.nilo.wms.web.controller.log;

import com.nilo.wms.common.annotation.RequiresPermissions;
import com.nilo.wms.common.util.BeanUtils;
import com.nilo.wms.common.util.DateUtil;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dao.platform.NotifyDao;
import com.nilo.wms.dto.common.ResultMap;
import com.nilo.wms.dto.platform.Notify;
import com.nilo.wms.dto.platform.parameter.NotifyParam;
import com.nilo.wms.dto.platform.parameter.UserParam;
import com.nilo.wms.dto.platform.system.User;
import com.nilo.wms.service.platform.UserService;
import com.nilo.wms.web.BaseController;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/log/api_log_out")
public class ApiLogOutController extends BaseController {
    @Autowired
    private NotifyDao notifyDao;

    @GetMapping
    @RequiresPermissions("40021")
    public String list(String searchValue, String searchKey, String dateRange) {

        NotifyParam parameter = new NotifyParam();
        if (StringUtil.isNotBlank(searchKey)) {
            BeanUtils.setProperty(parameter, searchKey, searchValue);
        }
        if (StringUtil.isNotBlank(dateRange)) {
            String[] date = dateRange.split(" - ");
            parameter.setStart_date(DateUtil.parse(date[0], "yyyy-MM-dd"));
            parameter.setEnd_date(DateUtil.parse(date[1], "yyyy-MM-dd") + 60 * 60 * 24 - 1);
        }
        parameter.setPageInfo(getPage());

        List<Notify> list = new ArrayList<>();
        Long count = notifyDao.queryCountBy(parameter);
        if (count != 0) {
            list = notifyDao.queryBy(parameter);
        }
        return toLayUIData(count.intValue(), list);
    }

}
