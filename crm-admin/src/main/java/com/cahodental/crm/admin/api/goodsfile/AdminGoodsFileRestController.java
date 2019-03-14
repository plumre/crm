package com.cahodental.crm.admin.api.goodsfile;

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
import com.cahodental.crm.model.GoodsFile;
import com.cahodental.crm.service.goodsfile.service.GoodsFileService;

import com.cahodental.crm.core.utils.ListUtil;
import com.cahodental.crm.core.utils.MapUtil;
import com.cahodental.crm.core.utils.StringUtil;


/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@RequestMapping("/admin/goods_file_rest/")
@RestController
public class AdminGoodsFileRestController extends CommonRestController<GoodsFile,Long> implements InitializingBean
{

    @Resource
    private GoodsFileService goodsFileService;

        //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="goodsIdFirst")                            Long goodsIdFirst ,
        @RequestParam(required = false,value ="fileSrcFirst")                            String fileSrcFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy
        ,HttpServletResponse response,@RequestParam(required = false) Integer queryType
    ){
        Map<String,Object> query = new HashMap();
        query.put("goodsIdFirst",goodsIdFirst);
        query.put("fileSrcFirst",coverBlankToNull(fileSrcFirst));
        Integer count = goodsFileService.getModelListCount(query);
        query.put("safeOrderBy",safeOrderBy);
        if(queryType==null || queryType == QUERY_TYPE_SEARCH){//普通查询
            limit = Math.min(limit, PageConstant.MAX_LIMIT);
            query.put("start",(page - 1) * limit);query.put("limit",limit);
            return new ResponseMsg(count,goodsFileService.getModelList(query));
        }else if(queryType == QUERY_TYPE_EXPORT_EXCEL){
            query.put("start",(page - 1) * limit);query.put("limit",limit);
            exportExcel(response,goodsFileService.getModelList(query),"商品附件",
            new String[]{"主键","所属商品","文件地址"},
            new String[]{"","",""});
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = goodsFileService;
        super.primaryKey = "fileId";//硬编码此实体的主键名称
    }
}
