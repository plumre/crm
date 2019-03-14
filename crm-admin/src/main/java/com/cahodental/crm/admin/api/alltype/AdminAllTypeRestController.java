package com.cahodental.crm.admin.api.alltype;

import com.cahodental.crm.core.common.constant.PageConstant;
import com.cahodental.crm.core.serialize.ResponseMsg;
import com.cahodental.crm.core.service.CommonRestController;
import com.cahodental.crm.model.AllType;
import com.cahodental.crm.service.alltype.service.AllTypeService;
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

@RequestMapping("/admin/all_type_rest/")
@RestController
public class AdminAllTypeRestController extends CommonRestController<AllType,Long> implements InitializingBean
{

    @Resource
    private AllTypeService allTypeService;

    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashMap();
        Integer count = allTypeService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        query.put("safeOrderBy",safeOrderBy);
        return new ResponseMsg(count,allTypeService.getModelList(query));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = allTypeService;
        super.primaryKey = "longId";//硬编码此实体的主键名称
    }
}
