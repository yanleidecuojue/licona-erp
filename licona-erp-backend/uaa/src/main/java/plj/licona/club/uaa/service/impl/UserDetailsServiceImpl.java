package plj.licona.club.uaa.service.impl;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import plj.licona.club.uaa.constant.MessageConstant;
import plj.licona.club.uaa.constant.RedisConstant;
import plj.licona.club.uaa.domain.SecurityUser;
import plj.licona.club.uaa.entity.User;
import plj.licona.club.uaa.service.RoleService;
import plj.licona.club.uaa.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理业务类
 *
 * @author licona
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(username);
        if (StrUtil.isEmpty(user.getUsername())) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }

        List<String> rolesByUserId = roleService.findRolesByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(user, rolesByUserId);
        securityUser.setSecretId();
        redisTemplate.opsForValue().set(RedisConstant.SECRET_ID + user.getId(), securityUser.getSecretId());


        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }
}
