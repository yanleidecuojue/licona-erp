package plj.licona.club.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plj.licona.club.acl.entity.UserRole;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author licona
 * @since 2021-06-18
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    int linkUserRole(UserRole userRole);

}
