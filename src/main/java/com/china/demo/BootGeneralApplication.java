package com.china.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
 * @Description: 微服务启动类 
 * @author liliang  
 * @date 2017年11月8日  
 * @version V1.0  
 *
 */
// SpringBoot 应用标识
@SpringBootApplication
public class BootGeneralApplication{

	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(BootGeneralApplication.class, args);
	}
}
