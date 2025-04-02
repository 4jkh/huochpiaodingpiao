package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.CheciDao;
import com.dao.ZhekouDao;
import com.entity.CheciEntity;
import com.entity.Zhekou;
import com.entity.view.CheciView;
import com.service.CheciService;
import com.service.ZhekouService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 */
@Service("zhekouService")
@Transactional
public class ZhekouServiceImpl extends ServiceImpl<ZhekouDao, Zhekou> implements ZhekouService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<Zhekou> page =new Query<Zhekou>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
