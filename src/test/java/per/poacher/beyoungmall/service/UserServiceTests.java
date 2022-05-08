package per.poacher.beyoungmall.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.poacher.beyoungmall.mapper.UserMapper;
import per.poacher.beyoungmall.pojo.User;
import per.poacher.beyoungmall.service.ex.ServiceException;

/**
 * @author poacher
 * @create 2022-05-04-0:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("zhaosi2");
            user.setPassword("123");
            user.setUserImg("zhaosi123");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            e.printStackTrace();
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        User zhaosi = userService.login("zhaosi3", "123",1);
        System.out.println(zhaosi);
    }

    @Test
    public void changePassword() {
        userService.changePassword(5,"zhaosi4", "321", "123");
    }

    @Test
    public void getByUid() {
        User user = userService.getByUid(5);
        System.out.println(user);
    }

    @Test
    public void changeInfo() {
        User user = new User();
        user.setUserMobile("1234567");
        user.setUserEmail("wangwu@qq.com");
        user.setUserSex("1");
        userService.changeInfo(5,"zhaosi4", user);
    }

    @Test
    public void changeImg() {
        userService.changeImg(5, "/upload/userImg.jpg");
    }
}
