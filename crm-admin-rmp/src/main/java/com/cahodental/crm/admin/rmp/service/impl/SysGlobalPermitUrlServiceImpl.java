
package com.cahodental.crm.admin.rmp.service.impl;

import com.cahodental.crm.admin.rmp.mapper.SysGlobalPermitUrlMapper;
import com.cahodental.crm.admin.rmp.model.SysGlobalPermitUrl;
import com.cahodental.crm.admin.rmp.service.SysGlobalPermitUrlService;
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
public class SysGlobalPermitUrlServiceImpl extends CommonServiceImpl<SysGlobalPermitUrl,Long> implements SysGlobalPermitUrlService,InitializingBean{
    @Resource
    private SysGlobalPermitUrlMapper sysGlobalPermitUrlMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysGlobalPermitUrlMapper;
    }
}
