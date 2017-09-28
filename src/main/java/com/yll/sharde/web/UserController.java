package com.yll.sharde.web;

import com.yll.sharde.entity.User;
import com.yll.sharde.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/6/29 16:12
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 返回逻辑视图名
     *
     * @return
     */
    @RequestMapping(value = "/getUserById")
    public String queryUserCount(@RequestParam(value = "userId") Long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return "success";
    }

    /**
     * 返回json数据
     * @return
     */
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUser(){
        List<User> users=userService.getAllUser();
        return users;
    }

    @RequestMapping(value = "/getUserByName")
    @ResponseBody
    public List<User> getUserByName(@RequestParam(value = "name") String name){
        List<User> user=userService.getUserByName(name);
        return user;
    }
}
