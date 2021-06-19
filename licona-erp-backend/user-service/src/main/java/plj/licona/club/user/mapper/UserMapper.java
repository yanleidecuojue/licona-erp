package plj.licona.club.user.mapper;

import plj.licona.club.user.domain.UserDTO;
import plj.licona.club.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author licona
 * @since 2021-05-25
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询用户信息
     * @param id
     * @return List<UserDTO>
     */
    UserDTO selectUserById(String id);

    int insertUser(User user);
}
