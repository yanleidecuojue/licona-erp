package plj.licona.club.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plj.licona.club.user.domain.UserDTO;
import plj.licona.club.user.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author licona
 * @since 2021-05-25
 */
public interface UserService extends IService<User> {
    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return User
     */
    UserDTO selectUserById(String id);

    /**
     * 往用户表插入记录
     *
     * @param user 用户实体
     * @return int
     */
    int insertUser(User user);

    /**
     * 修改用户头像
     *
     * @param user 用户实体
     * @return
     */
    int updateUserAvatarPath(User user);
}
