package youyanzu.seu.restaurant02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("youyanzu.seu.restaurant02.mapper")
@SpringBootApplication
public class RestProApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestProApplication.class, args);
    }

}
