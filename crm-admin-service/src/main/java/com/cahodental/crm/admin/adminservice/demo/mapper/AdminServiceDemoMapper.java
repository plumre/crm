package com.cahodental.crm.admin.adminservice.demo.mapper;

import com.cahodental.crm.admin.model.MyDemo;
import com.cahodental.crm.model.Goods;

import java.util.Map;

public interface AdminServiceDemoMapper {
    MyDemo daoDemoGoods(Map<String,Object> query);

}
