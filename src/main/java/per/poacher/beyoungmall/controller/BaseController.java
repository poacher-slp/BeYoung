package per.poacher.beyoungmall.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import per.poacher.beyoungmall.controller.ex.*;
import per.poacher.beyoungmall.service.ex.*;
import per.poacher.beyoungmall.util.Result;

import javax.servlet.http.HttpSession;

/**
 * @author poacher
 * @create 2022-05-04-2:44
 */
public class BaseController {

    public static final int OK = 200;

    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public Result handlerException(Throwable e) {
        Result<Object> result = new Result<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        }
        else if (e instanceof UserNotFoundtException) {
            result.setState(4001);
            result.setMessage("用户不存在,请先注册");
        }
        else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("密码错误");
        }
        else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("收货地址超出上限");
        }
        else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMessage("收货地址不存在");
        }
        else if (e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMessage("非法访问");
        }
        else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("商品不存在");
        }
        else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("购物车数据不存在");
        }
        else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("添加失败");
        }
//        else if (e instanceof UserNotFoundtException) {
//            result.setState(5001);
//            result.setMessage("用户数据不存在的异常");
//        }
//        else if (e instanceof PasswordNotMatchException) {
//            result.setState(5002);
//            result.setMessage("用户名的密码错误");
//        }
        else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("更新失败");
        }
        else if (e instanceof DeleteException) {
            result.setState(5004);
            result.setMessage("删除失败");
        }
        else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("文件为空");
        }
        else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件可能过大");
        }
        else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("文件类型不对");
        }
        else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("文件状态产生的未知异常");
        }
        else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("文件读写时产生的未知错误");
        }
        return result;
    }

    /**
     * 获取session对象中uid
     * @param session session对象
     * @return 当前登录的用户uid值
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session session对象
     * @return 当前登录用户的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

}
