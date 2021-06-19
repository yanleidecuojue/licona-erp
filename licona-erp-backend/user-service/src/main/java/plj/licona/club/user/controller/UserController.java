package plj.licona.club.user.controller;

import cn.hutool.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import plj.licona.club.user.domain.UserDTO;
import plj.licona.club.user.entity.User;
import plj.licona.club.user.holder.LoginUserHolder;
import plj.licona.club.user.response.ResultEnum;
import plj.licona.club.user.response.ResultResponse;
import plj.licona.club.user.service.UserService;
import plj.licona.club.user.utils.UIDRandom;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author licona
 * @since 2021-04-17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    LoginUserHolder loginUserHolder;

    @GetMapping("/info")
    public ResultResponse info() {
        UserDTO userDTO = userService.selectUserById(loginUserHolder.getCurrentUser().getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("id", userDTO.getId());
        jsonObject.set("name", userDTO.getName());
        jsonObject.set("nickname", userDTO.getNickname());
        jsonObject.set("avatar", userDTO.getAvatar());
        jsonObject.set("roles", userDTO.getRoles().split(","));
        return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), jsonObject);
    }

    @PostMapping("/register")
    public ResultResponse register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
                                   @RequestParam(value = "nick_name", required = false) String nickName, @RequestParam(value = "avatar", required = false) String avatar,
                                   @RequestParam(value = "email", required = false) String email, @RequestParam(value = "phone_number", required = false) String phoneNumber) {
        User user = new User();

        user.setId(UIDRandom.getRandomCode(8));
        user.setUsername(username);
        user.setPassword(password);
        user.setNickName(nickName);
        user.setAvatar(avatar);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        int i = userService.insertUser(user);
        if (i == 1) {
            return new ResultResponse(ResultEnum.SUCCESS.getCode(), "注册成功", null);
        }
        return new ResultResponse(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage(), null);
    }

    @PostMapping("/logout")
    public ResultResponse logout() {
        return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 更换用户头像，前端将图片保存在图片服务器并调用此接口将修改用户的图片路径
     *
     * @param avatarPath 头像路径
     * @return ResultPath
     */
    @PostMapping("/modify-avatar")
    public ResultResponse modifyAvatar(@RequestParam(value = "avatar_path") String avatarPath) {
        User user = new User();
        user.setId(String.valueOf(loginUserHolder.getCurrentUser().getId()));
        user.setAvatar(avatarPath);
        int updateAvatarValue = userService.updateUserAvatarPath(user);
        if (updateAvatarValue == 1) {
            return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
        }
        return new ResultResponse(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage(), null);
    }
}





