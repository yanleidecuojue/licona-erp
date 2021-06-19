package plj.licona.club.acl.controller;


import com.alibaba.fastjson.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plj.licona.club.acl.domain.RouteDTO;
import plj.licona.club.acl.entity.Permission;
import plj.licona.club.acl.response.ResultEnum;
import plj.licona.club.acl.response.ResultResponse;
import plj.licona.club.acl.service.PermissionService;
import plj.licona.club.acl.utils.RouteUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author licona
 * @since 2021-05-07
 */
@RestController
@RequestMapping(value = "/acl/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;
    @GetMapping("/routes")
    public ResultResponse routes() {
        List<RouteDTO> routeList = permissionService.findRoutes();
        RouteUtil routeIterator = new RouteUtil();
        JSONArray routeIteratedList = new JSONArray();
        routeIteratedList.addAll(routeIterator.routeIterate("0", routeList).get(0).getChildren());

        return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), routeIteratedList);
    }

    @GetMapping("/getMenus")
    public ResultResponse getMenus() {
        List<Permission> allMenus = permissionService.findAllMenus();
        return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), allMenus);
    }
}

