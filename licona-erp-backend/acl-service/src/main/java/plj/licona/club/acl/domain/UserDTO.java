package plj.licona.club.acl.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author licona
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private List<String> roles;
}
