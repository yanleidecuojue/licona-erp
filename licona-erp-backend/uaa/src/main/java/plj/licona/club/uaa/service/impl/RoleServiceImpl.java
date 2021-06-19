package plj.licona.club.uaa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plj.licona.club.uaa.entity.Role;
import plj.licona.club.uaa.mapper.RoleMapper;
import plj.licona.club.uaa.service.RoleService;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author licona
 * @since 2021-05-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<String> findRolesByUserId(String userId) {
        return baseMapper.findRolesByUserId(userId);
    }

    @Override
    public List<String> findRolesByPermissionId(String permissionId) {
        return baseMapper.findRolesByPermissionId(permissionId);
    }
}