package per.poacher.beyoungmall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class BeyoungmallApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() {
    }

    @Test
    public void getConnetion() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
