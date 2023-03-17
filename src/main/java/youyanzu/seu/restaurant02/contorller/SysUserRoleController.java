package youyanzu.seu.restaurant02.contorller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import youyanzu.seu.restaurant02.service.ISysMenuService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author summerWang
 * @since 2023-03-15
 */
@CrossOrigin
@RestController
@RequestMapping("/userRole")
public class SysUserRoleController {
    @Autowired
    private ISysMenuService service;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(){
        List<String> list = service.getPermissionsByRoleId(1);
        for (String i:list)
            System.out.println(i);
    }

}
