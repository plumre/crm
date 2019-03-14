package com...admin.api.user;

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
import com...model.User;
import com...service.user.service.UserService;

import com...core.utils.ListUtil;
import com...core.utils.MapUtil;
import com...core.utils.StringUtil;


/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@RequestMapping("/admin/user_rest/")
@RestController
public class AdminUserRestController extends CommonRestController<User,Long> implements InitializingBean
{

    @Resource
    private UserService userService;

        //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="idFirst")                            Long idFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy
        ,HttpServletResponse response,@RequestParam(required = false) Integer queryType
    ){
        Map<String,Object> query = new HashMap();
        query.put("idFirst",idFirst);
        Integer count = userService.getModelListCount(query);
        if(StringUtil.isBlank(safeOrderBy)){
            query.put("notSafeOrderBy","id desc");
        }else{
            query.put("safeOrderBy",safeOrderBy);
        }
        if(queryType==null || queryType == QUERY_TYPE_SEARCH){//普通查询
            limit = Math.min(limit, PageConstant.MAX_LIMIT);
            query.put("start",(page - 1) * limit);query.put("limit",limit);
            return new ResponseMsg(count,userService.getModelList(query));
        }else if(queryType == QUERY_TYPE_EXPORT_EXCEL){
            query.put("start",(page - 1) * limit);query.put("limit",limit);
            exportExcel(response,userService.getModelList(query),"user",
            new String[]{"用户id","姓名","年龄","性别","电话","邮箱","地址","用户类型：0客户1医生2护士3老板","店铺id","创建时间","修改时间"},
            new String[]{"","","","","","","","","","",""});
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = userService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
