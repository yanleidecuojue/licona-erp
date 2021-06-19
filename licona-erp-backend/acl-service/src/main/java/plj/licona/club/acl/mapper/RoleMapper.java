package plj.licona.club.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plj.licona.club.acl.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author licona
 * @since 2021-05-26
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findRolesByUserId(String userId);

    int addRole(Role role);

    int updateRole(Role role);
}
