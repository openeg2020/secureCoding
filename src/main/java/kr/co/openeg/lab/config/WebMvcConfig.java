package kr.co.openeg.lab.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

import kr.co.openeg.lab.common.interceptor.SessionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/img/**", "/js/**");// 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
    }
    
//    @Bean
//    public FilterRegistrationBean getFilterRegistrationBean(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new XssEscapeServletFilter());
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }



    
}
