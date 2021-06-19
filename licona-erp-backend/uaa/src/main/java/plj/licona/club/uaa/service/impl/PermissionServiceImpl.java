package plj.licona.club.uaa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plj.licona.club.uaa.entity.Permission;
import plj.licona.club.uaa.mapper.PermissionMapper;
import plj.licona.club.uaa.service.PermissionService;

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
    @Override
    public List<Permission> findAdminMenus() {
        return baseMapper.findAdminMenus();
    }
}
