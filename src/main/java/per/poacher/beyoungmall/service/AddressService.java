package per.poacher.beyoungmall.service;

import org.omg.CORBA.INTERNAL;
import per.poacher.beyoungmall.pojo.Address;

import java.util.List;

/**
 * @author poacher
 * @create 2022-05-04-19:39
 */
public interface AddressService {

    void addNewAddress(Integer userId, String username, Address address);

    /**
     * 根据用户的id获取用户的收货地址
     * @param userId
     * @return
     */
    List<Address> getByUid(Integer userId);

    /**
     * 修改某个用户的某条收货地址数据为默认收货地址
     * @param aid
     * @param uid
     */
    void setDefault(Integer aid, Integer uid);

    /**
     * 删除用户选中的收货地址数据
     * @param aid
     * @param uid
     */
    void delete(Integer aid, Integer uid);

    Address getByAid(Integer aid, Integer uid);

    /**
     * 更新收货地址
     * @param address
     * @param aid
     */
    void updateByAid(Address address, Integer aid);
}
