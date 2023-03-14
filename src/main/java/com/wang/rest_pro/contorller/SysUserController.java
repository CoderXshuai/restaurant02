package com.wang.rest_pro.contorller;


import com.wang.rest_pro.entity.Result;
import com.wang.rest_pro.Utils.ResultUtil;

import com.wang.rest_pro.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author summerWang
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/user/")
public class SysUserController {
    @Autowired
    private ISysUserService userService;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public Result userLogin(@RequestParam("id") String username, @RequestParam("psw") String password){
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        return ResultUtil.success();
    }
}
