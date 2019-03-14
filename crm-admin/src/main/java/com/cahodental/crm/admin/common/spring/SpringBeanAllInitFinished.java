package com.cahodental.crm.admin.common.spring;

import com.cahodental.crm.core.cache.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Resource;

/**
 * Created by magicalcoder.com on
 * 当spring bean 启动完毕 就会执行此类方法
 */
@Component
@Slf4j
public class SpringBeanAllInitFinished implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent contextRefreshedEvent) {
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
       log.info("spring bean 加载完毕");
       try {
           redisUtil.get("test");
           log.info("------------start success--------------");
       }catch (JedisConnectionException e){
            e.printStackTrace();
            log.error("-------can not connect redis---------");
            log.error("无法连接redis,请确认redis已经启动并且application-xxx.yml中的配置信息正确");
       }
    }
}
