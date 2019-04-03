package com.ccnu.test.quartz2.dynamic;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by gongyb08837 on 2016/1/10.
 */
public class PrintSysTimeJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("现在时间是 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.print(context.getMergedJobDataMap().get("testkey"));
    }
}
