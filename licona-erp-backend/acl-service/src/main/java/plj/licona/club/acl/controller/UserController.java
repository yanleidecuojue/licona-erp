package plj.licona.club.acl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plj.licona.club.acl.entity.User;
import plj.licona.club.acl.response.ResultEnum;
import plj.licona.club.acl.response.ResultResponse;
import plj.licona.club.acl.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author licona
 * @since 2021-06-17
 */
@RestController
@RequestMapping("/acl/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUsers")
    public ResultResponse getRoles() {
        List<User> users = userService.findUsers();
        return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), users);
    }

    @PostMapping("/deleteUser")
    public ResultResponse deleteUser(@RequestParam("username") String username) {
        int i = userService.deleteUserByUsername(username);
        if (i == 1) {
            return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
        }
        return new ResultResponse(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage(), null);
    }

    @PostMapping("/updateUser")
    public ResultResponse updateUser(@RequestParam("username") String username, @RequestParam("nick_name") String nickName,
                                     @RequestParam("avatar") String avatar, @RequestParam("email") String email,
                                     @RequestParam("phone_number") String phoneNumber, @RequestParam("roles") String roles) {
        User user = new User();
        user.setUsername(username);
        user.setNickName(nickName);
        user.setAvatar(avatar);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        List<String> roleNames = Arrays.stream(roles.split(",")).collect(Collectors.toList());
        userService.updateUser(user, roleNames);
        return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }
}

