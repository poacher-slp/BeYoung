package per.poacher.beyoungmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author poacher
 * @create 2022-05-05-0:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImg {

    private Integer id;

    private Integer itemId;

    private String uri;

    private Integer sort;

    private Integer isMain;

    private Date createTime;

    private Date updateTime;
}
