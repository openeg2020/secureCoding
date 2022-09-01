package kr.co.openeg.lab.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	@Autowired
	JDBCConfig jdbcgonfig;

	@Bean
	@Primary
	public DataSource customDataSource() {
		return DataSourceBuilder.create().url(jdbcgonfig.getUrl()).driverClassName(jdbcgonfig.getDriverClassName())
				.username(jdbcgonfig.getUsername()).password(jdbcgonfig.getPassword()).build();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource customDataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(customDataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setConfigLocation(resolver.getResource("classpath:mybatis/myBatisConfig.xml"));
		sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mappers/*.xml"));
		return sessionFactory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}

}
