package com.douyu.search.elasticsearchsimple.common;

/**
 * @author xialian
 * @Title: ESConstants
 * @ProjectName elasticsearch-simple
 * @Description: 常量
 * @date 2018/8/9 20:08
 */
public interface ESConstants {

    /**
     * 等待节点ping响应的时间 默认5S
     */
    String CLIENT_TRANSPORT_PING_TIMEOUT ="client.transport.ping_timeout";
    /**
     * 集群名
     */
    String CLUSTER_NAME ="cluster.name";
    /**
     * 节点ping的时间间隔 默认5S
     */
    String CLIENT_TRANSPORT_NODES_SAMPLER_INTERVAL ="client.transport.nodes_sampler_interval";
    /**
     * 设置为true时忽略节点的集群名检验
     */
    String CLIENT_TRANSPORT_IGNORE_CLUSTER_NAME ="client.transport.ignore_cluster_name";
    /**
     * 设置为true 开启嗅探
     */
    String CLIENT_TRANSPORT_SNIFF ="client.transport.sniff";
}
