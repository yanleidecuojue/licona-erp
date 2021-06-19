package plj.licona.club.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import plj.licona.club.acl.entity.RolePermission;
import plj.licona.club.acl.mapper.RolePermissionMapper;
import plj.licona.club.acl.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author licona
 * @since 2021-06-17
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
    @Override
    public int deleteByRoleId(String roleId) {
        return baseMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", roleId));
    }
}
