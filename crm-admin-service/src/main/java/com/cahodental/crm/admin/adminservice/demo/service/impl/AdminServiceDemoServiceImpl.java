package com.cahodental.crm.admin.adminservice.demo.service.impl;

import com.cahodental.crm.admin.adminservice.demo.mapper.AdminServiceDemoMapper;
import com.cahodental.crm.admin.adminservice.demo.service.AdminServiceDemoService;
import com.cahodental.crm.admin.model.MyDemo;
import com.cahodental.crm.core.utils.MapUtil;
import com.cahodental.crm.model.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceDemoServiceImpl  implements AdminServiceDemoService {
    @Resource
    private AdminServiceDemoMapper adminServiceDemoMapper;

    @Override
    public MyDemo demoGoods(Long id) {
        return adminServiceDemoMapper.daoDemoGoods(MapUtil.buildMap("id",id));
    }
}
