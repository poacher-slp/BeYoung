package per.poacher.beyoungmall.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.poacher.beyoungmall.pojo.Address;

import java.util.Date;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-04-19:40
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUserId(5);
        address.setReceiverMobile("123456");
        address.setReceiverName("rose");
        Integer insert = addressMapper.insert(address);
        System.out.println(insert);
    }

    @Test
    public void countByUid() {
        Integer integer = addressMapper.countByUid(5);
        System.out.println(integer);
    }

    @Test
    public void findByUid() {
        List<Address> list = addressMapper.findByUid(5);
        System.out.println(list);
    }

    @Test
    public void findByAid() {
        Address byAid = addressMapper.findByAid(4);
        System.out.println(byAid);
    }

    @Test
    public void updateNonDefault() {
        addressMapper.updateNonDefault(5);
    }

    @Test
    public void updateDefaultByAid() {
        addressMapper.updateDefaultByAid(2, new Date());
    }

    @Test
    public void deleteByAid() {
        Integer integer = addressMapper.deleteByAid(1);
        System.out.println(integer);
    }

    @Test
    public void findLastModified() {
        Address lastModified = addressMapper.findLastModified(6);
        System.out.println(lastModified);
    }
}
