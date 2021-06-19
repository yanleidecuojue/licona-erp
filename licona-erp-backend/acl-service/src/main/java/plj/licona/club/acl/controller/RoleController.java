package plj.licona.club.acl.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import org.springframework.web.bind.annotation.*;
import plj.licona.club.acl.entity.Role;
import plj.licona.club.acl.response.ResultEnum;
import plj.licona.club.acl.response.ResultResponse;
import plj.licona.club.acl.service.RoleService;
import plj.licona.club.acl.utils.RouteUtil;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author licona
 * @since 2021-05-26
 */
@RestController
@RequestMapping("/acl/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/getRoles")
    public ResultResponse getRoles(@RequestParam(value = "user_id", required = false) String userId) {
        List<Role> roles = null;
        if(StrUtil.isEmpty(userId)) {
            roles = roleService.findRoles();
        } else {
            roles = roleService.findRolesByUserId(userId);
        }

        return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), roles);
    }

    @PostMapping("/addRole")
    public ResultResponse addRole(@RequestParam String roleName, @RequestParam String remark, @RequestParam String routes) {
        if(roleName.isEmpty() || remark.isEmpty()) {
            return new ResultResponse(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage());
        }

        List<String> routeNames = RouteUtil.getRouteNames(JSONArray.parseArray(routes));

        Role role = new Role();
        role.setId(UUID.randomUUID().toString().replace("-", ""));
        role.setRoleName(roleName);
        role.setRemark(remark);
        int ansState = roleService.addRole(role, routeNames);
        if (ansState >= 0) {
            return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), role.getId());
        } else {
            return new ResultResponse(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage());
        }
    }

    @PostMapping("/updateRole")
    public ResultResponse updateRole(@RequestParam("id") String roleId, @RequestParam("role_name") String roleName, @RequestParam String remark, @RequestParam String paths) {
        if(roleName.isEmpty() || remark.isEmpty()) {
            return new ResultResponse(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage());
        }

        List<String> pathList = Arrays.stream(paths.split(",")).collect(Collectors.toList());

        Role role = new Role();
        role.setId(roleId);
        role.setRoleName(roleName);
        role.setRemark(remark);
        int ansState = roleService.updateRole(role, pathList);
        if (ansState >= 0) {
            return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), role.getId());
        } else {
            return new ResultResponse(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage());
        }
    }


    @PostMapping("/deleteRole")
    public ResultResponse deleteRole(@RequestParam("role_id") String roleId, @RequestParam("role_name") String roleName) {
        if(roleName.isEmpty()) {
            return new ResultResponse(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage());
        }

        int i = roleService.deleteRole(roleId, roleName);
        if(i == 1) {
            return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
        }else {
            return new ResultResponse(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage());
        }
    }
}

