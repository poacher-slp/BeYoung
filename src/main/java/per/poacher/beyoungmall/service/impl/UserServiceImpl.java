package per.poacher.beyoungmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import per.poacher.beyoungmall.mapper.UserMapper;
import per.poacher.beyoungmall.pojo.User;
import per.poacher.beyoungmall.service.UserService;
import per.poacher.beyoungmall.service.ex.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author poacher
 * @create 2022-05-04-1:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        User byUsername = userMapper.findByUsername(username);

        if (byUsername != null) {
            throw new UsernameDuplicatedException("用户名被占用");
        }

        String oldPwd = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String newPwd = getMd5(oldPwd, salt);
        user.setPassword(newPwd);
        user.setSalt(salt);

        Date date = new Date();
        user.setUserRegtime(date);
        user.setUserModtime(date);
        Integer insert = userMapper.insert(user);

//        System.out.println(insert);
        if (insert != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }

    }

    @Override
    public User login(String username, String password, Integer role) {
        User byUsername = userMapper.findByUsername(username);
        if (byUsername == null) {
            throw new UserNotFoundtException("用户数据不存在！请先注册");
        }

        String oldPwd = byUsername.getPassword();
        String salt = byUsername.getSalt();
        String newPwd = getMd5(password, salt);

        if (!newPwd.equals(oldPwd)) {
            throw new PasswordNotMatchException("密码错误！");
        }

        if(!byUsername.getRole().equals(role)) {
            throw new AccessDeniedException("非法访问");
        }

        return byUsername;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPwd, String newPwd) {
        User result = userMapper.findByUid(uid);
        if(result == null) {
            throw new UserNotFoundtException("用户数据不存在");
        }
        String oldmd5 = getMd5(oldPwd, result.getSalt());
        if (!result.getPassword().equals(oldmd5)) {
            throw new PasswordNotMatchException("原密码输入错误");
        }
        String newMd5 = getMd5(newPwd, result.getSalt());
        Integer update = userMapper.updatePasswordByUid(uid, newMd5, new Date());
        if(update != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }

    }

    @Override
    public User getByUid(Integer userid) {
        User result = userMapper.findByUid(userid);
        if(result == null) {
            throw new UserNotFoundtException("用户数据不存在");
        }
        System.out.println(result);
        return result;
    }

    @Override
    public void changeInfo(Integer userId, String username, User user) {
        User result = userMapper.findByUid(userId);
        if(result == null) {
            throw new UserNotFoundtException("用户数据不存在");
        }
        user.setUserId(userId);
        user.setUserModtime(new Date());
        Integer update = userMapper.updateInfoByUid(user);
        if (update != 1) {
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    @Override
    public void changeImg(Integer userId, String userImg) {
        User result = userMapper.findByUid(userId);
        if(result == null) {
            throw new UserNotFoundtException("用户数据不存在");
        }
        userMapper.updateImgByUid(userId, userImg, new Date());
    }

    /**
     * md5算法加密 三次加密
     * @param password 密码
     * @param salt 盐值
     * @return
     */
    private String getMd5(String password, String salt) {
        for(int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
