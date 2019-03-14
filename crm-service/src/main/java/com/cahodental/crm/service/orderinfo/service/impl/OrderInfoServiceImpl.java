package com...service.orderinfo.service.impl;

import com...service.orderinfo.mapper.OrderInfoMapper;
import com...service.orderinfo.service.OrderInfoService;
import com...model.OrderInfo;
import com...core.service.CommonServiceImpl;
import com...core.utils.StringUtil;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com...core.utils.CopyUtil;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@Service
public class OrderInfoServiceImpl extends CommonServiceImpl<OrderInfo,Long> implements OrderInfoService,InitializingBean{
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = orderInfoMapper;
    }
}
