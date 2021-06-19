package plj.licona.club.acl.utils;


import com.alibaba.fastjson.JSONArray;
import plj.licona.club.acl.domain.RouteDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author licona
 */
public class RouteUtil {
    public static List<String> paths = new ArrayList<>();

    public List<RouteDTO> routeIterate(String id, List<RouteDTO> routes) {
        List<RouteDTO> constructiveMenus = new ArrayList<>();
        routes.forEach(item -> {
            if (item.getPid().equals(id)) {
                constructiveMenus.add(item);
            }
        });

        // 把子菜单的子菜单再循环一遍
        for (RouteDTO routeDTO : constructiveMenus) {
            // 继续添加子元素
            routeDTO.setChildren(routeIterate(routeDTO.getId(), routes));
        }

        // 停下来的条件，如果 没有子元素了，则停下来
        if (constructiveMenus.size() == 0) {
            return null;
        }
        return constructiveMenus;
    }

    public static List<String> getRouteNames(JSONArray routes) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.getJSONObject(i).get("path") != null) {
                paths.add(routes.getJSONObject(i).get("path").toString());
                if (routes.getJSONObject(i).get("children") != null) {
                    getRouteNames(routes.getJSONObject(i).getJSONArray("children"));
                }
            }
        }

        List<String> collect = paths.stream().filter((String path) -> (!path.equals("/") && !path.equals("/dashboard"))).collect(Collectors.toList());
        return collect;
    }

    public static void main(String[] args) {
        String routes = "[{\"path\":\"/\",\"component\":{\"name\":\"Layout\",\"components\":{\"Navbar\":{\"components\":{\"Breadcrumb\":{\"watch\":{},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-b50ef614\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/components/Breadcrumb/index.vue\",\"_Ctor\":{}},\"Hamburger\":{\"name\":\"Hamburger\",\"props\":{\"isActive\":{\"default\":false}},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-4e6f274c\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/components/Hamburger/index.vue\",\"_Ctor\":{}}},\"computed\":{},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-d16d6306\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/Navbar.vue\",\"_Ctor\":{}},\"Sidebar\":{\"components\":{\"SidebarItem\":{\"name\":\"SidebarItem\",\"components\":{\"Item\":{\"name\":\"MenuItem\",\"functional\":true,\"props\":{\"icon\":{\"default\":\"\"},\"title\":{\"default\":\"\"}},\"_scopeId\":\"data-v-31ea41b3\",\"__file\":\"src/layout/components/Sidebar/Item.vue\",\"_Ctor\":{}},\"AppLink\":{\"props\":{\"to\":{\"required\":true}},\"computed\":{},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/Sidebar/Link.vue\",\"_Ctor\":{}}},\"mixins\":[{\"computed\":{},\"methods\":{}}],\"props\":{\"item\":{\"required\":true},\"isNest\":{\"default\":false},\"basePath\":{\"default\":\"\"}},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/Sidebar/SidebarItem.vue\",\"_Ctor\":{}},\"Logo\":{\"name\":\"SidebarLogo\",\"props\":{\"collapse\":{\"required\":true}},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-6494804b\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/Sidebar/Logo.vue\",\"_Ctor\":{}}},\"computed\":{},\"staticRenderFns\":[],\"_compiled\":true,\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/Sidebar/index.vue\",\"_Ctor\":{}},\"AppMain\":{\"name\":\"AppMain\",\"computed\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-078753dd\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/AppMain.vue\",\"_Ctor\":{}}},\"mixins\":[{\"watch\":{},\"methods\":{}}],\"computed\":{},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-13877386\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/index.vue\",\"_Ctor\":{}},\"redirect\":\"/dashboard\",\"children\":[{\"path\":\"/dashboard\",\"name\":\"Dashboard\",\"meta\":{\"title\":\"仪表盘\",\"icon\":\"dashboard\"}}]},{\"path\":\"/acl\",\"component\":{\"name\":\"Layout\",\"components\":{\"Navbar\":{\"components\":{\"Breadcrumb\":{\"watch\":{},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-b50ef614\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/components/Breadcrumb/index.vue\",\"_Ctor\":{}},\"Hamburger\":{\"name\":\"Hamburger\",\"props\":{\"isActive\":{\"default\":false}},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-4e6f274c\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/components/Hamburger/index.vue\",\"_Ctor\":{}}},\"computed\":{},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-d16d6306\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/Navbar.vue\",\"_Ctor\":{}},\"Sidebar\":{\"components\":{\"SidebarItem\":{\"name\":\"SidebarItem\",\"components\":{\"Item\":{\"name\":\"MenuItem\",\"functional\":true,\"props\":{\"icon\":{\"default\":\"\"},\"title\":{\"default\":\"\"}},\"_scopeId\":\"data-v-31ea41b3\",\"__file\":\"src/layout/components/Sidebar/Item.vue\",\"_Ctor\":{}},\"AppLink\":{\"props\":{\"to\":{\"required\":true}},\"computed\":{},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/Sidebar/Link.vue\",\"_Ctor\":{}}},\"mixins\":[{\"computed\":{},\"methods\":{}}],\"props\":{\"item\":{\"required\":true},\"isNest\":{\"default\":false},\"basePath\":{\"default\":\"\"}},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/Sidebar/SidebarItem.vue\",\"_Ctor\":{}},\"Logo\":{\"name\":\"SidebarLogo\",\"props\":{\"collapse\":{\"required\":true}},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-6494804b\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/Sidebar/Logo.vue\",\"_Ctor\":{}}},\"computed\":{},\"staticRenderFns\":[],\"_compiled\":true,\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/Sidebar/index.vue\",\"_Ctor\":{}},\"AppMain\":{\"name\":\"AppMain\",\"computed\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-078753dd\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/components/AppMain.vue\",\"_Ctor\":{}}},\"mixins\":[{\"watch\":{},\"methods\":{}}],\"computed\":{},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-13877386\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/layout/index.vue\",\"_Ctor\":{}},\"meta\":{\"title\":\"权限管理\",\"icon\":\"lock\",\"roles\":[\"admin\",\"useradmin\"]},\"children\":[{\"path\":\"/menu\",\"component\":{\"name\":\"Dashboard\",\"computed\":{},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-67112044\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/views/acl/menu.vue\"},\"meta\":{\"title\":\"菜单管理\",\"icon\":null,\"roles\":[\"null\"]}},{\"path\":\"/role\",\"component\":{\"computed\":{},\"methods\":{},\"staticRenderFns\":[],\"_compiled\":true,\"_scopeId\":\"data-v-3edf1116\",\"beforeCreate\":[null],\"beforeDestroy\":[null],\"__file\":\"src/views/acl/role.vue\",\"_Ctor\":{}},\"meta\":{\"title\":\"角色管理\",\"icon\":null,\"roles\":[\"null\"]}}]}]";
        List<String> routeNames = getRouteNames(JSONArray.parseArray(routes));
        for (int i = 0; i < routeNames.size(); i++) {
            System.out.println(routeNames.get(i) + " ");
        }
    }
}
