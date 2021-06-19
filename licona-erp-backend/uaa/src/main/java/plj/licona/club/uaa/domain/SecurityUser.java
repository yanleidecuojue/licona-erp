package plj.licona.club.uaa.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import plj.licona.club.uaa.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * 登录用户信息
 *
 * @author licona
 */
@Data
public class SecurityUser implements UserDetails {

    /**
     * ID
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Boolean isDeleted;
    /**
     * 用来进行网关后其他服务的授权验证
     */
    private String secretId;
    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser(User user, List<String> pwdRoleList) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        // 0
        this.setIsDeleted(user.getIsDeleted());
        if (pwdRoleList != null) {
            authorities = new ArrayList<>();
            pwdRoleList.forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
        }
    }

    public void setSecretId() {
        this.secretId = UUID.randomUUID().toString().replace("-", "");
    }

    public String getSecretId() {
        return secretId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.isDeleted;
    }

}
