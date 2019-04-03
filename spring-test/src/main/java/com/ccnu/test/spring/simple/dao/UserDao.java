package com.ccnu.test.spring.simple.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ccnu.test.spring.simple.model.User;
import com.ccnu.test.spring.simple.util.ApplicationContextUtil;

/**
 * Created by gongyb08837 on 2015/11/3.
 */
public class UserDao {
    public void insert(User user) {
        DataSource dataSource = (DataSource) ApplicationContextUtil.getBean("dataSource", DataSource.class);
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        String sqlInsert = "INSERT INTO USERTABLE VALUES (?,?)";
        jdbcTemplate.update(sqlInsert, user.getId(), user.getUsername());
    }
}
