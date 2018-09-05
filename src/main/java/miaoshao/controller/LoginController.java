package miaoshao.controller;

import miaoshao.redis.RedisService;
import miaoshao.result.CodeMsg;
import miaoshao.result.Result;
import miaoshao.service.MiaoshaUserService;
import miaoshao.util.ValidatorUtil;
import miaoshao.vo.LoginVo;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;


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
    public Result<Boolean> doLogin(LoginVo loginVo){
        log.info(loginVo.toString());
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
        }
        CodeMsg cm = userService.login(loginVo);
        if(cm.getCode()==0){
            return Result.success(true);
        }else{
            return Result.error(cm);
        }
    }

}
