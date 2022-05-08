package per.poacher.beyoungmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author poacher
 * @create 2022-05-05-10:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
//    `cid` int NOT NULL AUTO_INCREMENT COMMENT '购物车数据id',
//            `uid` int NOT NULL COMMENT '用户id',
//            `pid` int NOT NULL COMMENT '商品id',
//            `price` int DEFAULT NULL COMMENT '加入时商品单价',
//            `num` int DEFAULT NULL COMMENT '商品数量',
//            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
//            `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Integer price;
    private Integer num;
    private Date createTime;
    private Date modifiedTime;
}
