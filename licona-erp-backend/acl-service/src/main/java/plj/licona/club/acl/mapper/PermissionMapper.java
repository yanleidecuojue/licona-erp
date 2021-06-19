package plj.licona.club.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plj.licona.club.acl.domain.RouteDTO;
import plj.licona.club.acl.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author licona
 * @since 2021-05-25
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<RouteDTO> findRoutes();

    List<Permission> findAllMenus();
}
