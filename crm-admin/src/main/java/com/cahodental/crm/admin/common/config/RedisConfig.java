package com.cahodental.crm.admin.common.config;

import com.cahodental.crm.core.utils.StringUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * redis配置
 * 集群模式 支持1到多个redis服务
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.clusters.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.clusters.connectionTimeout}")
    private int connectionTimeout;

    @Value("${spring.redis.clusters.soTimeout}")
    private int soTimeout;

    @Value("${spring.redis.clusters.maxAttempts}")
    private int maxAttempts;

    //默认不开启集群模式 因为集群模式部署比较麻烦 但是支持
//    @Bean
   /* public JedisCluster jedisCluster(){
        Set<HostAndPort> jedisClusterNode = null;
        if(StringUtil.isNotBlank(clusterNodes)){
            jedisClusterNode = new HashSet<>();
            String[] arr = clusterNodes.split(",");
            for(String hostAndPot : arr){
                String[] hp = hostAndPot.split(":");
                jedisClusterNode.add(new HostAndPort(hp[0].trim(),Integer.valueOf(hp[1].trim())));
            }
        }else {
            return null;
        }
        if(StringUtil.isBlank(password)){
            return new JedisCluster(jedisClusterNode,connectionTimeout,soTimeout,maxAttempts,new GenericObjectPoolConfig());
        }else {
            return new JedisCluster(jedisClusterNode,connectionTimeout,soTimeout,maxAttempts,password,new GenericObjectPoolConfig());
        }
    }*/

//单机模式 默认开启 clusterNodes就是IP:PORT 只能配置1个
    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxWaitMillis(soTimeout);
        jedisPoolConfig.setMinIdle(0);
        JedisPool jedisPool;

        String[] arr = clusterNodes.split(":");
        if(StringUtil.isNotBlank(password)){
            jedisPool = new JedisPool(jedisPoolConfig, arr[0], Integer.valueOf(arr[1]), connectionTimeout, password);
        }else {
            jedisPool = new JedisPool(jedisPoolConfig, arr[0], Integer.valueOf(arr[1]), connectionTimeout);
        }
        return jedisPool;
    }

}
