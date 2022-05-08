package per.poacher.beyoungmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author poacher
 * @create 2022-05-05-0:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductParams {
//    `param_id` varchar(64) NOT NULL COMMENT '商品参数id',
//            `product_id` varchar(64) NOT NULL COMMENT '商品id',
//            `product_place` varchar(64) NOT NULL COMMENT '产地',
//            `foot_period` varchar(64) NOT NULL COMMENT '保质期',
//            `brand` varchar(64) NOT NULL COMMENT '品牌',
//            `factory_name` varchar(64) NOT NULL COMMENT '生产厂名',
//            `factory_address` varchar(64) NOT NULL COMMENT '生产地址',
//            `packaging_method` varchar(64) NOT NULL COMMENT '包装方式',
//            `weight` varchar(64) NOT NULL COMMENT '规格重量',
//            `storage_method` varchar(64) NOT NULL COMMENT '存储方法',
//            `create_time` datetime NOT NULL COMMENT '创建时间',
//            `update_time` datetime NOT NULL COMMENT '更新时间',

    private Integer paramId;

    private Integer productId;

    private String productPlace;

    private String footPeriod;

    private String brand;

    private String factoryName;

    private String factoryAddress;

    private String packagingMethod;

    private String weight;

    private String storageMethod;

    private Date createTime;

    private Date updateTime;
}
