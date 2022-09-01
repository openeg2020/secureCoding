package kr.co.openeg.lab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ @PropertySource(value = "classpath:dbconn.properties", ignoreResourceNotFound = true)
// MSSQL 사용시 주석 해제
// @PropertySource( value = "classpath:dbconnMS.properties",
// ignoreResourceNotFound = true )
})

public class JDBCConfig {
	@Value("${jdbc.driver}")
	private String driverClassName;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	public String getDriverClassName() {
		return driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
