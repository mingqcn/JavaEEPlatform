package cn.edu.xmu.javaee.goodsdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 跨域访问配置
 * @author Ming Qiu
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        /**addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
         allowedMethods：允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等。
         allowedOrigins：允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，如：“http://www.aaa.com”，只有该域名可以访问我们的跨域资源。
         allowedHeaders：允许所有的请求header访问，可以自定义设置任意请求头信息。
         **/
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "HEAD")
                .maxAge(3600);
    }
}
