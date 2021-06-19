package plj.licona.club.uaa.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import plj.licona.club.uaa.constant.RedisConstant;
import plj.licona.club.uaa.entity.Permission;
import plj.licona.club.uaa.service.PermissionService;
import plj.licona.club.uaa.service.RoleService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 资源与角色匹配关系管理业务类
 *
 * @author licona
 */
@Service
public class ResourceServiceImpl {
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void initData() {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        List<Permission> adminMenus = permissionService.findAdminMenus();
        adminMenus.forEach(item -> resourceRolesMap.put(item.getInterfaceUri(), roleService.findRolesByPermissionId(item.getId())));

        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
