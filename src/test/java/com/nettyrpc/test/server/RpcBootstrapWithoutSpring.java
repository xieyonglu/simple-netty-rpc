package com.nettyrpc.test.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nettyrpc.registry.ServiceRegistry;
import com.nettyrpc.server.RpcServer;
import com.nettyrpc.test.client.HelloService;
import com.nettyrpc.test.client.PersonService;

public class RpcBootstrapWithoutSpring {
	
    private static final Logger logger = LoggerFactory.getLogger(RpcBootstrapWithoutSpring.class);

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1:18866";
        ServiceRegistry serviceRegistry = new ServiceRegistry("127.0.0.1:2181");
        RpcServer rpcServer = new RpcServer(serverAddress, serviceRegistry);
        
        HelloService helloService = new HelloServiceImpl();
        rpcServer.addService("com.nettyrpc.test.client.HelloService", helloService);
        
        PersonService personService = new PersonServiceImpl();
        rpcServer.addService("com.nettyrpc.test.client.PersonService", personService);
        
        try {
            rpcServer.start();
        } catch (Exception ex) {
            logger.error("Exception: {}", ex);
        }
    }
    
}
