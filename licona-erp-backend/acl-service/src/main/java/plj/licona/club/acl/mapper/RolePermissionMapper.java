package plj.licona.club.acl.mapper;

import plj.licona.club.acl.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @author licona
 * @since 2021-06-17
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    int linkRolePermission(RolePermission rolePermission);
}
