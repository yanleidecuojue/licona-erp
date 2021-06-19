import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;


/**
 * @author licona
 */
public class JsonLoop {

    public static String json = "{\n" +
            "\t\t\t\"id\": \"adc777f9666e4852b4a2681c84638f6e\",\n" +
            "\t\t\t\"pid\": \"1\",\n" +
            "\t\t\t\"path\": \"/acl\",\n" +
            "\t\t\t\"component\": \"layout/Layout\",\n" +
            "\t\t\t\"name\": \"权限管理\",\n" +
            "\t\t\t\"title\": \"权限管理\",\n" +
            "\t\t\t\"icon\": \"lock\",\n" +
            "\t\t\t\"roles\": \"admin,useradmin\",\n" +
            "\t\t\t\"children\": [\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"id\": \"543aca77fe77493a865516a6b1e35fef\",\n" +
            "\t\t\t\t\t\"pid\": \"adc777f9666e4852b4a2681c84638f6e\",\n" +
            "\t\t\t\t\t\"path\": \"menu\",\n" +
            "\t\t\t\t\t\"component\": \"views/acl/menu\",\n" +
            "\t\t\t\t\t\"name\": \"菜单管理\",\n" +
            "\t\t\t\t\t\"title\": \"菜单管理\",\n" +
            "\t\t\t\t\t\"icon\": null,\n" +
            "\t\t\t\t\t\"roles\": null,\n" +
            "\t\t\t\t\t\"children\": null\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"id\": \"82160b8cbe3c4ce2bb32137ecc475536\",\n" +
            "\t\t\t\t\t\"pid\": \"adc777f9666e4852b4a2681c84638f6e\",\n" +
            "\t\t\t\t\t\"path\": \"user\",\n" +
            "\t\t\t\t\t\"component\": \"views/acl/user\",\n" +
            "\t\t\t\t\t\"name\": \"用户管理\",\n" +
            "\t\t\t\t\t\"title\": \"用户管理\",\n" +
            "\t\t\t\t\t\"icon\": null,\n" +
            "\t\t\t\t\t\"roles\": \"useradmin\",\n" +
            "\t\t\t\t\t\"children\": null\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"id\": \"fe627631f3214d0286a2d58c4cd9334e\",\n" +
            "\t\t\t\t\t\"pid\": \"adc777f9666e4852b4a2681c84638f6e\",\n" +
            "\t\t\t\t\t\"path\": \"role\",\n" +
            "\t\t\t\t\t\"component\": \"views/acl/role\",\n" +
            "\t\t\t\t\t\"name\": \"角色管理\",\n" +
            "\t\t\t\t\t\"title\": \"角色管理\",\n" +
            "\t\t\t\t\t\"icon\": null,\n" +
            "\t\t\t\t\t\"roles\": null,\n" +
            "\t\t\t\t\t\"children\": null\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t}";

    public static void jsonLoop(Object object) {

        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                Object o = entry.getValue();
                if (o instanceof String) {
                    System.out.println("key:" + entry.getKey() + "，value:" + entry.getValue());
                } else {
                    jsonLoop(o);
                }
            }
        }
        if (object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonLoop(jsonArray.get(i));
            }
        }
    }

    public static void main(String[] args) {

        JSONObject jsonObject = JSON.parseObject(json);
        jsonLoop(jsonObject);
    }
}
