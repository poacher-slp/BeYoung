package per.poacher.beyoungmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import per.poacher.beyoungmall.controller.ex.*;
import per.poacher.beyoungmall.pojo.User;
import per.poacher.beyoungmall.service.UserService;
import per.poacher.beyoungmall.service.ex.InsertException;
import per.poacher.beyoungmall.service.ex.UsernameDuplicatedException;
import per.poacher.beyoungmall.util.Result;
import per.poacher.beyoungmall.util.UploadUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author poacher
 * @create 2022-05-04-2:37
 */
@RequestMapping("users")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("reg")
    public Result reg(User user) {
        userService.reg(user);
        return new Result<>(OK);
    }

    @RequestMapping("login")
    public Result<User> login(String username, String password, Integer role, HttpSession session) {
        User user = userService.login(username, password, role);
        session.setAttribute("uid", user.getUserId());
        session.setAttribute("username", user.getUsername());
//        session.setAttribute("nicknam", user.getNickname());
        return new Result<User>(OK, user);
    }

    @RequestMapping("logout")
    public Result logout(HttpSession session) {
        System.out.println("注销用户");
        session.invalidate();
//        System.out.println(getUsernameFromSession(session));
        return new Result<>(OK);
    }

    @RequestMapping("changePwd")
    public Result changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new Result<>(OK);
    }

    @RequestMapping("getByUid")
    public Result<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getUidFromSession(session));
        return new Result<User>(OK, data);
    }

    @RequestMapping("changeInfo")
    public Result changeInfo(User user, HttpSession session) {
//        System.out.println(user);
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new Result<>(OK);
    }

    @RequestMapping("changeImg")
    public Result<String> changeImg(HttpSession session, @RequestParam("file") MultipartFile file) {
        String username = getUsernameFromSession(session);
        String userImg = UploadUtils.uploadImg(file, username);
        Integer uid = getUidFromSession(session);
        userService.changeImg(uid, userImg);
        return new Result<>(OK, userImg);
    }
}
