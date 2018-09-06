package miaosha.service;

import miaosha.dao.MiaoshaUserDao;
import miaosha.exception.GlobalException;
import miaosha.redis.MiaoshaUserKey;
import miaosha.redis.RedisService;
import miaosha.result.CodeMsg;
import miaosha.util.MD5Util;
import miaosha.util.UUIDUtil;
import miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miaosha.domain.MiaoshaUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {

    private static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo==null){
            throw new GlobalException(CodeMsg.SEVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //judge if the user exist or not.
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //judge password
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass =  MD5Util.formPassToDBPass(formPass,saltDB);
        if(!calcPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        //generate cookie
        String token = UUIDUtil.uuid();
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }
}

