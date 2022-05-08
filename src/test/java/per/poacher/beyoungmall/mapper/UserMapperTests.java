package per.poacher.beyoungmall.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.poacher.beyoungmall.pojo.User;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author poacher
 * @create 2022-05-04-0:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123");
        user.setUserImg("zhangsan123");
//        user.setUserRegtime(new Date(System.currentTimeMillis()));
//        user.setUserModtime(new Date(System.currentTimeMillis()));
//        System.out.println(userMapper);
//        System.out.println(user);
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void findByUsername() {
        User zhangsan = userMapper.findByUsername("zhaosi4");
        System.out.println(zhangsan);
    }

    @Test
    public void updatePasswordByUid() {
        Integer integer = userMapper.updatePasswordByUid(4, "321", new Date());
        System.out.println(integer);
    }

    @Test
    public void findByUid() {
        User user = userMapper.findByUid(4);
        System.out.println(user);
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUserId(4);
        user.setUserMobile("123456");
        user.setUserEmail("zhaosi3@qq.com");
        user.setUserSex("1");
        Integer update = userMapper.updateInfoByUid(user);
        System.out.println(update);
    }

    @Test
    public void updateImgByUid() {
        userMapper.updateImgByUid(6, "/upload/userImg.png", new Date());
    }
}
