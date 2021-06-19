package plj.licona.club.uaa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plj.licona.club.uaa.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author licona
 * @since 2021-05-07
 */
public interface RoleService extends IService<Role> {
    /**
     * 查询用户对应角色列表
     *
     * @param userId 用户id
     * @return List<String>
     * @see plj.licona.club.uaa.mapper.RoleMapper
     */
    List<String> findRolesByUserId(String userId);

    /**
     * 查询权限对应角色列表
     *
     * @param permissionId 菜单id
     * @return List<String>
     * @see plj.licona.club.uaa.mapper.RoleMapper
     */
    List<String> findRolesByPermissionId(String permissionId);
}
