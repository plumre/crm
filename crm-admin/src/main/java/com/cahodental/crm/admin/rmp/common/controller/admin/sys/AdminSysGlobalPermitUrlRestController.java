package com.cahodental.crm.admin.rmp.common.controller.admin.sys;

import com.cahodental.crm.admin.rmp.model.SysGlobalPermitUrl;
import com.cahodental.crm.admin.rmp.service.SysGlobalPermitUrlService;
import com.cahodental.crm.core.common.constant.PageConstant;
import com.cahodental.crm.core.serialize.ResponseMsg;
import com.cahodental.crm.core.service.CommonRestController;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@RequestMapping("/admin/sys_global_permit_url_rest/")
@RestController
public class AdminSysGlobalPermitUrlRestController extends CommonRestController<SysGlobalPermitUrl,Long> implements InitializingBean
{

    @Resource
    private SysGlobalPermitUrlService sysGlobalPermitUrlService;
    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="permitNameFirst")                            String permitNameFirst ,
        @RequestParam(required = false,value ="moduleIdFirst")                            Long moduleIdFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashMap();
        query.put("permitNameFirst",coverBlankToNull(permitNameFirst));
        query.put("moduleIdFirst",moduleIdFirst);
        Integer count = sysGlobalPermitUrlService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        query.put("safeOrderBy",safeOrderBy);
        return new ResponseMsg(count,sysGlobalPermitUrlService.getModelList(query));
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = sysGlobalPermitUrlService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
