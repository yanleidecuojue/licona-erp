package plj.licona.club.uaa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plj.licona.club.uaa.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author licona
 * @since 2021-05-07
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return User
     */
    User findUserByUsername(String username);
}
