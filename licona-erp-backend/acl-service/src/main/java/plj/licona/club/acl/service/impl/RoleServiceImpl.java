package plj.licona.club.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plj.licona.club.acl.entity.Role;
import plj.licona.club.acl.entity.RolePermission;
import plj.licona.club.acl.mapper.RoleMapper;
import plj.licona.club.acl.mapper.RolePermissionMapper;
import plj.licona.club.acl.service.PermissionService;
import plj.licona.club.acl.service.RolePermissionService;
import plj.licona.club.acl.service.RoleService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author licona
 * @since 2021-05-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    RoleMapper roleMapper;

    @Resource
    RolePermissionMapper rolePermissionMapper;

    @Resource
    RolePermissionService rolePermissionService;

    @Resource
    PermissionService permissionService;

    @Override
    public List<Role> findRoles() {
        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<Role> findRolesByUserId(String userId) {
        return roleMapper.findRolesByUserId(userId);
    }

    @Override
    public int addRole(Role role, List<String> routeNames) {
        int i = roleMapper.addRole(role);
        List<Integer> result = new ArrayList<>();
        result.add(i);
        for (int j = 0; j < routeNames.size(); j++) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setId(UUID.randomUUID().toString().replace("-", ""));
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permissionService.selectPermissionByPath(routeNames.get(j)).getId());
            int i1 = rolePermissionMapper.linkRolePermission(rolePermission);
            result.add(i1);
        }

        if (result.stream().filter(ans -> ans != 1).collect(Collectors.toList()).size() > 0) {
            return -1;
        } else return 1;
    }

    @Override
    public int updateRole(Role role, List<String> routeNames) {
        int i = roleMapper.updateRole(role);
        List<Integer> result = new ArrayList<>();
        result.add(i);
        // 删除role与permission之间的关系
        rolePermissionService.deleteByRoleId(role.getId());

        // 重新建立role与permission之间的关系
        for (int j = 0; j < routeNames.size(); j++) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setId(UUID.randomUUID().toString().replace("-", ""));
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permissionService.selectPermissionByPath(routeNames.get(j)).getId());
            int i1 = rolePermissionMapper.linkRolePermission(rolePermission);
            result.add(i1);
        }

        if (result.stream().filter(ans -> ans != 1).collect(Collectors.toList()).size() > 0) {
            return -1;
        } else return 1;
    }

    @Override
    public int deleteRole(String roleId, String roleName) {
        // 删除角色权限表中与此角色相关的记录
        rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", roleId));
        return baseMapper.delete(new QueryWrapper<Role>().eq("role_name", roleName));
    }

    @Override
    public Role selectRoleByName(String roleName) {
        return baseMapper.selectOne(new QueryWrapper<Role>().eq("role_name", roleName));
    }
}
