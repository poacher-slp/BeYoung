package per.poacher.beyoungmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("per.poacher.beyoungmall.mapper")
public class BeyoungmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeyoungmallApplication.class, args);
    }

}
