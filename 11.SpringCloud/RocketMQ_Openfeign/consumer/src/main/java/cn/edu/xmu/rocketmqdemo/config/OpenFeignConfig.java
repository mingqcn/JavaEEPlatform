/**
 * Copyright School of Informatics Xiamen University
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package cn.edu.xmu.rocketmqdemo.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 将包头原样复制的OpenFeign的请求包头中
 *
 * @author mingqiu
 */
@Configuration
public class OpenFeignConfig{

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new OpenFeignHeaderInterceptor();
    }
}

class OpenFeignHeaderInterceptor implements RequestInterceptor {

    Logger log = LoggerFactory.getLogger(OpenFeignHeaderInterceptor.class);
    private static String LOGFORMAT = "%s: %s";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        if (request == null) {
            return;
        }

        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames == null) {
            return;
        }
        log.info(String.format(LOGFORMAT,"apply","feign interceptor....."));
        // 把请求过来的header请求头 原样设置到feign请求头中
        // 包括token
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            // 跳过 content-length,防止报错Feign报错feign.RetryableException: too many bytes written executing
            if (name.equals("Content-Length")) {
                log.debug(String.format(LOGFORMAT, "apply", "skip Content-Length"));
                continue;
            }
            this.addHeader(requestTemplate, name, request.getHeader(name));
        }
        this.addHeader(requestTemplate, "Content-Type", new String[]{"application/json;charset=UTF-8"});
    }

    private void addHeader(RequestTemplate requestTemplate, String name, String... values) {
        if (!requestTemplate.headers().containsKey(name)) {
            requestTemplate.header(name, values);
            log.debug(String.format(LOGFORMAT, "addHeader", "name = "+name+", values="+values));
        }
    }

}
