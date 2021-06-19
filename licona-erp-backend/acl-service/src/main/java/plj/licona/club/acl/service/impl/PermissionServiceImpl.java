package plj.licona.club.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plj.licona.club.acl.domain.RouteDTO;
import plj.licona.club.acl.entity.Permission;
import plj.licona.club.acl.mapper.PermissionMapper;
import plj.licona.club.acl.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author licona
 * @since 2021-05-25
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Resource
    PermissionMapper permissionMapper;

    @Override
    public Permission selectPermissionByPath(String permissionPath) {
        return baseMapper.selectOne(new QueryWrapper<Permission>().eq("path", permissionPath));
    }

    @Override
    public List<RouteDTO> findRoutes() {
        return permissionMapper.findRoutes();
    }

    @Override
    public List<Permission> findAllMenus() {
        return permissionMapper.findAllMenus();
    }
}
