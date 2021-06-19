package plj.licona.club.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plj.licona.club.acl.entity.User;
import plj.licona.club.acl.entity.UserRole;
import plj.licona.club.acl.mapper.UserMapper;
import plj.licona.club.acl.mapper.UserRoleMapper;
import plj.licona.club.acl.service.RoleService;
import plj.licona.club.acl.service.UserRoleService;
import plj.licona.club.acl.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Resource
    UserRoleMapper userRoleMapper;
    @Resource
    RoleService roleService;
    @Resource
    UserRoleService userRoleService;

    @Override
    public User selectPwdUserById(String id) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("id", id));
    }

    @Override
    public List<User> findUsers() {
        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public int deleteUserByUsername(String username) {
        return baseMapper.delete(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public int updateUser(User user, List<String> roleNames) {

        User user01 = baseMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        int i = userMapper.updateByUsername(user);
        List<Integer> result = new ArrayList<>();
        result.add(i);

        // 删除user与role之间的关系
        userRoleService.deleteByUserId(user01.getId());

        // 重新建立role与permission之间的关系
        for (int j = 0; j < roleNames.size(); j++) {
            UserRole userRole = new UserRole();
            userRole.setId(UUID.randomUUID().toString().replace("-", ""));
            userRole.setUserId(user01.getId());
            userRole.setRoleId(roleService.selectRoleByName(roleNames.get(j)).getId());
            int i1 = userRoleMapper.linkUserRole(userRole);
            result.add(i1);
        }

        if (result.stream().filter(ans -> ans != 1).collect(Collectors.toList()).size() > 0) {
            return -1;
        } else return 1;
    }
}
