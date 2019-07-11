package com.site.mountain.job.impl;

import com.site.mountain.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class HelloJob implements BaseJob {
    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        _log.error("Hello Job执行时间: " + new Date());
    }
}
