package miaosha.controller;

import miaosha.domain.MiaoshaUser;
import miaosha.redis.RedisService;
import miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_list")
    public String toLogin(Model model,
                          @CookieValue(value=MiaoshaUserService.COOKI_NAME_TOKEN, required = false) String cookieToken,
                          @RequestParam(value = MiaoshaUserService.COOKI_NAME_TOKEN,required = false) String paramToken){
        //model.addAttribute("user", new MiaoshaUser());
        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "login";
        }

        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        MiaoshaUser user = userService.getByToken(token);

        model.addAttribute("user",user);
        System.out.println(user.getNickname());
        return "goods_list";
    }
    @RequestMapping("/totest_list")
    public String t(){
        return "goods_list";
    }
}
