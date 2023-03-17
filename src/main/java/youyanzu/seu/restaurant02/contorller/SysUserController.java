package youyanzu.seu.restaurant02.contorller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import youyanzu.seu.restaurant02.Enums.ResultEnum;
import youyanzu.seu.restaurant02.Utils.ResultUtil;
import youyanzu.seu.restaurant02.Utils.UserResult;
import youyanzu.seu.restaurant02.entity.Result;
import youyanzu.seu.restaurant02.entity.SysUser;
import youyanzu.seu.restaurant02.service.ISysRoleService;
import youyanzu.seu.restaurant02.service.ISysUserRoleService;
import youyanzu.seu.restaurant02.service.ISysUserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author summerWang
 * @since 2023-03-14
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysUserService userService;

    private String getRoleByLoginCode(String loginCode) {
        int roleId = userService.getUserByUsername(loginCode).getRoleId();
        return roleService.getNameById(roleId);
    }

    //    @RequestParam("loginCode") String username, @RequestParam("password") String password
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result doLogin(@RequestBody Map<String, Object> paramsMap) {
        System.out.println(paramsMap == null);
        String username = paramsMap.get("loginCode").toString();
        String password = paramsMap.get("password").toString();
        if (!userService.userExist(username)) {
            return ResultUtil.error(ResultEnum.USER_NO_FOUND);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            HashMap<String, String> map = new HashMap<>();
            map.put("token", username + UUID.randomUUID());
            map.put("loginCode", username);
            map.put("role", getRoleByLoginCode(username));
            return ResultUtil.success(map);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultUtil.error((ResultEnum.PWD_ERROR));
        }
    }

    //    @RequestParam(value = "phone") String username, @RequestParam(value = "password") String password,
//    @RequestParam(value = "name") String name, @RequestParam(value = "email", required = false) String email,
//    @RequestParam(value = "gender") String gender,
//    @RequestParam(value = "idNumber") String id, @RequestParam(value = "address", required = false) String address,
//    @RequestParam(value = "roleName", required = false) String roleName
//    @RequiresPermissions("userManage:edit")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addUser(@RequestBody Map<String, Object> map) {
        System.out.println(map.toString());
        SysUser user = new SysUser();
        user.setLoginCode(map.get("phone").toString());
        user.setPassword(map.get("password").toString());
        user.setName(map.get("name").toString());
        System.out.println(map.containsKey("email"));
        if (map.containsKey("email"))
            user.setEmail(map.get("email").toString());
        user.setGender(map.get("gender").toString());
        //从身份证号中提取出生年月日
        String id = map.get("idNumber").toString();
        String birthdayString = id.substring(6, 10) + "-" + id.substring(10, 12) + "-" + id.substring(12, 14);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        user.setBirthday(LocalDate.parse(birthdayString, fmt));


        Integer roleId = roleService.getIdByName(map.get("roleName").toString());
        user.setRoleId(roleId);


        user.setIdNumber(id);
        if (map.containsKey("address"))
            user.setAddress(map.get("address").toString());
        user.setPhone(user.getLoginCode());
        if (userService.idExist(id)) {
            return ResultUtil.error(ResultEnum.ID_NUMBER_IS_EXIST);
        }
        if (userService.userExist(user.getLoginCode())) {
            return ResultUtil.error(ResultEnum.PHONE_IS_EXIST);
        }
        if (userService.addUser(user)) {
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

    //    @RequestParam("loginCode") String username,
//    @RequestParam(value = "name",required = false) String name, @RequestParam(value = "email", required = false)String email,
//    @RequestParam(value = "gender", required = false)String gender, @RequestParam(value = "address", required = false
//    @RequiresPermissions("userManage:edit")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result editUser(@RequestBody Map<String, Object> map) {
        SysUser user = new SysUser();
        if (map.containsKey("name"))
            user.setName(map.get("name").toString());
        if (map.containsKey("email"))
            user.setName(map.get("email").toString());
        if (map.containsKey("gender"))
            user.setName(map.get("gender").toString());
        if (map.containsKey("address"))
            user.setName(map.get("address").toString());
        if (userService.editUser(map.get("loginCode").toString(), user)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

    //    @RequiresPermissions("userManage:edit")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deleteUser(@RequestBody Map<String, Object> map) {
        String username = map.get("loginCode").toString();
        SysUser user = userService.getUserByUsername(username);
        Long id = user.getUserId();
        if (!userService.userExist(username)) {
            return ResultUtil.error(ResultEnum.USER_NO_FOUND);
        }
        if (userService.deleteByUsername(username)) {
            return ResultUtil.success();
        } else
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Result resetPassword(@RequestParam("loginCode") String username, @RequestParam("password") String password) {
        if (userService.resetPassword(username, password)) {
            return ResultUtil.success();
        } else
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

    //    @RequiresPermissions("userManage:view ")
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public Result list(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize,
                       @RequestParam(value = "name", required = false) String name, @RequestParam(value = "phone", required = false) String phone,
                       @RequestParam(value = "gender", required = false) String gender, @RequestParam(value = "roleName", required = false) String[] role,
                       @RequestParam(value = "birthday", required = false) String birthday, @RequestParam(value = "idNumber", required = false) String idNumber) {
        pageNum--;

        SysUser user = new SysUser();
        if (name != null)
            user.setName(name);
        if (phone != null)
            user.setPhone(phone);
        if (gender != null)
            user.setGender(gender);
        if (birthday != null) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            user.setBirthday(LocalDate.parse(birthday, fmt));
        }
        if (idNumber != null)
            user.setIdNumber(idNumber);

        Page<SysUser> pageList = userService.getList(pageNum, pageSize, user, role);
        List<SysUser> list = pageList.getRecords();
        List<UserResult> userResults = toUserResult(list);
        Result<List<UserResult>> result = new Result<>();
        result.setData(userResults);
        result.setCode(200);
        result.setMsg("查询成功");
        result.setCount((long) userResults.size());
        return result;
    }

    private List<UserResult> toUserResult(List<SysUser> list) {
        List<UserResult> results = new ArrayList<>();
        for (SysUser user : list) {
            results.add(toResult(user));
        }
        return results;
    }

    private UserResult toResult(SysUser user) {
        UserResult result = new UserResult();
        result.setName(user.getName());
        result.setBirthday(user.getBirthday());
        result.setGender(user.getGender());
        result.setLoginCode(user.getLoginCode());
        result.setPhone(user.getPhone());
        result.setAddress(user.getAddress());
        result.setRoleName(roleService.getNameById(user.getRoleId()));
        result.setIdNumber(user.getIdNumber());
        result.setEmail(user.getEmail());
        return result;
    }

}
