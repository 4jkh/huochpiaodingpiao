package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.Zhekou;
import com.entity.view.CheciView;
import com.service.ZhekouService;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@Controller
@RequestMapping("/zhekou")
public class ZhekouController {

    @Autowired
    private ZhekouService zhekouService;


    /**
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        List<Zhekou> zhekous = zhekouService.selectList(null);
        return R.ok().put("data", zhekous);
    }

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = zhekouService.queryPage(params);
        return R.ok().put("data", page);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        Zhekou zhekou = zhekouService.selectById(id);
        if(zhekou !=null){
            return R.ok().put("data", zhekou);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Zhekou zhekou, HttpServletRequest request){

        Wrapper<Zhekou> queryWrapper = new EntityWrapper<Zhekou>()
                .eq("piaozhong", zhekou.getPiaozhong())
                ;

        Zhekou zhekouEntity = zhekouService.selectOne(queryWrapper);
        if(zhekouEntity==null){
            zhekouService.insert(zhekou);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Zhekou zhekou, HttpServletRequest request){
        //根据字段查询是否有相同数据
        Wrapper<Zhekou> queryWrapper = new EntityWrapper<Zhekou>()
                .notIn("id",zhekou.getId())
                .andNew()
                .eq("piaozhong", zhekou.getPiaozhong())
                ;

        Zhekou zhekouEntity = zhekouService.selectOne(queryWrapper);
        if(zhekouEntity==null){
            zhekouService.updateById(zhekou);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        if(!ObjectUtils.isEmpty(ids)){
            zhekouService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }else{
            return R.error("失败");
        }
    }
}
