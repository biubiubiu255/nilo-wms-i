package com.nilo.wms.web.controller.report;

import com.nilo.wms.common.annotation.RequiresPermissions;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dao.flux.FluxReportDao;
import com.nilo.wms.dto.flux.StaffWork;
import com.nilo.wms.web.BaseController;
import org.apache.commons.beanutils.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/5/23.
 */
@Controller
@RequestMapping("/report")
public class FluxReportController extends BaseController {

    @Autowired
    private FluxReportDao fluxReportDao;

    @RequiresPermissions("3001")
    @GetMapping("/daily_pick")
    @ResponseBody
    public String daily_pick(String searchKey, String searchValue, String dateRange) {

        Map<String, String> param = new HashMap<>();
        param.put(searchKey, searchValue);
        if (StringUtil.isNotBlank(dateRange)) {
            String[] date = dateRange.split(" - ");
            param.put("start_date", date[0]);
            param.put("end_date", date[1]);
        }
        List<StaffWork> list = fluxReportDao.daily_pick(param);
        return toLayUIData(list.size(), list);
    }

    @RequiresPermissions("3002")
    @GetMapping("/daily_verify")
    @ResponseBody
    public String daily_verify(String searchKey, String searchValue, String dateRange) {

        Map<String, String> param = new HashMap<>();
        param.put(searchKey, searchValue);
        if (StringUtil.isNotBlank(dateRange)) {
            String[] date = dateRange.split(" - ");
            param.put("start_date", date[0]);
            param.put("end_date", date[1]);
        }
        List<StaffWork> list = fluxReportDao.daily_verify(param);
        return toLayUIData(list.size(), list);
    }

    @RequiresPermissions("3003")
    @GetMapping("/daily_dispatch")
    @ResponseBody
    public String daily_dispatch(String searchKey, String searchValue, String dateRange) {

        Map<String, String> param = new HashMap<>();
        param.put(searchKey, searchValue);
        if (StringUtil.isNotBlank(dateRange)) {
            String[] date = dateRange.split(" - ");
            param.put("start_date", date[0]);
            param.put("end_date", date[1]);
        }
        List<StaffWork> list = fluxReportDao.daily_dispatch(param);
        return toLayUIData(list.size(), list);
    }
}
