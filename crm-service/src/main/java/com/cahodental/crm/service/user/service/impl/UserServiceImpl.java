package com...service.user.service.impl;

import com...service.user.mapper.UserMapper;
import com...service.user.service.UserService;
import com...model.User;
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
public class UserServiceImpl extends CommonServiceImpl<User,Long> implements UserService,InitializingBean{
    @Resource
    private UserMapper userMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = userMapper;
    }
}
