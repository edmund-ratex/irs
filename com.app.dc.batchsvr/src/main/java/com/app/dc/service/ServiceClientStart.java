package com.app.dc.service;

import com.app.dc.utils.MDHistSvrClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author
 * @Date
 **/
@Slf4j
@Component
public class ServiceClientStart implements CommandLineRunner {
    @Autowired
    private MDHistSvrClient mdHistSvrClient;


    @Override
    public void run(String... args) throws Exception {

        log.info("ServiceClientStart run");

        mdHistSvrClient.init();

        log.info("ServiceClientStart start end");
    }
}
