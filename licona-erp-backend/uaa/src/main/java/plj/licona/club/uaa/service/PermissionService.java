package plj.licona.club.uaa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plj.licona.club.uaa.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author licona
 * @since 2021-05-07
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 查询菜单列表
     * @see plj.licona.club.uaa.mapper.PermissionMapper
     * @return List<AclPermission>
     */
    List<Permission> findAdminMenus();
}
