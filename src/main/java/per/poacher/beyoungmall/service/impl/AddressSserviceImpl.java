package per.poacher.beyoungmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import per.poacher.beyoungmall.mapper.AddressMapper;
import per.poacher.beyoungmall.pojo.Address;
import per.poacher.beyoungmall.service.AddressService;
import per.poacher.beyoungmall.service.ex.*;

import java.util.Date;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-04-19:39
 */
@Service
public class AddressSserviceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer userId, String username, Address address) {
        Integer count = addressMapper.countByUid(userId);
        if (count >= maxCount) {
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
        address.setUserId(userId);
        Integer isDefault = count == 0 ? 1 : 0;
        address.setCommonAddr(isDefault);
        address.setCreateTime(new Date());
        address.setUpdateTime(new Date());
        Integer insert = addressMapper.insert(address);
        if(insert != 1) {
            throw new InsertException("插入用户的收货地址产生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer userId) {
        List<Address> list = addressMapper.findByUid(userId);
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        if(!result.getUserId().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        Integer integer = addressMapper.updateNonDefault(uid);
        if(integer < 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }
        Integer rows = addressMapper.updateDefaultByAid(aid, new Date());
        if(rows != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if(!result.getUserId().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteException("删除数据产生未知的异常");
        }
        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            return;
        }
        if (result.getCommonAddr() == 1) {
            Address address = addressMapper.findLastModified(uid);
            Integer updateDefaultByAid = addressMapper.updateDefaultByAid(address.getAddrId(), new Date());
            if (updateDefaultByAid != 1) {
                throw new UpdateException("更新数据产生未知的异常");
            }
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address result = addressMapper.findByAid(aid);
        if(result == null) {
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if(!result.getUserId().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        return result;
    }

    @Override
    public void updateByAid(Address address, Integer aid) {
        address.setUpdateTime(new Date());
        address.setAddrId(aid);
        Integer rows = addressMapper.updateByAid(address);
        if(rows != 1) {
            throw new UpdateException("更新地址失败");
        }
    }
}
