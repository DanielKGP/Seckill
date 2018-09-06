package miaosha.controller;

import miaosha.redis.RedisService;
import miaosha.result.CodeMsg;
import miaosha.result.Result;
import miaosha.service.MiaoshaUserService;
import miaosha.util.ValidatorUtil;
import miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response,  @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        /*
        //test parameter
        String passInput = loginVo.getPassword();
        String mobile = loginVo.getMobile();

        System.out.println(passInput);
        System.out.println(mobile);

        if(StringUtils.isEmpty(passInput)){
            return Result.error(CodeMsg.PASSWORD_EMPTY);

        }
        if(StringUtils.isEmpty(mobile)){
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if(!ValidatorUtil.isMobile(mobile)){
            return Result.error(CodeMsg.MOBILE_ERROR);
        }*/
        userService.login(response,loginVo);
        return Result.success(true);

    }

}
