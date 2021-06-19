package plj.licona.club.acl.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author licona
 */
@Data
@NoArgsConstructor
public class MenuDTO implements Serializable {
    private String id;
    private String pid;
    private String name;
    private List<MenuDTO> menuChildren;

    @Override
    public String toString() {
        return "{" + "id=" + id + ",pid=" + pid + ",name=" + name + ",menuChildren=" + menuChildren + "}";
    }
}
