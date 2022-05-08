package per.poacher.beyoungmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author poacher
 * @create 2022-04-18-17:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;

    private String username;

    private String password;

    private String nickname;

    private String realname;

    private String userImg;

    private String userMobile;

    private String userEmail;

    private String userSex;

    private Date userBirth;

    private Date userRegtime;

    private Date userModtime;

    private String salt;

    private Integer role;
}
