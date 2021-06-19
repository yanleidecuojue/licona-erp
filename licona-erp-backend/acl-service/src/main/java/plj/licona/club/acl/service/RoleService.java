package plj.licona.club.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plj.licona.club.acl.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author licona
 * @since 2021-05-26
 */
public interface RoleService extends IService<Role> {
    /**
     * 查询所有角色信息
     *
     * @return List<Role>
     */
    List<Role> findRoles();
    /**
     * 查询所有角色信息
     *
     * @param userId
     * @return List<Role>
     */
    List<Role> findRolesByUserId(String userId);

    /**
     * 添加新角色
     *
     * @param role
     * @return int
     */
    int addRole(Role role, List<String> routeNames);

    /**
     * 更新角色信息
     *
     * @param role
     * @return int
     */
    int updateRole(Role role, List<String> routeNames);

    /**
     * 根据角色名称删除角色
     *
     * @param roleName
     * @return int
     */
    int deleteRole(String roleId, String roleName);

    Role selectRoleByName(String roleName);
}
