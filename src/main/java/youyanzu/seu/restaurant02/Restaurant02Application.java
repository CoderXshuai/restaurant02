package youyanzu.seu.restaurant02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CoderXshuai
 * @date 2023/3/14 20:48
 */
@SpringBootApplication
@MapperScan("youyanzu.seu.restaurant02.mapper")
public class Restaurant02Application {

    public static void main(String[] args) {
        SpringApplication.run(Restaurant02Application.class, args);
    }

}
