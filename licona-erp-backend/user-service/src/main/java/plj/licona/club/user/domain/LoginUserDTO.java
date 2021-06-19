package plj.licona.club.user.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author licona
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginUserDTO {
    private String id;
    private String username;
    private List<String> roles;
}
