package com.slife.controller;

//import cas.service.UserService;
import com.slife.entity.SysUser;
import com.slife.service.ISysUserService;
//import com.slife.service.impl.SysUserService;
import com.slife.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private ISysUserService userService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("username",getSysUser());
        return "redirect:index";
    }

    public SysUser getSysUser(){
        return userService.getByLoginName(SecurityUtils.getSubject().getPrincipal().toString());
    }
    public ShiroUser getShiroUser(){
        SysUser sysUser = this.getSysUser();
        ShiroUser shiroUser = new ShiroUser(sysUser.getId(),sysUser.getName(),sysUser.getLoginName()) ;
        return shiroUser;
    }
}