package com.nilo.wms.dao.flux;

import com.nilo.wms.dto.OrderHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
@Repository
public interface WMSFeeDao {

    List<OrderHandler> queryInBoundOrderHandler(String date);

    List<OrderHandler> queryOrderHandler(String date);

    List<OrderHandler> queryOrderReturn(String date);

    List<OrderHandler> queryReturnMerchant(String date);
}
