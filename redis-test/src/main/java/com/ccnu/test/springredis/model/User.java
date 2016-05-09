package com.ccnu.test.springredis.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gongyb08837 on 2015/11/14.
 */
public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2892289358484499459L;
	private long id;
    private String name;
    private int age;
    private Date birthday;

    private static final String OBJECT_KEY = "User";

    public User(){

    }

    public User(int age, Date birthday, long id, String name) {
        this.age = age;
        this.birthday = birthday;
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public static String getObjectKey() {
        return OBJECT_KEY;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
