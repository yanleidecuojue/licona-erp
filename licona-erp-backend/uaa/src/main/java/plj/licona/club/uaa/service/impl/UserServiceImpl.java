package plj.licona.club.uaa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plj.licona.club.uaa.entity.User;
import plj.licona.club.uaa.mapper.UserMapper;
import plj.licona.club.uaa.service.UserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author licona
 * @since 2021-05-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User findUserByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}