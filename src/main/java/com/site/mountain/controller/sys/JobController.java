package com.site.mountain.controller.sys;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.JobAndTrigger;
import com.site.mountain.entity.JobBean;
import com.site.mountain.job.BaseJob;
import com.site.mountain.service.IJobAndTriggerService;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/job")
public class JobController {
    @Autowired
    private IJobAndTriggerService iJobAndTriggerService;

    //加入Qulifier注解，通过名称注入bean
    @Autowired
    private Scheduler scheduler;

    private static Logger log = LoggerFactory.getLogger(JobController.class);


    @PostMapping(value = "/addjob")
    public Map addjob(@RequestBody JobBean jobBean) throws Exception {
        Map map = new HashMap();
        map.put("code", 20000);
        map.put("state", 30000);
        try{
            addJob(jobBean.getJobClassName(), jobBean.getJobGroupName(), jobBean.getCronExpression());
        }catch (Exception e){
            e.printStackTrace();
            map.put("state", 30001);
        }
        return map;
    }

    public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }


    @PostMapping(value = "/pausejob")
    public Map pausejob(@RequestBody JobBean jobBean) throws Exception {
        Map map = new HashMap();
        map.put("code", 20000);
        map.put("state", 30000);
        try {
            jobPause(jobBean.getJobClassName(), jobBean.getJobGroupName());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", 30001);
        }
        return map;
    }

    public void jobPause(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @PostMapping(value = "/resumejob")
    public Map resumejob(@RequestBody JobBean jobBean) throws Exception {
        Map map = new HashMap();
        map.put("code", 20000);
        map.put("state", 30000);
        try {
            jobresume(jobBean.getJobClassName(), jobBean.getJobGroupName());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", 30001);
        } finally {

        }
        return map;
    }

    public void jobresume(String jobClassName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @PostMapping(value = "/reschedulejob")
    public Map rescheduleJob(@RequestBody JobBean jobBean) throws Exception {
        Map map = new HashMap();
        map.put("code", 20000);
        map.put("state", 30000);
        try {
            jobreschedule(jobBean.getJobClassName(), jobBean.getJobGroupName(), jobBean.getCronExpression());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", 30001);
        }
        return map;
    }

    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
    }


    @PostMapping(value = "/deletejob")
    public Map deletejob(@RequestBody JobBean jobBean) throws Exception {
        Map map = new HashMap();
        map.put("code", 20000);
        map.put("state", 30000);
        try {
            jobdelete(jobBean.getJobClassName(), jobBean.getJobGroupName());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", 30001);
        }
        return map;
    }

    public void jobdelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @RequestMapping(value = "/queryjob")
    public Map<String, Object> queryjob(@RequestBody JobBean jobBean) {
        PageInfo<JobAndTrigger> jobAndTrigger = iJobAndTriggerService.getJobAndTriggerDetails(jobBean.getPageNum(), jobBean.getPageSize());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        map.put("code", 20000);
        return map;
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }
}
