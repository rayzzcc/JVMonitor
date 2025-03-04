package com.ray.jvm.controller;

import com.ray.jvm.common.response.Response;
import com.ray.jvm.common.response.ResponseHelper;
import com.ray.jvm.core.model.Peer;
import com.ray.jvm.core.model.ServiceDescriptor;
import com.ray.jvm.core.util.MBeanServerConnectionUtil;
import com.ray.jvm.service.IJmsService;
import com.ray.jvm.vo.reponse.Response4monitorVO;
import com.ray.jvm.vo.reponse.Response4overviewVO;
import com.ray.jvm.vo.reponse.Response4threadVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * jms请求接口
 * @author songxulin
 */
@RestController
public class JmsController {
    private static final Logger logger = LoggerFactory.getLogger(JmsController.class);

    @Autowired
    private IJmsService jmsService;
    /**
     * 获取已经连接的节点
     * @return
     */
    @GetMapping("/jms/getPeerList")
    public Response getPeerList() {
        Set<Peer> peerSet = MBeanServerConnectionUtil.getPeerSet();
        logger.info("peerSet:{}",peerSet);
        return ResponseHelper.buildOk(peerSet);
    }
    /**
     * 获取jmx连接
     * @author: songxulin
     * @date :  2020-05-25 20:12
     */
    @GetMapping("/jms/getConnection")
    public Response getConnection(Peer peer) {
        logger.info("getConnection{}:{}",peer.getHost(),peer.getPort());
        try {
            ServiceDescriptor descriptor = MBeanServerConnectionUtil.getMBeanServerConnection(peer);
            String connectionId = descriptor.getJmxConnector().getConnectionId();
            return ResponseHelper.buildOk(connectionId);
        } catch (Exception e) {
            logger.error("获取连接失败",e);
            return ResponseHelper.buildFail(e.getMessage());
        }
    }

    /**
     * 获取jvm概述信息
     * @param peer
     * @return Response
     */
    @GetMapping("/jms/getOverview")
    public Response getOverview(Peer peer) {
        logger.info("getOverview{}:{}",peer.getHost(),peer.getPort());
        try {
            ServiceDescriptor descriptor = MBeanServerConnectionUtil.getMBeanServerConnection(peer);
            Response4overviewVO response4OverviewVO= jmsService.getOverview(descriptor);
            logger.info("overview:{}",response4OverviewVO);
            return ResponseHelper.buildOk(response4OverviewVO);
        } catch (Exception e) {
            logger.error("获取连接失败",e);
            return ResponseHelper.buildFail(e.getMessage());
        }
    }
    /**
     * 获取监控数据
     * @author: songxulin
     * @date :  2020-05-28 16:05
     */
    @GetMapping("/jms/getMonitor")
    public Response getMonitor(Peer peer) {
        logger.info("getMonitor{}:{}",peer.getHost(),peer.getPort());
        try {
            ServiceDescriptor descriptor = MBeanServerConnectionUtil.getMBeanServerConnection(peer);
            Response4monitorVO monitor = jmsService.getMonitor(descriptor);
            logger.info("monitor:{}",monitor);
            return ResponseHelper.buildOk(monitor);
        } catch (Exception e) {
            logger.error("获取连接失败",e);
            return ResponseHelper.buildFail(e.getMessage());
        }
    }

    /**
     * 获取线程数据
     * @author: songxulin
     * @date :  2020-05-28 16:05
     */
    @GetMapping("/jms/getThreadAll")
    public Response getThreadAll(Peer peer) {
        logger.info("getThreadAll{}:{}",peer.getHost(),peer.getPort());
        try {
            ServiceDescriptor descriptor = MBeanServerConnectionUtil.getMBeanServerConnection(peer);
            Response4threadVO response = jmsService.getThreadAll(descriptor);
            return ResponseHelper.buildOk(response);
        } catch (Exception e) {
            logger.error("获取连接失败",e);
            return ResponseHelper.buildFail(e.getMessage());
        }
    }

}
