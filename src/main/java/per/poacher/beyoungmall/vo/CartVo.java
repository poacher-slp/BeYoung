package per.poacher.beyoungmall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author poacher
 * @create 2022-05-05-12:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVo implements Serializable {

    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Integer price;
    private Integer num;
    private String title;
    private String image;
    private Integer realPrice;
}
