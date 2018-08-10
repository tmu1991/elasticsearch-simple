package com.douyu.search.elasticsearchsimple.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xialian
 * @Title: ClientProperties
 * @ProjectName elasticsearch-simple
 * @Description: TODO
 * @date 2018/8/9 20:29
 */

@Getter
@Setter
public class ClientProperties {

    private String hostAndPorts;
    private int port;
    private String clientTransportPingTimeout="5S";
    private String clusterName;
    private String clientTransportNodesSamplerInterval="5S";
    private boolean clientTransportIgnoreClusterName;
    private boolean clientTransportSniff;


}
