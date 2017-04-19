package com.youedata.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@ImportResource(locations={"classpath:dubbo.xml"})
public class Application {
	public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
    	  ConfigurableApplicationContext run = SpringApplication.run(Application.class,args);

    	  
    	  
	}
}
