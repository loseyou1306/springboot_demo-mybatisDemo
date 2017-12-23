# springboot_demo-mybatisDemo

数据库使用mysql
application.properties中数据源非标准,自定义configuration加载配置:

mysql.datasource.url=jdbc:mysql://localhost:13306/test_proc
mysql.datasource.username=root
mysql.datasource.password=123456
mysql.datasource.driver-class-name=com.mysql.jdbc.Driver

#mybatis.typeAliasesPackage：指定domain类的基包，即指定其在*Mapper.xml文件中可以使用简名来代替全类名
mybatis.typeAliasesPackage=com.china.demo.domain
#mybatis.mapperLocations：指定*Mapper.xml的位置,如果不用自己写sql,没有mapper对应的xml文件,则不需要此设置
mybatis.mapperLocations=classpath:mapper/*.xml

#springboot使用10001端口进行访问
server.port=10001
server.session.timeout=1800
server.max-http-header-size=20971520




=======================================================
DruidConfiguration使用阿里巴巴的durid加载数据源


/**
 * 数据库连接池druid的配置
 * 
 * @author li
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
========================================
mapper支持注解和自定义sql:

@Mapper
public interface UserMapper {
	int create(User pt);
	 /**
     * 查所有
     *
     * @return
     */
    @Select("SELECT * FROM USER WHERE 1=1")
    List<User> findAll();
}
===================================================
自定义的mapper.xml文件:

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper  
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.china.demo.mapper.UserMapper">

	<insert id="create">
		insert user value(#{id},#{username},#{password});
	</insert>
	
</mapper>  


