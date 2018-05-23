/**
 * Kilimall Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.dao.flux;

import com.nilo.wms.dto.flux.StaffWork;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Ronny.zeng
 * @version $Id: DailyReport.java, v 0.1 2016年10月7日 下午3:17:06 Exp $
 */
@Repository
public interface DailyReportDao {

    List<StaffWork> queryDailySKUPicked(Map<String, String> param);

    List<StaffWork> queryDailyOrderPicked(Map<String, String> param);

    List<StaffWork> queryDailyVerification(Map<String, String> param);

    List<StaffWork> queryDailyDispatched(Map<String, String> param);

}
