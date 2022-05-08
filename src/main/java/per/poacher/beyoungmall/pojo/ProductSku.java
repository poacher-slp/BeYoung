package per.poacher.beyoungmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author poacher
 * @create 2022-05-05-0:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSku {
//     `sku_id` varchar(64) NOT NULL COMMENT 'sku编号',
//            `product_id` varchar(64) NOT NULL COMMENT '商品id',
//            `sku_name` varchar(32) NOT NULL COMMENT 'sku名称',
//            `sku_img` varchar(32) NOT NULL COMMENT 'sku图片',
//            `untitled` varchar(64) NOT NULL COMMENT '属性组合',
//            `original_price` int NOT NULL COMMENT '原价',
//            `sell_price` int NOT NULL COMMENT '销售价格',
//            `discounts` decimal(4,2) NOT NULL COMMENT '折扣力度',
//            `stock` int NOT NULL COMMENT '库存',
//            `create_time` datetime NOT NULL COMMENT '创建时间',
//            `update_time` datetime NOT NULL COMMENT '更新时间',
//            `status` int DEFAULT NULL COMMENT 'sku状态 1启用 0禁用 -1删除',

    private Integer skuId;

    private Integer productId;

    private String skuName;

    private String skuImg;

    private String untitled;

    private Integer originalPrice;

    private Integer sellPrice;

    private BigDecimal discounts;

    private Integer stock;

    private Date createTime;

    private Date updateTime;

    private Integer status;
}
