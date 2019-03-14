package com...admin.api.shop;

import com...core.service.CommonRestController;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com...core.common.constant.PageConstant;
import com...core.common.exception.BusinessException;
import com...core.serialize.ResponseMsg;
import com...model.Shop;
import com...service.shop.service.ShopService;

import com...core.utils.ListUtil;
import com...core.utils.MapUtil;
import com...core.utils.StringUtil;


/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@RequestMapping("/admin/shop_rest/")
@RestController
public class AdminShopRestController extends CommonRestController<Shop,Long> implements InitializingBean
{

    @Resource
    private ShopService shopService;

        //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="idFirst")                            Long idFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy
        ,HttpServletResponse response,@RequestParam(required = false) Integer queryType
    ){
        Map<String,Object> query = new HashMap();
        query.put("idFirst",idFirst);
        Integer count = shopService.getModelListCount(query);
        if(StringUtil.isBlank(safeOrderBy)){
            query.put("notSafeOrderBy","id desc");
        }else{
            query.put("safeOrderBy",safeOrderBy);
        }
        if(queryType==null || queryType == QUERY_TYPE_SEARCH){//普通查询
            limit = Math.min(limit, PageConstant.MAX_LIMIT);
            query.put("start",(page - 1) * limit);query.put("limit",limit);
            return new ResponseMsg(count,shopService.getModelList(query));
        }else if(queryType == QUERY_TYPE_EXPORT_EXCEL){
            query.put("start",(page - 1) * limit);query.put("limit",limit);
            exportExcel(response,shopService.getModelList(query),"shop",
            new String[]{"店铺id","店铺名称","是否营业","开店日期","店铺电话","店铺邮箱","店铺地址","创建时间","更新时间"},
            new String[]{"","","","","","","","",""});
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = shopService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
