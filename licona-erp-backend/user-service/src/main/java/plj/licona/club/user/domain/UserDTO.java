package plj.licona.club.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author licona
 */
@Data
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private String nickname;
    private String avatar;
    private String roles;
}
