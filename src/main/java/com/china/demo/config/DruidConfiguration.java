package com.china.demo.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据库连接池druid的配置
 * 
 * @author CatL
 * @date 2017年11月06日
 * @version 1.0
 */
@Configuration
public class DruidConfiguration {
	@Value("${mysql.datasource.url}")
	private String url;
	@Value("${mysql.datasource.username}")
	private String username;
	@Value("${mysql.datasource.password}")
	private String password;
	@Value("${mysql.datasource.driver-class-name}")
	private String dirverclass;
	@Value("${mybatis.typeAliasesPackage}")
	private String typeAliasesPackage;
	@Value("${mybatis.mapperLocations}")
	private String mapperLocations;

	/**
	 * 返回数据源datasource
	 * 
	 * @param datasourceUrl
	 *            数据库连接
	 * @return DataSource
	 */
	@Bean
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setDriverClassName(dirverclass);
		return druidDataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(druidDataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(druidDataSource());
		sessionFactory.setFailFast(true);
		sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		
		//下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		//指定基包
		sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
		//指定xml文件位置
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		
		return sessionFactory.getObject();
	}

}
