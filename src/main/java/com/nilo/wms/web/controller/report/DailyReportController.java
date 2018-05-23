package com.nilo.wms.web.controller.report;

import com.nilo.wms.dao.flux.DailyReportDao;
import com.nilo.wms.dto.flux.StaffWork;
import com.nilo.wms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by admin on 2018/5/23.
 */
@Controller
@RequestMapping("/report")
public class DailyReportController extends BaseController {

    @Autowired
    private DailyReportDao dailyReportDao;

    @RequestMapping(value = "/daily_sku_pick.html", method = {RequestMethod.POST})
    public String list(String dateFrom, String dateTo, String accountNo) {

        List<StaffWork> list = dailyReportDao.queryDailySKUPicked(null);

        return toLayUIData(list.size(), list);
    }


}
