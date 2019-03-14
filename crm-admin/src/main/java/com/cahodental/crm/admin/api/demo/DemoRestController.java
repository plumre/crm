package com.cahodental.crm.admin.api.demo;

import com.cahodental.crm.admin.adminservice.demo.service.AdminServiceDemoService;
import com.cahodental.crm.core.serialize.ResponseMsg;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/admin/demo_rest/")
@RestController
public class DemoRestController  {
    @Resource
    private AdminServiceDemoService adminServiceDemoService;

    @RequestMapping(value = "/demo_goods/{id}")
    public ResponseMsg demoGoods(@PathVariable Long id){
        return new ResponseMsg(adminServiceDemoService.demoGoods(id));
    }

}
