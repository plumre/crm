
package com.cahodental.crm.admin.rmp.service.impl;

import com.cahodental.crm.admin.rmp.mapper.SysPermissionMapper;
import com.cahodental.crm.admin.rmp.model.SysPermission;
import com.cahodental.crm.admin.rmp.service.SysPermissionService;
import com.cahodental.crm.core.service.CommonServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@Service
public class SysPermissionServiceImpl extends CommonServiceImpl<SysPermission,Long> implements SysPermissionService,InitializingBean{
    @Resource
    private SysPermissionMapper sysPermissionMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysPermissionMapper;
    }
}
