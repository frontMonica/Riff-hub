package com.riffhub.config;
//
//import com.riffhub.interceptors.LoginInterceptors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private LoginInterceptors loginInterceptors;
//
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptors).excludePathPatterns("/user/login","/user/register");
//    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // 所有接口
//                .allowCredentials(true) // 是否发送 Cookie
//                .allowedOrigins("http://localhost:5173")// 支持域
//                .allowedMethods("GET", "POST", "PUT", "DELETE") // 支持方法
//                .allowedHeaders("*")
//                .exposedHeaders("*");
//    }
//
//}
//package com.lsu.server.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; /** * 处理跨域问题 * * @Author wang suo * @Date 2020/10/10 0010 21:37 * @Version 1.0 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file-save-path}")
    private String fileSavePath;
    @Override
    public void addCorsMappings(CorsRegistry registry) { /* * 一小时内不需要再预检 */
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowCredentials(true)
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .maxAge(3600);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+fileSavePath);
        //System.out.println("file:"+fileSavePath);
    }
}