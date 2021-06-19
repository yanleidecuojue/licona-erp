### 基于分布式架构的ERP系统

#### 技术选型
##### 基础架构
SpringCloud SpringSecurity+JWT+OAuth2 
##### 数据库
MYSQL Redis 
##### 注册中心
Nacos
##### 网关
Gateway

#### Bugs

##### 1.在将认证移动到统一认证中心，将授权移动到网关服务中之后，出现了第三方可以绕过中间的认证授权服务直接访问网关后服务的问题。
对于此问题，我们在认证时候将secret_id返回给用户并将其保存在redis数据库中，一旦用户在通过网关服务后访问被其保护的微服务时，通过全局过滤器对服务进行过滤，拦截那些没有secret_id或者secret_id与存储不同的请求，防止第三方绕过网关直接访问服务内容。
由于我们的secret_id是保存在Authorization中的，因此在网关与用户之间传输时候并不会泄露secret_id。但是我们在网关向其他微服务转发之前会将Authorization中的信息解密，如此操作会不会有安全问题暂时未知
综合以上，我们决定通过uuid来生成secret_id

##### 2.为了解决前端的跨域问题，需要设置如下
```java
package plj.licona.club.gateway.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**

@author licona
*/
@Configuration
public class PreFlightCorsConfiguration {

@Bean
public CorsWebFilter corsFilter() {
    return new CorsWebFilter(corsConfigurationSource());
}

@Bean
CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
    config.addAllowedMethod(HttpMethod.PUT);
    config.addAllowedMethod(HttpMethod.DELETE);
    source.registerCorsConfiguration("/**", config);
    return source;
}
}
```

```yaml
spring:
  cloud:
    gateway:
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins: "*"
            allowedHeaders: "*"
            allowCredentials: true
            allowedMethods: "*"
      default-filters:
        - DedupeResponseHeader=Access-Control-Allow-Credentials Access-Control-Allow-Origin
```
##### 3.mybatis-plus update操作的问题
默认情况下，update操作返回的是记录的条数，需要在jdbc url后加入useAffectedRows=true

##### 4.elasticsearch无法启动问题
4.1.修改java堆内存

```shell
find / -name jvm.options
```