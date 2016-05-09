package com.ccnu.test.quartz2.dynamic;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by gongyb08837 on 2015/11/26.
 */
public class AppTest {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/com/ccnu/test/quartz2/dynamic/applicationContext.xml");
        System.in.read();
    }
}
