package plj.licona.club.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plj.licona.club.acl.domain.RouteDTO;
import plj.licona.club.acl.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author licona
 * @since 2021-05-25
 */
public interface PermissionService extends IService<Permission> {

    Permission selectPermissionByPath(String permissionPath);

    List<RouteDTO> findRoutes();

    List<Permission> findAllMenus();
}
