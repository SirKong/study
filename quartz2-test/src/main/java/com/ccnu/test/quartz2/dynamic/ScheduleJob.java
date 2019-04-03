package com.ccnu.test.quartz2.dynamic;

import java.util.Map;

/**
 * Created by gongyb08837 on 2016/1/11.
 */
public class ScheduleJob {
    /**
     * 任务id
     */
    private String jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务状态 0禁用 1启用
     */
    private boolean enabled;
    /**
     * 是否启动即运行
     */
    private boolean bootOnStartup;
    /**
     * 任务运行时间表达式
     */
    private String cronExpression;
    /**
     * 定时任务执行类
     */
    private Class<?> jobClass;
    /**
     * jobDataMap
     */
    private Map<String, ?> jobDataMap;
    /**
     * 任务描述
     */
    private String desc;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public boolean isBootOnStartup() {
        return bootOnStartup;
    }

    public void setBootOnStartup(boolean bootOnStartup) {
        this.bootOnStartup = bootOnStartup;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setJobClass(Class<?> jobClass) {
        this.jobClass = jobClass;
    }

    public Class<?> getJobClass() {
        return jobClass;
    }

    public void setJobDataMap(Map<String, ?> jobDataMap) {
        this.jobDataMap = jobDataMap;
    }

    public Map<String, ?> getJobDataMap() {
        return jobDataMap;
    }

    @Override
    public String toString() {
        return "ScheduleJob{" +
                "bootOnStartup=" + bootOnStartup +
                ", jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", enabled=" + enabled +
                ", cronExpression='" + cronExpression + '\'' +
                ", desc='" + desc + '\'' +
                ", jobClass='" + jobClass + '\'' +
                ", jobDataMap='" + jobDataMap + '\'' +
                '}';
    }
}
