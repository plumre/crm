package com.cahodental.crm.web.api.web;

import com.cahodental.crm.core.serialize.ResponseMsg;
import com.cahodental.crm.service.goods.service.GoodsService;
import com.cahodental.crm.web.webservice.goods.service.WebGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * author:www.magicalcoder.com
 * date:2018/8/23
 * function:
 */
@RestController
@RequestMapping("/web/goods/")
public class WebGoodsController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private WebGoodsService webGoodsService;


    @RequestMapping("info")
    public ResponseMsg info(){
        Jedis jedis;
        goodsService.getModel(1L);
        webGoodsService.getWebGoods(1L);
        return new ResponseMsg("ok");
    }

}
