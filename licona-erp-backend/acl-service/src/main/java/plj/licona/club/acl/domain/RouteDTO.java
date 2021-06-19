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
public class RouteDTO implements Serializable {
    private String id;
    private String pid;
    private String path;
    private String component;
    private String name;
    /**
     * meta data
     */
    private String title;
    private String icon;
    private String roles;
    private List<RouteDTO> children;

    public RouteDTO(String id, String pid, String path, String component, String name, String title, String icon, String roles, List<RouteDTO> children) {
        this.id = id;
        this.pid = pid;
        this.path = path;
        this.component = component;
        this.name = name;
        this.title = title;
        this.icon = icon;
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "{" + "path=" + path + ",component=" + component + ",name=" + name + ",meta:{" + "title:" + title +
                ",icon:" + icon + ",roles:" + roles + "}" + ",children=" + children + "}";
    }
}
