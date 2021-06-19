package plj.licona.club.uaa.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plj.licona.club.uaa.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author licona
 * @since 2021-05-07
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户id查询此用户对应的角色，保存在SecurityUser对象中
     *
     * @param userId 用户id
     * @return List<String>
     */
    List<String> findRolesByUserId(String userId);

    /**
     * 根据菜单id查询对应角色，保存在资源与角色对应关系类中，用于网关授权
     *
     * @param permissionId 权限id
     * @return List<String>
     */
    List<String> findRolesByPermissionId(String permissionId);
}
