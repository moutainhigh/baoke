package com.baoke.job.dao;

import java.util.List;

import com.baoke.job.domain.SchedulingJob;

public interface QuartzJobDao {

	int addSchedulingJob(SchedulingJob record);

	int modifySchedulingJob(SchedulingJob record);

	int deleteSchedulingJob(Integer id);

	SchedulingJob querySchedulingJob(Integer id);

	List<SchedulingJob> querySchedulingJobList();
}