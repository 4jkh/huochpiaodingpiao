package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.BarChartData;
import com.entity.Echart;
import com.entity.Excel;
import com.utils.PageUtils;
import com.entity.CheciOrderEntity;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 购票订单 服务类
 */
public interface CheciOrderService extends IService<CheciOrderEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);

    List<Echart> echart();

    List<Excel> excel(Integer id);
}
