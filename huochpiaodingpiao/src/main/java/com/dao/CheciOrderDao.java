package com.dao;

import com.entity.BarChartData;
import com.entity.CheciOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.Echart;
import com.entity.Excel;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CheciOrderView;

/**
 * 购票订单 Dao 接口
 *
 * @author
 */
public interface CheciOrderDao extends BaseMapper<CheciOrderEntity> {

   List<CheciOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

    List<Echart> echart();

    List<Excel> excel(Integer id);
}
