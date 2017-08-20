package com.zjh.service;

import com.zjh.dao.LoginDao;
import com.zjh.dao.UserDao;
import com.zjh.domain.LoginLog;
import com.zjh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jiaheng on 2017/8/20.
 */
@Service
public class UserService {

    private UserDao userDao;
    private LoginDao loginDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginDao(LoginDao loginDao){
        this.loginDao = loginDao;
    }

    public boolean hasMatchUser(String userName,String password){
        int matchCount = userDao.getMatchCount(userName,password);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginDao.insertLoginLog(loginLog);
    }
}
