package per.poacher.beyoungmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.util.Date;

/**
 * @author poacher
 * @create 2022-05-05-15:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
//    `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
//            `user_id` int DEFAULT NULL COMMENT '用户id',
//            `untitled` varchar(32) DEFAULT NULL COMMENT '产品名称',
//            `reveiver_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货人姓名',
//            `receiver_mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货人手机号',
//            `reveiver_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货地址',
//            `total_amount` decimal(32,8) DEFAULT NULL COMMENT '订单总价格',
//            `actual_amount` int DEFAULT NULL COMMENT '实际支付总价格',
//            `pay_type` int DEFAULT NULL COMMENT '支付方式 1微信 2支付宝',
//            `order_remark` varchar(32) DEFAULT NULL COMMENT '订单备注',
//            `status` int DEFAULT NULL COMMENT '订单状态 0未支付 1已支付 2已取消 3已关闭 4已完成',
//            `delivery_type` varchar(32) DEFAULT NULL COMMENT '配送方式',
//            `delivery_flow_id` varchar(32) DEFAULT NULL COMMENT '物流单号',
//            `order_freight` decimal(32,8) DEFAULT '0.00000000' COMMENT '订单运费',
//            `delete_status` int DEFAULT '0' COMMENT '逻辑删除状态 1删除 0未删除',
//            `create_time` datetime DEFAULT NULL COMMENT '创建时间（成交时间）',
//            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
//            `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
//            `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
//            `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
//            `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
    private Integer orderId;
    private Integer userId;
    private String untitled;
    private String reveiverName;
    private String receiverMobile;
    private String reveiverAddress;
    private Integer totalAmount;
    private Integer actualAmount;
    private Integer payType;
    private String orderRemark;
    private Integer status;
    private String deliveryType;
    private String deliveryFlowId;
    private String orderFreight;
    private Integer deleteStatus;
    private Date createTime;
    private Date updateTime;
    private Date payTime;
    private Date deliveryTime;
    private Date finishTime;
    private Date cancelTime;
}
