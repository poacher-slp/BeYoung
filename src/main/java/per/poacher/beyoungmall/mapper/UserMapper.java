package per.poacher.beyoungmall.mapper;

import org.apache.ibatis.annotations.Param;
import per.poacher.beyoungmall.pojo.User;

import java.util.Date;

/**
 * @author poacher
 * @create 2022-04-18-17:24
 */
public interface UserMapper {

    /**
     * 添加用户
     * @param user 用户数据
     * @return 返回1：添加成功
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名，即登录名
     * @return 返回对应的用户数据，返回null表示该用户名为进行注册
     */
    User findByUsername(String username);

    /**
     * 根据用户的uid修改用户的密码
     * @param userId 用户的id
     * @param password 新密码
     * @param userModtime 修改时间
     * @return 受影响的行数
     */
    Integer updatePasswordByUid(@Param("userId") Integer userId, @Param("password") String password, @Param("userModtime") Date userModtime);

    /**
     * 根据用户的id查询用户的数据
     * @param userId 用户的id
     * @return 如果找到则返回对象，否则返回null
     */
    User findByUid(Integer userId);

    /**
     * 更新用户的数据信息
     * @param user 用户的数据
     * @return 受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户的id修改用户头像
     * @param userId 用户的id
     * @param userImg 用户的头像
     * @param userModtime 修改时间
     * @return 受影响的行数
     */
    Integer updateImgByUid(@Param("userId") Integer userId, @Param("userImg") String userImg, @Param("userModtime") Date userModtime);

}
