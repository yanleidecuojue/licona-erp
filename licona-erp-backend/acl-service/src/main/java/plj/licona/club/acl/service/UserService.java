package plj.licona.club.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plj.licona.club.acl.entity.User;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author licona
 * @since 2021-05-25
 */
public interface UserService extends IService<User> {
    User selectPwdUserById(String id);

    List<User> findUsers();

    int deleteUserByUsername(String username);

    int updateUser(User user, List<String> roleNames);
}
