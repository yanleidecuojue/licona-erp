package plj.licona.club.acl.config;

import cn.hutool.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import plj.licona.club.acl.constant.RedisConstant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author licona
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    RedisTemplate<String,String> redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                JSONObject user =new JSONObject(request.getHeader("user"));
                Object secretId = user.get("secret_id");
                return secretId.equals(redisTemplate.opsForValue().get(RedisConstant.SECRET_ID + user.get("id")));
            }
        });
    }
}