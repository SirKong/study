package com.ccnu.test.quartz2.plain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * Created by gongyb08837 on 2015/11/26.
 */
public class JobBean {
    private static final Log log = LogFactory.getLog(JobBean.class);

    public void run(){
        log.info("开始定时任务执行"+new Date());
    }
}
