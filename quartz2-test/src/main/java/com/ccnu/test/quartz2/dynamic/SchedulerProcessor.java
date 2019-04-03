package com.ccnu.test.quartz2.dynamic;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

/**
 * Created by gongyb08837 on 2016/1/10.
 */
public class SchedulerProcessor extends AbstractProcessor {
    public static final String TASK_ENABLED = "1";
    public static final String TASK_EXECUTE_ON_BOOTSTRAP = "1";
    public static final String JOB_PARAM_KEY = "jobParam";

    private static Scheduler scheduler;

    private List<ScheduleJob> scheduleJobs;

    @Override
    protected void execute(ApplicationContext applicationContext) throws Exception {
        //schedulerFactoryBean 由spring创建注入
        for (ScheduleJob job : scheduleJobs) {
            if (!job.isEnabled()) {
                continue;
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //不存在，创建一个
            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob()
                        .withIdentity(job.getJobName(), job.getJobGroup()).build();
                if (!CollectionUtils.isEmpty(job.getJobDataMap())) {
                    Map<String, ?> jobDataMap = job.getJobDataMap();
                    Iterator<String> it = jobDataMap.keySet().iterator();
                    while (it.hasNext()) {
                        String key = it.next();
                        jobDetail.getJobDataMap().put(key, jobDataMap.get(key));
                    }
                }
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());
                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                // Trigger已存在，那么更新相应的定时设置
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());
                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();
                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
            if (job.isBootOnStartup()) {
                JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
                scheduler.triggerJob(jobKey);
            }
        }
    }

    public static void pauseJob(JobKey jobKey) {
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error(String.format("暂停定时任务[%s]失败！", jobKey));
        }
    }

    public static void resumeJob(JobKey jobKey) {
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error(String.format("恢复定时任务[%s]失败！", jobKey));
        }
    }


    public static void deleteJob(JobKey jobKey) {
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error(String.format("删除定时任务[%s]失败！", jobKey));
        }
    }

    public static void triggerJob(JobKey jobKey) {
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error(String.format("立即触发执行定时任务[%s]失败！", jobKey));
        }
    }

    public void setScheduler(Scheduler scheduler) {
        SchedulerProcessor.scheduler = scheduler;
    }

    public void setScheduleJobs(List<ScheduleJob> scheduleJobs) {
        this.scheduleJobs = scheduleJobs;
    }
}
