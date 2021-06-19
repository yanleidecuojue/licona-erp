package plj.licona.club.acl.service;

import plj.licona.club.acl.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author licona
 * @since 2021-06-17
 */
public interface RolePermissionService extends IService<RolePermission> {
    int deleteByRoleId(String roleId);
}
