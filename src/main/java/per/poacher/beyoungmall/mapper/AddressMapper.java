package per.poacher.beyoungmall.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import per.poacher.beyoungmall.pojo.Address;

import java.util.Date;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-04-19:21
 */
public interface AddressMapper {

    /**
     * 新增收货地址
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id统计收货地址数量
     * @param uid 用户的id
     * @return 当前用户的收货地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的id查询用户的收货地址
     * @param userId 用户id
     * @return 收货地址数据
     */
    List<Address> findByUid(Integer userId);

    /**
     * 根据aid查询收获地址数据
     * @param addrId 收货地址ia
     * @return 收货地址数据，如果没有找到则返回null
     */
    Address findByAid(Integer addrId);

    /**
     * 根据用户的id将用户的所有收货地址设置为非默认
     * @param userId 用户的id
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer userId);

    /**
     * 根据aid将收货地址设置为默认收货地址
     * @param addrId
     * @param updateTime
     * @return
     */
    Integer updateDefaultByAid(@Param("addrId") Integer addrId, @Param("updateTime") Date updateTime);

    /**
     * 根据收货地址id删除收货地址数据
     * @param addrId
     * @return
     */
    Integer deleteByAid(Integer addrId);

    /**
     * 根据用户uid查询当前用户最后一次被修改的收获地址数据
     * @param userId 用户id
     * @return 收获地址数据
     */
    Address findLastModified(Integer userId);

    /**
     * 根据aid更新收货地址数据
     * @return
     */
    Integer updateByAid(Address address);
}
