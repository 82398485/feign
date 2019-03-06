package com.songcj.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * EurekaServerEventListener
 * （EurekaEventListener已使用故增加Server区分）
 */
@Component
@EnableEurekaClient
public class EurekaServerEventListener {

    private static final Logger log = LoggerFactory.getLogger(EurekaServerEventListener.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Value("${server.port}")
    private String eurekaPort;
    @Value("${spring.application.name}")
    private String eurekaServiceName;
    @Value("${spring.cloud.client.ip-address}")
    private String eurekaIp;

    /**
     * 服务下线事件
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        if(event!=null){
            Object[] params = new Object[7];
            //客户端端信息
            params[0] = (event.getAppName()==null?"":event.getAppName());
            params[1] = (event.getServerId()==null?"":event.getServerId());
            params[2] = simpleDateFormat.format(new Date(event.getTimestamp()));
            //同步或非同步
            params[3] = (event.isReplication() ? "同步" : "");
            //服务端信息
            params[4] = this.eurekaServiceName;
            params[5] = this.eurekaIp;
            params[6] = this.eurekaPort;
            //其他信息待增加
            log.info("appName:[{}] serverId:[{}] time:[{}] --[{}]下线--> eurekaServiceName:[{}] eurekaIp:[{}] eurekaPort:[{}]",params);
        }else{
            log.info("EurekaInstanceCanceledEvent event = null");
        }
    }

    /**
     * 服务注册事件
     * @param event
     */
    @EventListener  
    public void listen(EurekaInstanceRegisteredEvent event) {
        if(event!=null&&event.getInstanceInfo()!=null){
            Object[] params = new Object[7];
            //客户端端信息
            params[0] = (event.getInstanceInfo().getAppName()==null?"":event.getInstanceInfo().getAppName());
            params[1] = (event.getInstanceInfo().getIPAddr()==null?"":event.getInstanceInfo().getIPAddr());
            params[2] = event.getInstanceInfo().getPort();
            //同步或非同步
            params[3] = (event.isReplication() ? "同步" : "");
            //服务端信息
            params[4] = this.eurekaServiceName;
            params[5] = this.eurekaIp;
            params[6] = this.eurekaPort;
            //其他信息待增加
            log.info("appName:[{}] IPAddr:[{}] port:[{}] --[{}]注册--> eurekaServiceName:[{}] eurekaIp:[{}] eurekaPort:[{}]",params);
        }else{
            if (log.isInfoEnabled()) {
                if (event == null) {
                    log.info("event is null");
                } else {
                    log.info(event.toString());
                }
            }
        }
    }

    /**
     * 服务续约事件
     * @param event
     */
    @EventListener  
    public void listen(EurekaInstanceRenewedEvent event) {
        if(event!=null&&event.getInstanceInfo()!=null) {
            Object[] params = new Object[7];
            //客户端端信息
            params[0] = (event.getInstanceInfo().getAppName() == null ? "" : event.getInstanceInfo().getAppName());
            params[1] = (event.getInstanceInfo().getIPAddr() == null ? "" : event.getInstanceInfo().getIPAddr());
            params[2] = event.getInstanceInfo().getPort();
            //同步或非同步
            params[3] = (event.isReplication() ? "同步" : "");
            //服务端信息
            params[4] = this.eurekaServiceName;
            params[5] = this.eurekaIp;
            params[6] = this.eurekaPort;
            //其他信息待增加
            log.info("appName:[{}] IPAddr:[{}] port:[{}] --[{}]续约--> eurekaServiceName:[{}] eurekaIp:[{}] eurekaPort:[{}]", params);
        }else{
            if (log.isInfoEnabled()) {
                if (event == null) {
                    log.info("event is null");
                } else {
                    log.info(event.toString());
                }
            }
        }
    }

    /**
     * Eureka 注册中心启动事件
     * @param event
     */
    @EventListener  
    public void listen(EurekaRegistryAvailableEvent event) {
        if (log.isInfoEnabled()) {
            log.info("Eureka注册中心启动事件{0}", event.toString()); //event只有一个时间戳
        }
    }

    /**
     * Eureka Server启动事件
     * @param event
     */
    @EventListener  
    public void listen(EurekaServerStartedEvent event) {
        if (log.isInfoEnabled()) {
            log.info("Eureka Server启动事件{0}", event.toString());//event只有一个时间戳
        }
    }

}  