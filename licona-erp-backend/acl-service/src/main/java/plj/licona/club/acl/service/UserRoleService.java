package plj.licona.club.acl.service;

import plj.licona.club.acl.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author licona
 * @since 2021-06-18
 */
public interface UserRoleService extends IService<UserRole> {
    int deleteByUserId(String userId);
}
