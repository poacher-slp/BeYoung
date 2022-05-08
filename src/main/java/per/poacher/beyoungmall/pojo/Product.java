package per.poacher.beyoungmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author poacher
 * @create 2022-05-05-0:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
// `id` int NOT NULL COMMENT '商品id',
//            `category_id` int DEFAULT NULL COMMENT '分类id',
//            `item_type` varchar(100) DEFAULT NULL COMMENT '商品系列',
//            `title` varchar(100) DEFAULT NULL COMMENT '商品标题',
//            `sell_point` varchar(150) DEFAULT NULL COMMENT '商品卖点',
//            `price` int DEFAULT NULL COMMENT '商品单价',
//            `num` int DEFAULT NULL COMMENT '库存数量',
//            `image` varchar(500) DEFAULT NULL COMMENT '图片路径',
//            `status` int DEFAULT '1' COMMENT '商品状态 1上架 2下架 3删除',
//            `priority` int DEFAULT NULL COMMENT '显示优先级',
//            `created_time` datetime DEFAULT NULL COMMENT '创建时间',
//            `modified_time` datetime DEFAULT NULL COMMENT '最后修改时间',
    private Integer id;

    private Integer categoryId;

    private String itemType;

    private String title;

    private String sellPoint;

    private Integer price;

    private Integer num;

    private String image;

    private Integer status;

    private Integer priority;

    private Date createdTime;

    private Date modifiedTime;
}
