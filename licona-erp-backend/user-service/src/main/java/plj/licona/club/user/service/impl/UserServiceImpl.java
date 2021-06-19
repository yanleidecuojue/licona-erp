package plj.licona.club.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plj.licona.club.user.domain.UserDTO;
import plj.licona.club.user.entity.User;
import plj.licona.club.user.mapper.UserMapper;
import plj.licona.club.user.service.UserService;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author licona
 * @since 2021-05-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserDTO selectUserById(String id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUserAvatarPath(User user) {
        return baseMapper.updateById(user);
    }
}
