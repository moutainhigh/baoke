package com.baoke.job.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.job.dao.QuartzJobDao;
import com.baoke.job.domain.SchedulingJob;
import com.baoke.job.manager.SchedulingJobManager;
import com.baoke.job.util.QuartzHelper;
import com.baoke.job.util.QuartzJobBean;

/**
 * 定时任务管理实现类
 * 
 */
@DataSource("miscDataSource")
@Component
public class SchedulingJobManagerImpl implements SchedulingJobManager {

	private Log log = LogFactory.getLog(SchedulingJobManagerImpl.class);

	@Resource
	private QuartzJobDao schedulingJobDao;

	@Override
	public void start(int id) throws Exception {
		log.error("SchedulingJobManagerImpl start id=" + id);
		SchedulingJob schedulingJob = schedulingJobDao.querySchedulingJob(id);
		enable(schedulingJob);
		schedulingJob.setJobStatus(JOB_STATUS_ON);
		log.error("SchedulingJobManagerImpl start id update 1");
		schedulingJobDao.modifySchedulingJob(schedulingJob);
		log.error("SchedulingJobManagerImpl start id update 2");
	}

	@Override
	public void start(SchedulingJob schedulingJob) throws Exception {
		log.error("SchedulingJobManagerImpl start schedulingJob.id=" + schedulingJob.getId());
		enable(schedulingJob);
		schedulingJob.setJobStatus(JOB_STATUS_ON);
		log.error("SchedulingJobManagerImpl start schedulingJob update 1");
		schedulingJobDao.modifySchedulingJob(schedulingJob);
		log.error("SchedulingJobManagerImpl start schedulingJob update 2");
	}

	@Override
	public void add(SchedulingJob schedulingJob) throws Exception {
		schedulingJobDao.addSchedulingJob(schedulingJob);
		enable(schedulingJob);
	}

	@Override
	public void update(SchedulingJob schedulingJob) throws Exception {
		SchedulingJob old = schedulingJobDao.querySchedulingJob(Integer.parseInt(schedulingJob.getId()));
		diable(old);
		schedulingJobDao.modifySchedulingJob(schedulingJob);
		if (JOB_STATUS_ON.equalsIgnoreCase(schedulingJob.getJobStatus())) {
			enable(schedulingJob);
		}
	}

	@Override
	public void delete(int id) throws Exception {
		SchedulingJob schedulingJob = schedulingJobDao.querySchedulingJob(id);
		diable(schedulingJob);
		schedulingJobDao.deleteSchedulingJob(id);
	}

	@Override
	public void stop(int id) throws Exception {
		SchedulingJob schedulingJob = schedulingJobDao.querySchedulingJob(id);
		diable(schedulingJob);
		schedulingJob.setJobStatus(JOB_STATUS_OFF);
		schedulingJobDao.modifySchedulingJob(schedulingJob);
	}

	@Override
	public void startAll() throws Exception {
		List<SchedulingJob> list = schedulingJobDao.querySchedulingJobList();
		if (list != null) {
			for (SchedulingJob sj : list) {
				if (JOB_STATUS_OFF.equalsIgnoreCase(sj.getJobStatus())) {
					sj.setJobStatus(JOB_STATUS_ON);
					schedulingJobDao.modifySchedulingJob(sj);
					enable(sj);
				}
			}
		}
	}

	@Override
	public void stopAll() throws Exception {
		List<SchedulingJob> list = schedulingJobDao.querySchedulingJobList();
		if (list != null) {
			for (SchedulingJob sj : list) {
				if (JOB_STATUS_ON.equalsIgnoreCase(sj.getJobStatus())) {
					sj.setJobStatus(JOB_STATUS_OFF);
					schedulingJobDao.modifySchedulingJob(sj);
					diable(sj);
				}
			}
		}
	}

	@Override
	public List<SchedulingJob> getJobList() throws Exception {
		return schedulingJobDao.querySchedulingJobList();
	}

	@Override
	public SchedulingJob getJob(int id) throws Exception {
		return schedulingJobDao.querySchedulingJob(id);
	}

	/**
	 * 停止任务
	 * 
	 * @param schedulingJob
	 * @throws Exception
	 */
	private void diable(SchedulingJob schedulingJob) throws Exception {
		Scheduler scheduler = QuartzHelper.getScheduler();
		Trigger trigger = scheduler.getTrigger(schedulingJob.getTriggerName(), schedulingJob.getJobGroup());
		if (null != trigger) {
			scheduler.deleteJob(schedulingJob.getJobName(), schedulingJob.getJobGroup());
		}
	}

	/**
	 * 启动任务
	 * 
	 * @param schedulingJob
	 * @throws Exception
	 */
	Scheduler scheduler = QuartzHelper.getScheduler();

	private void enable(SchedulingJob schedulingJob) throws Exception {
		log.error("SchedulingJobManagerImpl enable 1");
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(schedulingJob.getTriggerName(),
				schedulingJob.getJobGroup());
		log.error("SchedulingJobManagerImpl enable 2");
		if (null == trigger) {
			log.error("SchedulingJobManagerImpl enable 3");
			// Trigger不存在，那么创建一个
			JobDetail jobDetail = new JobDetail(schedulingJob.getJobName(), schedulingJob.getJobGroup(),
					QuartzJobBean.class);
			log.error("SchedulingJobManagerImpl enable 3.1");
			jobDetail.getJobDataMap().put(QuartzJobBean.TARGET_CLASS, schedulingJob.getJobClass());
			log.error("SchedulingJobManagerImpl enable 3.2");
			jobDetail.getJobDataMap().put(QuartzJobBean.TARGET_METHOD, schedulingJob.getJobMethod());
			log.error("SchedulingJobManagerImpl enable 3.3");
			jobDetail.getJobDataMap().put(QuartzJobBean.TARGET_ARGUMENTS, schedulingJob.getMethodArgs());
			log.error("SchedulingJobManagerImpl enable 3.4");

			trigger = new CronTrigger(schedulingJob.getTriggerName(), schedulingJob.getJobGroup(),
					schedulingJob.getCronExpression());
			log.error("SchedulingJobManagerImpl enable 3.5");
			scheduler.scheduleJob(jobDetail, trigger);
			log.error("SchedulingJobManagerImpl enable 3.6");
		} else {
			// Trigger已存在，那么更新相应的定时设置
			log.error("SchedulingJobManagerImpl enable 4");
			trigger.setCronExpression(schedulingJob.getCronExpression());
			scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(), trigger);
		}
		scheduler.start();
	}

}
