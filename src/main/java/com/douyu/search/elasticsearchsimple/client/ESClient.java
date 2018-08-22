package com.douyu.search.elasticsearchsimple.client;

import com.douyu.search.elasticsearchsimple.bean.ClientProperties;
import com.douyu.search.elasticsearchsimple.common.ESConstants;
import com.douyu.search.elasticsearchsimple.holder.SpringContextHolder;
import com.google.common.collect.Iterables;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author xialian
 * @Title: ESClient
 * @ProjectName elasticsearch-simple
 * @Description: es连接客户端
 * @date 2018/8/9 20:02
 */
public class ESClient {

    private Logger logger = LoggerFactory.getLogger(ESClient.class);

    private ClientProperties clientProperties;

    private TransportClient client;

    private ESClient() {
        clientProperties = SpringContextHolder.getBean("clientProperties",ClientProperties.class);
        client = new PreBuiltTransportClient(settings()).addTransportAddresses(transportAddress());
    }

    private TransportAddress[] transportAddress() {
        String hostAndPorts = clientProperties.getHostAndPorts();
        Assert.notNull(hostAndPorts, "hosts is null");
        String[] hostAndPortArray = hostAndPorts.split(",");
        List<TransportAddress> transportAddresses = new ArrayList<>(hostAndPortArray.length);
        for (String hostAndPort : hostAndPortArray) {
            if (checkHostAndPort(hostAndPort)) {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getByName(StringUtils.substringBefore(hostAndPort, ":"));
                } catch (UnknownHostException e) {
                    logger.warn("unknown host {}",hostAndPort,e);
                    continue;
                }
                transportAddresses
                    .add(new TransportAddress(inetAddress,
                        Integer.parseInt(StringUtils.substringAfter(hostAndPort, ":"))));
            }
        }
        if(CollectionUtils.isEmpty(transportAddresses)){
            throw new IllegalArgumentException("none of nodes are available");
        }
        return Iterables.toArray(transportAddresses, TransportAddress.class);
//        return transportAddresses.toArray(new TransportAddress[0]);
    }

    private boolean checkHostAndPort(String hostAndPort) {
        //TODO
        return true;
    }

    private Settings settings() {
        return Settings.builder()
            .put(ESConstants.CLIENT_TRANSPORT_PING_TIMEOUT, clientProperties.getClientTransportPingTimeout())
            .put(ESConstants.CLUSTER_NAME, clientProperties.getClusterName())
            .put(ESConstants.CLIENT_TRANSPORT_IGNORE_CLUSTER_NAME,
                clientProperties.isClientTransportIgnoreClusterName())
            .put(ESConstants.CLIENT_TRANSPORT_NODES_SAMPLER_INTERVAL,
                clientProperties.getClientTransportNodesSamplerInterval())
            .put(ESConstants.CLIENT_TRANSPORT_SNIFF, clientProperties.isClientTransportSniff())
            .build();
    }

    private static ESClient getInstance() {
        return ClientHolder.instance;
    }

    public static TransportClient client() {
        return getInstance().getClient();
    }

    private static class ClientHolder {
        private static ESClient instance = new ESClient();
    }

    private TransportClient getClient() {
        return this.client;
    }

}
