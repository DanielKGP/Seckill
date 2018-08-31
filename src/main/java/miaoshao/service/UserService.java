package miaoshao.service;

import miaoshao.dao.UserDao;
import miaoshao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id){
        return userDao.getByID(id);
    }

}
