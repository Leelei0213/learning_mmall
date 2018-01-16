package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Lllei on 2017/12/22.
 */
@Controller
@RequestMapping("/manager/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public ServerResponse<User> login(String useranme, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(useranme, password);
        if (response.isSuccess()) {
            User user = response.getData();
            if (user.getRole().equals(Const.Role.ROLE_ADMIN)) {
                //说明登录的是管理员
                session.setAttribute(Const.CURRENT_USER, user);
                return response;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员，无法登录");
            }
        }

        return response;
    }
}
