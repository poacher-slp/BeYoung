package per.poacher.beyoungmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author poacher
 * @create 2022-05-05-15:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
//    `item_id` int NOT NULL COMMENT '订单项id',
//            `order_id` int DEFAULT NULL COMMENT '订单id',
//            `product_id` int DEFAULT NULL COMMENT '商品id',
//            `product_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
//            `product_img` varchar(32) DEFAULT NULL COMMENT '商品图片',
//            `sku_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'sku id',
//            `sku_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'sku名称',
//            `product_price` int DEFAULT NULL COMMENT '商品价格',
//            `buy_counts` int DEFAULT NULL COMMENT '购买数量',
//            `total_amount` decimal(32,8) DEFAULT NULL COMMENT '商品总金额',
//            `basket_date` datetime DEFAULT NULL COMMENT '加入购物车时间',
//            `buy_time` datetime DEFAULT NULL COMMENT '购买时间',
//            `is_comment` int DEFAULT NULL COMMENT '评论状态 0未评价 1已评价',
    private Integer itemId;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private String productImg;
    private String skuId;
    private String skuName;
    private Integer productPrice;
    private Integer buyCounts;
    private Integer totalAmount;
    private Date basketDate;
    private Date buyTime;
    private Integer isComment;
}
