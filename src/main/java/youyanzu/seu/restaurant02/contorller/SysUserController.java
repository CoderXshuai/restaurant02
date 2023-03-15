package youyanzu.seu.restaurant02.contorller;


import youyanzu.seu.restaurant02.Enums.ResultEnum;
import youyanzu.seu.restaurant02.Utils.ResultUtil;
import youyanzu.seu.restaurant02.entity.Result;
import youyanzu.seu.restaurant02.entity.SysUser;
import youyanzu.seu.restaurant02.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author summerWang
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private ISysUserService userService;
    @RequestMapping("/login")
    public Result doLogin(@RequestParam("username") String username, @RequestParam("password") String password){
        if(!userService.userExist(username)){
            return ResultUtil.error(ResultEnum.USER_NO_FOUND);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            subject.login(token);
            HashMap<String, String> map = new HashMap<>();
            map.put("token", username + UUID.randomUUID());
            map.put("loginCode",username);
            return ResultUtil.success(map);
        }
        catch (AuthenticationException e){
            e.printStackTrace();
            return ResultUtil.error((ResultEnum.PWD_ERROR));
        }

    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addUser(@RequestParam(value = "phone") String username, @RequestParam(value = "password") String password,
                          @RequestParam(value = "name") String name, @RequestParam(value = "email", required = false) String email,
                          @RequestParam(value = "gender") String gender,
                          @RequestParam(value = "id_number") String id, @RequestParam(value = "address", required = false) String address){
        SysUser user = new SysUser();
        user.setLoginCode(username);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        //从身份证号中提取出生年月日
        String birthdayString = id.substring(6, 10)+"-"+id.substring(10,12)+"-"+id.substring(12,14);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        user.setBirthday(LocalDate.parse(birthdayString, fmt));

        user.setIdNumber(id);
        user.setAddress(address);
        user.setPhone(username);
        if(userService.idExist(id)){
            return ResultUtil.error(ResultEnum.ID_NUMBER_IS_EXIST);
        }
        if(userService.userExist(username)){
            return ResultUtil.error(ResultEnum.PHONE_IS_EXIST);
        }
        if(userService.addUser(user)){
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result editUser(@RequestParam("username") String username,
                           @RequestParam(value = "name",required = false) String name, @RequestParam(value = "email", required = false)String email,
                           @RequestParam(value = "gender", required = false)String gender, @RequestParam(value = "address", required = false) String address){
        SysUser user = new SysUser();
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        user.setAddress(address);
        if(userService.editUser(username, user)){
            return ResultUtil.success();
        }
        else {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deleteUser(@RequestParam("username") String username){
        if(!userService.userExist(username)){
            return ResultUtil.error(ResultEnum.USER_NO_FOUND);
        }
        if (userService.deleteByUsername(username)){
            return ResultUtil.success();
        }
        else
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Result resetPassword(@RequestParam("username") String username, @RequestParam("password") String password){
        if(userService.resetPassword(username,password)){
            return ResultUtil.success();
        }
        else
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }
}
