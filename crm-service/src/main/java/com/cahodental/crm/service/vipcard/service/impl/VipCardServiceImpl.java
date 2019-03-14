package com...service.vipcard.service.impl;

import com...service.vipcard.mapper.VipCardMapper;
import com...service.vipcard.service.VipCardService;
import com...model.VipCard;
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
public class VipCardServiceImpl extends CommonServiceImpl<VipCard,Long> implements VipCardService,InitializingBean{
    @Resource
    private VipCardMapper vipCardMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = vipCardMapper;
    }
}
