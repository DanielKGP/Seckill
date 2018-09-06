package miaosha.controller;

import miaosha.domain.User;
import miaosha.redis.RedisService;
import miaosha.redis.UserKey;
import miaosha.result.CodeMsg;
import miaosha.result.Result;

import miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;



    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "Hello world!!! ";
    }
    //1.json 2.page


    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError(){

        return Result.error(CodeMsg.SEVER_ERROR);
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("hello,kkk");
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "kkkk Daniel");
        return "hello";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){

        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User user = redisService.get(UserKey.getById,""+1, User.class);
        return Result.success(user);

    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> resultSet(){
        User user = new User();
        user.setId(1);
        user.setName("11111");
        redisService.set(UserKey.getById,""+1,user);
        return Result.success(true);
    }

}
