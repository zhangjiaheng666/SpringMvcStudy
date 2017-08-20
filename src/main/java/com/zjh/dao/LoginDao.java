package com.zjh.dao;

import com.zjh.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Jiaheng on 2017/8/20.
 */
@Repository
public class LoginDao {
    private JdbcTemplate jdbcTemplate;

    private final static String INSERT_LOGIN_LOG_SQL = " INSERT INTO " +
            " t_login_log(userid,ip,login_datetime) VALUES(?,?,?)";

    public void insertLoginLog(LoginLog loginLog){
        Object[] args = {loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()};
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,args);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
