package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.CheciEntity;
import com.entity.Zhekou;
import com.entity.view.CheciView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * @author
 */
public interface ZhekouDao extends BaseMapper<Zhekou> {

   List<Zhekou> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
