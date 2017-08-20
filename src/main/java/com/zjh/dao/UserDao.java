package com.zjh.dao;

import com.zjh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jiaheng on 2017/8/20.
 */
@Repository//通过spring注解定义一个DAO
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_USER = " SELECT * FROM "+
            " t_user WHERE user_name =? and password=? ";
    private final static String MATCH_COUNT_SQL = " SELECT count(*) FROM "+
            " t_user WHERE user_name =? and password=? ";
    private final static  String UPDATE_LOGIN_INFO_SQL = " UPDATE t_user SET "+
            " last_visit=?,last_ip=?,credits=? WHERE user_id = ?";
    @Autowired //自动注入JdbcTemplate的Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    //通过数据库获取用户个数,用来判断是否合法
    public int getMatchCount(String userName,String password){
        return jdbcTemplate.queryForInt(MATCH_COUNT_SQL,new Object[]{userName,password});
    }

    public User findUserByUserName(final String userName)
    {
        final User user = new User();
        jdbcTemplate.query(MATCH_USER, new Object[]{ userName },
        //匿名方式实现回调函数
        new RowCallbackHandler(){
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit()
        ,user.getLastIp(),user.getCredits(),user.getUserId()});
    }
}
