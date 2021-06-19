package plj.licona.club.acl.mapper;

import plj.licona.club.acl.entity.User;
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
    int updateByUsername(User user);;
}
