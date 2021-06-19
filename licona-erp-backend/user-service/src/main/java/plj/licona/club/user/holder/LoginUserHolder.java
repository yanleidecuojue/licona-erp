package plj.licona.club.user.holder;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import plj.licona.club.user.domain.LoginUserDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取登录用户信息
 *
 * @author licona
 */
@Component
public class LoginUserHolder {
    public LoginUserDTO getCurrentUser() {
        // 从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
        LoginUserDTO userDTO = new LoginUserDTO();
        userDTO.setUsername(userJsonObject.getStr("user_name"));
        userDTO.setId(userJsonObject.getStr("id"));
        userDTO.setRoles(Convert.toList(String.class, userJsonObject.get("authorities")));
        return userDTO;
    }
}
