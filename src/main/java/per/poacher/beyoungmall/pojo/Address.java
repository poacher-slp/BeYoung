package per.poacher.beyoungmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** 收货地址
 * @author poacher
 * @create 2022-05-04-19:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Integer addrId;

    private Integer userId;

    private String receiverName;

    private String receiverMobile;

    private String province;

    private String city;

    private String area;

    private String addr;

    private String postCode;

    private String areaCode;

    private String cityCode;

    private String provinceCode;

    private Integer status;

    private Integer commonAddr;

    private Date createTime;

    private Date updateTime;

    private String tag;

}
