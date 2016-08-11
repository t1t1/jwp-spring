package next.config;

import javax.sql.DataSource;

import next.aop.PerformanceAspect;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(
	basePackages = { "next.service", "next.dao", "next.aop" },
//	basePackages = { "next.service", "next.dao" },
//	includeFilters = @ComponentScan.Filter(value = {PerformanceAspect.class}, type = FilterType.ANNOTATION), // error
	excludeFilters = @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION)
)
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy(proxyTargetClass=true) // next.config.AppConfig에 @EnableAspectJAutoProxy 설정한다. Cglib 라이브러리를 사용하도록 설정해야 한다.
public class AppConfig {
	
	@Value("${db.driver}")
	private String DB_DRIVER;
	@Value("${db.url}")
	private String DB_URL;
	@Value("${db.username}")
	private String DB_USERNAME;
	@Value("${db.password}")
	private String DB_PW;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DB_DRIVER);
		ds.setUrl(DB_URL);
		ds.setUsername(DB_USERNAME);
		ds.setPassword(DB_PW);
//		ds.setDriverClassName(env.getProperty("db.driver"));
//		ds.setUrl(env.getProperty("db.url"));
//		ds.setUsername(env.getProperty("db.username"));
//		ds.setPassword(env.getProperty("db.password"));
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
