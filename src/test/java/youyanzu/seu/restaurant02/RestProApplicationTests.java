package youyanzu.seu.restaurant02;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import youyanzu.seu.restaurant02.service.ISysMenuService;

import java.util.List;

@SpringBootTest
class RestProApplicationTests {

    @Autowired
    private ISysMenuService service;

    @Test
    void contextLoads() {
        List<String> list = service.getPermissionsByRoleId(1);
        for (String i:list)
            System.out.println(i);
    }

}
