package com.cahodental.crm.admin.api.goodscategory;

import com.cahodental.crm.core.service.CommonRestController;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.cahodental.crm.core.common.constant.PageConstant;
import com.cahodental.crm.core.common.exception.BusinessException;
import com.cahodental.crm.core.serialize.ResponseMsg;
import com.cahodental.crm.model.GoodsCategory;
import com.cahodental.crm.service.goodscategory.service.GoodsCategoryService;

import com.cahodental.crm.core.utils.ListUtil;
import com.cahodental.crm.core.utils.MapUtil;
import com.cahodental.crm.core.utils.StringUtil;


/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@RequestMapping("/admin/goods_category_rest/")
@RestController
public class AdminGoodsCategoryRestController extends CommonRestController<GoodsCategory,Long> implements InitializingBean
{

    @Resource
    private GoodsCategoryService goodsCategoryService;

        @RequestMapping(value = "search")
    public ResponseMsg search(
        @RequestParam(required = false) String uniqueField,
        @RequestParam(required = false) Long uniqueValue,
        @RequestParam(required = false,defaultValue = "20") Integer limit,
        @RequestParam(required = false) String keyword
    ){
        limit = Math.min(PageConstant.MAX_LIMIT,limit);
        List<GoodsCategory> list = null;
        Map<String,Object> query = new HashMap();
        query.put("limit",limit);
        if(uniqueValue!=null){//说明是来初始化的
            query.put(uniqueField,uniqueValue);
            list = goodsCategoryService.getModelList(query);
        }else {//正常搜索
            if(ListUtil.isBlank(list)){
                query.put("nameFirst",keyword);
                list = goodsCategoryService.getModelList(query);
                query.remove("nameFirst");
            }
        }
        return new ResponseMsg(list);
    }
    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="idFirst")                            Long idFirst ,
        @RequestParam(required = false,value ="nameFirst")                            String nameFirst ,
        @RequestParam(required = false,value ="keywordFirst")                            String keywordFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy
        ,HttpServletResponse response,@RequestParam(required = false) Integer queryType
    ){
        Map<String,Object> query = new HashMap();
        query.put("idFirst",idFirst);
        query.put("nameFirst",coverBlankToNull(nameFirst));
        query.put("keywordFirst",coverBlankToNull(keywordFirst));
        Integer count = goodsCategoryService.getModelListCount(query);
        query.put("safeOrderBy",safeOrderBy);
        if(queryType==null || queryType == QUERY_TYPE_SEARCH){//普通查询
            limit = Math.min(limit, PageConstant.MAX_LIMIT);
            query.put("start",(page - 1) * limit);query.put("limit",limit);
            return new ResponseMsg(count,goodsCategoryService.getModelList(query));
        }else if(queryType == QUERY_TYPE_EXPORT_EXCEL){
            query.put("start",(page - 1) * limit);query.put("limit",limit);
            exportExcel(response,goodsCategoryService.getModelList(query),"商品规格分类1",
            new String[]{"主键","分类名称","分类另一个关键词"},
            new String[]{"","",""});
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = goodsCategoryService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
