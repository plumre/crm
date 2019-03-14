package com.cahodental.crm.admin.api.goods;

import com.cahodental.crm.core.service.CommonRestController;
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
import com.cahodental.crm.core.common.constant.PageConstant;
import com.cahodental.crm.core.common.exception.BusinessException;
import com.cahodental.crm.core.serialize.ResponseMsg;
import com.cahodental.crm.model.Goods;
import com.cahodental.crm.service.goods.service.GoodsService;

import com.cahodental.crm.core.utils.ListUtil;
import com.cahodental.crm.core.utils.MapUtil;
import com.cahodental.crm.core.utils.StringUtil;


/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@RequestMapping("/admin/goods_rest/")
@RestController
public class AdminGoodsRestController extends CommonRestController<Goods,Long> implements InitializingBean
{

    @Resource
    private GoodsService goodsService;

        @RequestMapping(value = "search")
    public ResponseMsg search(
        @RequestParam(required = false) String uniqueField,
        @RequestParam(required = false) Long uniqueValue,
        @RequestParam(required = false,defaultValue = "20") Integer limit,
        @RequestParam(required = false) String keyword
    ){
        limit = Math.min(PageConstant.MAX_LIMIT,limit);
        List<Goods> list = null;
        Map<String,Object> query = new HashMap();
        query.put("limit",limit);
        query.put("notSafeOrderBy","id desc");
        if(uniqueValue!=null){//说明是来初始化的
            query.put(uniqueField,uniqueValue);
            list = goodsService.getModelList(query);
        }else {//正常搜索
            if(ListUtil.isBlank(list)){
                query.put("goodsNameFirst",keyword);
                list = goodsService.getModelList(query);
                query.remove("goodsNameFirst");
            }
            if(ListUtil.isBlank(list)){
                query.put("imgSrcFirst",keyword);
                list = goodsService.getModelList(query);
                query.remove("imgSrcFirst");
            }
        }
        return new ResponseMsg(list);
    }
    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="idFirst")                            Long idFirst ,
        @RequestParam(required = false,value ="goodsNameFirst")                            String goodsNameFirst ,
        @RequestParam(required = false,value ="publishStatusFirst")                            String publishStatusFirst ,
        @RequestParam(required = false,value ="goodsStatusFirst")                            Byte goodsStatusFirst ,
        @RequestParam(required = false,value ="priceFirst")                            BigDecimal priceFirst ,
        @RequestParam(required = false,value ="storeCountFirst")                            Integer storeCountFirst ,
        @RequestParam(required = false,value ="createTimeFirst")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Timestamp createTimeFirst ,
        @RequestParam(required = false,value ="createTimeSecond")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Timestamp createTimeSecond ,
        @RequestParam(required = false,value ="updateTimeFirst")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Timestamp updateTimeFirst ,
        @RequestParam(required = false,value ="updateTimeSecond")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Timestamp updateTimeSecond ,
        @RequestParam(required = false,value ="imgSrcFirst")                            String imgSrcFirst ,
        @RequestParam(required = false,value ="goodsCategoryIdFirst")                            Long goodsCategoryIdFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy
        ,HttpServletResponse response,@RequestParam(required = false) Integer queryType
    ){
        Map<String,Object> query = new HashMap();
        query.put("idFirst",idFirst);
        query.put("goodsNameFirst",coverBlankToNull(goodsNameFirst));
        query.put("publishStatusFirst",coverBlankToNull(publishStatusFirst));
        query.put("goodsStatusFirst",goodsStatusFirst);
        query.put("priceFirst",priceFirst);
        query.put("storeCountFirst",storeCountFirst);
        query.put("createTimeFirst",createTimeFirst);
        query.put("createTimeSecond",createTimeSecond);
        query.put("updateTimeFirst",updateTimeFirst);
        query.put("updateTimeSecond",updateTimeSecond);
        query.put("imgSrcFirst",coverBlankToNull(imgSrcFirst));
        query.put("goodsCategoryIdFirst",goodsCategoryIdFirst);
        Integer count = goodsService.getModelListCount(query);
        if(StringUtil.isBlank(safeOrderBy)){
            query.put("notSafeOrderBy","id desc");
        }else{
            query.put("safeOrderBy",safeOrderBy);
        }
        if(queryType==null || queryType == QUERY_TYPE_SEARCH){//普通查询
            limit = Math.min(limit, PageConstant.MAX_LIMIT);
            query.put("start",(page - 1) * limit);query.put("limit",limit);
            return new ResponseMsg(count,goodsService.getModelList(query));
        }else if(queryType == QUERY_TYPE_EXPORT_EXCEL){
            query.put("start",(page - 1) * limit);query.put("limit",limit);
            exportExcel(response,goodsService.getModelList(query),"商品",
            new String[]{"ID","商品名","是否发布","商品状态","价格","库存","简介","商品描述","创建时间","更新时间","图片","所属类目"},
            new String[]{"","","[{\"value\":\"请选择\",\"key\":\"\"},{\"value\":\"未发表\",\"key\":\"0\"},{\"value\":\"已发布\",\"key\":\"1\"}]","[{\"value\":\"商品\",\"key\":\"0\"},{\"value\":\"货品\",\"key\":\"1\"}]","","","","","","","",""});
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = goodsService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
