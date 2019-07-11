package com.site.mountain.service;


import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.JobAndTrigger;

public interface IJobAndTriggerService {
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
