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

    public static final int IMG_MAX_SIZE = 10 * 1024 * 1024;    //图像上传的最大值
    public static final List<String> IMG_TYPE = new ArrayList<>();  //文件上传的类型
    static{
        IMG_TYPE.add("image/jpeg");
        IMG_TYPE.add("image/png");
        IMG_TYPE.add("image/bmp");
        IMG_TYPE.add("image/gif");
        IMG_TYPE.add("image/jpg");
    }


    @RequestMapping("changeImg")
    public Result<String> changeImg(HttpSession session, @RequestParam("file") MultipartFile file) {
        if(file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize() > IMG_MAX_SIZE) {
            throw new FileSizeException("文件超出限制");
        }
        if(!IMG_TYPE.contains(file.getContentType())) {
            throw new FileTypeException("文件类型不支持");
        }
//        String parent = session.getServletContext().getRealPath("upload");
//        System.out.println("parent---" + parent);
        String parent = "E:/IDEA/poacher/beyoungmall/src/upload/" + getUidFromSession(session);
//        File dir = new File(parent);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        String userImg = parent + "/" + filename;
        File dest = new File(userImg);
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
//        File dest = new File(dir, filename);    //空文件
//        参数file中数据写入到这个空文件中
        try {
            file.transferTo(dest);//将file文件中的数据写入到dest中
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }
        Integer uid = getUidFromSession(session);
//        String userImg = "/upload/" + filename;
        userService.changeImg(uid, userImg);
        return new Result<>(OK, userImg);
    }
}
