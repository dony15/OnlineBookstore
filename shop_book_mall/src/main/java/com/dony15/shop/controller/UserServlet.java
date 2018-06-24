package com.dony15.shop.controller;

import com.dony15.shop.pojo.User;
import com.dony15.shop.service.UserService;
import com.dony15.shop.utils.CheckCodeUtils;
import com.dony15.shop.utils.MessageDigestUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DonY15
 * @description
 * @create 2018\6\19 0019
 */
@Controller
public class UserServlet {
    /**
     * 用户资源
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * 注册
     * @param username
     * @param password
     * @param email
     * @param telephone
     * @param gender
     * @param introduce
     * @param req
     * @return
     */
    @RequestMapping("/register")
    public String insertUser(@Param("username")String username,
                                   @Param("password")String password,
                                   @Param("email")String email,
                                   @Param("telephone")String telephone,
                                   @Param("gender")String gender,
                                   @Param("introduce")String introduce,
                                   HttpServletRequest req
    ){
        User user = new User(  username,   password,   email,   telephone,   gender,   introduce);
        User user1 = userService.insertUser(user);
        if (user1!=null){
            req.getSession().setAttribute("user",user1);
            return "registersuccess";
        }else{
            req.setAttribute("error","注册失败,请重试");
            return"register";
        }
    }

    /**
     * 注册后台验证
     * @param userInfo
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkuserinfo")
    public String checkuserinfo(@RequestParam("userInfo")String userInfo,
                                @RequestParam("type")String type
                                ){
        System.out.println(userInfo+" "+type);
        Boolean chekInfo = userService.chekInfo(userInfo, type);
        return chekInfo+"";
    }

    /**
     * 验证码返回文本
     * @param req
     * @param resp
     */
    @RequestMapping("/getcodeword")
    public void getCodeWord(HttpServletRequest req,HttpServletResponse resp){
        CheckCodeUtils.getCodeWord(req,resp);
    }
    /**
     * 图片验证码程序,返回src
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/imageCode")
    public void imagecheckcode(HttpServletRequest req,
                               HttpServletResponse resp) throws IOException {
        CheckCodeUtils.imagecheckcode(req,resp);
    }

    /**
     * 登录
     * @param userInfo 用户名/邮箱/手机号
     * @param password 密码
     * @param resp
     * @param req
     */
    @RequestMapping(value = "/login" , method ={RequestMethod.POST})
    public void login(@RequestParam("userInfo")String userInfo,
                     @RequestParam("password")String password,
                     HttpServletResponse resp,
                     HttpServletRequest req){

        resp.setContentType("text/html;charset=UTF-8");
        password = MessageDigestUtils.sha1(password);
        User user = userService.selectUser(userInfo, password);
        if (user!=null&&user.getRole()!=null){
            req.getSession().setAttribute("user",user);
        System.out.println(req.getSession().getAttribute("user"));
            try {
                resp.getWriter().write("{\"role\":\""+user.getRole()+"\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                resp.getWriter().write("{\"role\":\"error\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改用户个人信息
     * @param password 密码
     * @param gender    性别
     * @param telephone 电话
     * @param req
     * @return  跳转过渡网页-->去掉Session信息,登录
     */
    @RequestMapping("/updateUser")
    public String updateUser(
                             @Param("password")String password,
                             @Param("gender")String gender,
                             @Param("telephone")String telephone,
                             HttpServletRequest req
                             ){

        User user = (User) req.getSession().getAttribute("user");
        System.out.println("更新用户信息:"+user);
        if (password!=null&&password!=""){
            password = MessageDigestUtils.sha1(password);
            user.setPassword(password);
        }
        if (gender!=null&&gender!=""){
            user.setGender(gender);
        }
        if (telephone!=null&&telephone!=""){
            user.setTelephone(telephone);
        }
        if ((password==null||password=="")&&(gender==null&&gender=="")&&(telephone==null&&telephone=="")){
            return "modifyUserInfo";
        }else{
            int i = userService.updateUser(user);
            req.getSession().removeAttribute("user");
            return i>0?"modifyUserInfoSuccess":"modifyUserInfo";
        }
    }

    /**
     * 登出功能user
     * @param request
     * @return 跳转login页面-->去除Session用户信息
     */
    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "login";
    }

    /**
     * 登出功能admin *********************************************ERROR******************
     * @param request
     * @return 跳转login页面-->去除Session用户信息
     */
    @RequestMapping("admin/loginOuttoadmin")
    public String loginOuttoadmin(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "login";
    }



}
