package plj.licona.club.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plj.licona.club.acl.entity.UserRole;
import plj.licona.club.acl.mapper.UserRoleMapper;
import plj.licona.club.acl.service.UserRoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author licona
 * @since 2021-06-18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public int deleteByUserId(String userId) {
        return baseMapper.delete(new QueryWrapper<UserRole>().eq("user_id", userId));
    }
}
