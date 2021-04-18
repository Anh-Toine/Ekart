package com.nguyen.utils.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ServiceUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtil.class);
    private final String port;
    private String serviceAddress = null;

    public ServiceUtil(
            @Value("${server.port}") String port
    ) {
        this.port = port;
    }

    public String getServiceAddress(){
        return (serviceAddress != null) ? serviceAddress : getHostName() + "/" + getIpAddress() + ":" + port;
    }

    private String getHostName() {
        try{
            return InetAddress.getLocalHost().getHostName();
        }catch(UnknownHostException e){
            return "Can't find user's host name";
        }
    }

    private String getIpAddress() {
        try{
            return InetAddress.getLocalHost().getHostAddress();
        }catch(UnknownHostException e){
            return "Can't find user's IP address";
        }
    }
}
