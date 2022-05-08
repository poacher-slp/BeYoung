package per.poacher.beyoungmall.service;

import org.apache.ibatis.annotations.Param;
import per.poacher.beyoungmall.pojo.User;

import java.util.Date;

/**
 * @author poacher
 * @create 2022-05-04-1:56
 */
public interface UserService {

    /**
     * 用户注册
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     * 用户登录功能
     * @param username 用户名
     * @param password 用户密码
     * @return 当前匹配的用户数据，null表示没有此用户
     */
    User login(String username, String password, Integer role);

    /**
     * 根据用户id修改密码
     * @param uid 用户的uid
     * @param username 登录用户的用户名
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     */
    void changePassword(Integer uid, String username, String oldPwd, String newPwd);

    /**
     * 根据用户的id查询数据
     * @param userid 用户id
     * @return 用户的数据
     */
    User getByUid(Integer userid);

    /**
     * 更新用户的数据
     * @param userId 用户id
     * @param username 用户名称
     * @param user 用户的数据
     */
    void changeInfo(Integer userId, String username, User user);

    /**
     * 修改用户头像
     * @param userId 用户的id
     * @param userImg 用户的头像
     */
    void changeImg(Integer userId, String userImg);

}
