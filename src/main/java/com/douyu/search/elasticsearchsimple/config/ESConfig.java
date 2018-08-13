package com.douyu.search.elasticsearchsimple.config;

import com.douyu.ocean.config.client.PropertiesConfigClient;
import com.douyu.search.elasticsearchsimple.bean.ClientProperties;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xialian
 * @Title: ESConfig
 * @ProjectName elasticsearch-simple
 * @Description: es配置类
 * @date 2018/8/9 20:07
 */
@Configuration
public class ESConfig {

    private static final String HOST_AND_PORT ="140.143.233.86:9300";
    private static final String CLUSTER_NAME ="my-application";

    private PropertiesConfigClient configClient;

//    @PostConstruct
    public void init() {
        configClient = new PropertiesConfigClient("ocean", "ocean-search-platform", "ocean-common");
    }

    @Bean(name = "clientProperties")
    public ClientProperties clientProperties(){
        return getClientProperties(HOST_AND_PORT, CLUSTER_NAME);
    }

    private ClientProperties getClientProperties(String hostAndPort,String clusterName){
        ClientProperties clientProperties=new ClientProperties();
        clientProperties.setHostAndPorts(hostAndPort);
        clientProperties.setClusterName(clusterName);
        clientProperties.setClientTransportPingTimeout("30S");
        return clientProperties;
    }

}
