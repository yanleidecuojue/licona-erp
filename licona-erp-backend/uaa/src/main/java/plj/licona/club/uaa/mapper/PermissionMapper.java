package plj.licona.club.uaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plj.licona.club.uaa.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author licona
 * @since 2021-05-07
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 查询所有菜单
     * @return List<Permission>
     */
    List<Permission> findAdminMenus();
}
