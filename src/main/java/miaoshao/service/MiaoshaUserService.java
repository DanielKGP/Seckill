package miaoshao.service;

import miaoshao.dao.MiaoshaUserDao;
import miaoshao.result.CodeMsg;
import miaoshao.util.MD5Util;
import miaoshao.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miaoshao.domain.MiaoshaUser;
@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo){
        if(loginVo==null){
            return CodeMsg.SEVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //judge if the user exist or not.
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null){
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        //judge password
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass =  MD5Util.formPassToDBPass(formPass,saltDB);
        if(calcPass.equals(dbPass)){
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;

    }
}

