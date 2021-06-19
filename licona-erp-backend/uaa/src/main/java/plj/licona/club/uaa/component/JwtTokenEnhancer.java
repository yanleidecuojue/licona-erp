package plj.licona.club.uaa.component;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import plj.licona.club.uaa.domain.SecurityUser;

import java.util.HashMap;
import java.util.Map;


/**
 * jwt内容增强器
 *
 * @author licona
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>(1);
        // 把用户ID设置到JWT中
        info.put("id", securityUser.getId());
        // 将secret_id设置到JWT中
        info.put("secret_id", securityUser.getSecretId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
