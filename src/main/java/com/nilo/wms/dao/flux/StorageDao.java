package com.nilo.wms.dao.flux;

import com.nilo.wms.dto.OrderHandler;
import com.nilo.wms.dto.StorageInfo;
import com.nilo.wms.dto.StorageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
@Repository
public interface StorageDao {

    List<StorageInfo> queryBy(@Param("param") StorageParam param);

}
