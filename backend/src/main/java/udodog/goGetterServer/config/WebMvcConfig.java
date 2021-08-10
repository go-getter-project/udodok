package udodog.goGetterServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import udodog.goGetterServer.controller.interceptor.AdmApiInterceptor;
import udodog.goGetterServer.controller.interceptor.BkUserApiInterceptor;
import udodog.goGetterServer.controller.interceptor.UserApiInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        registry.addInterceptor(bkUserApiInterceptor())
//                .addPathPatterns("/api/bkusers/**");
//
//        registry.addInterceptor(userApiInterceptor())
//                .addPathPatterns("/api/users/**");
//
//        registry.addInterceptor(admApiInterceptor())
//                .addPathPatterns("/api/admin/**");

    }

    @Bean
    public BkUserApiInterceptor bkUserApiInterceptor(){
        return new BkUserApiInterceptor();
    }

    @Bean
    public UserApiInterceptor userApiInterceptor(){
        return new UserApiInterceptor();
    }

    @Bean
    public AdmApiInterceptor admApiInterceptor(){
        return new AdmApiInterceptor();
    }
}
