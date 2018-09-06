package com.baoke.job.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baoke.job.domain.SchedulingJob;
import com.baoke.job.manager.SchedulingJobManager;
import com.baoke.job.util.ExceptionUtils;
import com.baoke.job.util.QuartzJobBean;

/**
 * 任务管理控制类
 *
 */
@Controller
@RequestMapping("/job")
public class QuartzJobController implements ApplicationListener<ContextRefreshedEvent> {

	private Log log = LogFactory.getLog(QuartzJobController.class);
	// 参数id
	private static final String ID = "id";
	// 错误信息----参数错误
	private static final String PARAM_ERROR = "参数错误";
	// 错误信息的key值
	private static final String MESSAGE = "message";

	@Resource
	private SchedulingJobManager schedulingJobManager;

	/**
	 * 保存任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("save")
	public ModelAndView saveJob(HttpServletRequest request, HttpServletResponse response) {
		log.error("begin to execute saveJob");
		ModelMap model = new ModelMap();
		SchedulingJob sj = new SchedulingJob();
		if (handleRequest(request, sj)) {
			try {
				if (StringUtils.isEmpty(sj.getId())) {
					// 添加任务
					schedulingJobManager.add(sj);
					log.error("begin to execute addJob success.");
				} else {
					// 修改任务
					schedulingJobManager.update(sj);
				}
				List<SchedulingJob> list = schedulingJobManager.getJobList();
				model.put("list", list);
			} catch (Exception e) {
				log.error(ExceptionUtils.getDetailMessage(e));
				model.put(MESSAGE, e.getMessage());
				return new ModelAndView("error", model);
			}
		} else {
			model.put(MESSAGE, PARAM_ERROR);
			return new ModelAndView("error", model);
		}
		log.error("end to execute saveJob, id=" + sj.getId());
		return new ModelAndView("redirect:/job/list", model);
	}

	/**
	 * 跳转到更新任务页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("update")
	public ModelAndView updateJob(HttpServletRequest request, HttpServletResponse response) {
		ModelMap model = new ModelMap();
		String id = null;
		try {
			id = request.getParameter(ID);
			log.error("begin to execute updateJob, id=" + id);
			if (StringUtils.isEmpty(id)) {
				model.put(MESSAGE, PARAM_ERROR);
				return new ModelAndView("error", model);
			}
			SchedulingJob sj = schedulingJobManager.getJob(Integer.parseInt(id));
			model.put("sj", sj);
		} catch (Exception e) {
			log.error(ExceptionUtils.getDetailMessage(e));
			model.put(MESSAGE, e.getMessage());
			return new ModelAndView("error", model);
		}
		log.error("end to execute updateJob, id=" + id);
		return new ModelAndView("jobdetail", model);
	}

	/**
	 * 跳转到任务列表页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView listJob(HttpServletRequest request, HttpServletResponse response) {
		ModelMap model = new ModelMap();
		try {
			List<SchedulingJob> list = schedulingJobManager.getJobList();
			model.put("list", list);
		} catch (Exception e) {
			log.error(ExceptionUtils.getDetailMessage(e));
			model.put(MESSAGE, e.getMessage());
			return new ModelAndView("error", model);
		}
		return new ModelAndView("joblist", model);
	}

	/**
	 * 删除任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("delete")
	public ModelAndView deleteJob(HttpServletRequest request, HttpServletResponse response) {
		ModelMap model = new ModelMap();
		String id = null;
		try {
			id = request.getParameter(ID);
			log.error("begin to execute deleteJob, id=" + id);
			if (StringUtils.isEmpty(id)) {
				model.put(MESSAGE, PARAM_ERROR);
				return new ModelAndView("error", model);
			}
			schedulingJobManager.delete(Integer.parseInt(id));
			List<SchedulingJob> list = schedulingJobManager.getJobList();
			model.put("list", list);
		} catch (Exception e) {
			log.error(ExceptionUtils.getDetailMessage(e));
			model.put(MESSAGE, e.getMessage());
			return new ModelAndView("error", model);
		}
		log.error("end to execute deleteJob, id=" + id);
		return new ModelAndView("redirect:/job/list", model);
	}

	/**
	 * 启动任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("start")
	public ModelAndView startJob(HttpServletRequest request, HttpServletResponse response) {
		ModelMap model = new ModelMap();
		String id = null;
		try {
			id = request.getParameter(ID);
			log.error("begin to execute startJob, id=" + id);
			if (StringUtils.isEmpty(id)) {
				model.put(MESSAGE, PARAM_ERROR);
				return new ModelAndView("error", model);
			}
			schedulingJobManager.start(Integer.parseInt(id));
			List<SchedulingJob> list = schedulingJobManager.getJobList();
			model.put("list", list);
		} catch (Exception e) {
			log.error(ExceptionUtils.getDetailMessage(e));
			model.put(MESSAGE, e.getMessage());
			return new ModelAndView("error", model);
		}
		log.error("end to execute startJob, id=" + id);
		return new ModelAndView("redirect:/job/list", model);
	}

	/**
	 * 停止任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("stop")
	public ModelAndView stopJob(HttpServletRequest request, HttpServletResponse response) {
		ModelMap model = new ModelMap();
		String id = null;
		try {
			id = request.getParameter(ID);
			log.error("begin to execute stopJob, id=" + id);
			if (StringUtils.isEmpty(id)) {
				model.put(MESSAGE, PARAM_ERROR);
				return new ModelAndView("error", model);
			}
			schedulingJobManager.stop(Integer.parseInt(id));
			List<SchedulingJob> list = schedulingJobManager.getJobList();
			model.put("list", list);
		} catch (Exception e) {
			log.error(ExceptionUtils.getDetailMessage(e));
			model.put(MESSAGE, e.getMessage());
			return new ModelAndView("error", model);
		}
		log.error("end to execute stopJob, id=" + id);
		return new ModelAndView("redirect:/job/list", model);
	}

	/**
	 * 处理request请求，取出里面的参数set到SchedulingJob中
	 * 
	 * @param request
	 * @param sj
	 * @return
	 */
	private boolean handleRequest(HttpServletRequest request, SchedulingJob sj) {
		String id = request.getParameter(ID);
		String jobClass = request.getParameter("jobClass");
		String jobMethod = request.getParameter("jobMethod");
		String jobGroup = request.getParameter("jobGroup");
		String jobName = request.getParameter("jobName");
		String jobStatus = request.getParameter("jobStatus");
		String description = request.getParameter("description");
		String cronExpression = request.getParameter("cronExpression");
		String methodArgs = request.getParameter("methodArgs");

		if (StringUtils.isEmpty(cronExpression) || StringUtils.isEmpty(jobClass) || StringUtils.isEmpty(jobName)
				|| StringUtils.isEmpty(jobGroup) || StringUtils.isEmpty(jobMethod)) {
			return false;
		} else {
			if (StringUtils.isEmpty(jobStatus)) {
				jobStatus = "0";
			}
			sj.setId(id);
			sj.setCronExpression(cronExpression);
			sj.setDescription(description);
			sj.setJobClass(jobClass);
			sj.setJobGroup(jobGroup);
			sj.setJobMethod(jobMethod);
			sj.setJobName(jobName);
			sj.setJobStatus(jobStatus);
			sj.setMethodArgs(methodArgs);
		}

		return true;
	}

	/**
	 * 初始化方法，在容器启动的时候调用，开始所有状态为“0”的任务
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() != null) {
			return;
		}

		log.error("start init QuartzJobController");

		QuartzJobBean.setAc(event.getApplicationContext());
		List<SchedulingJob> list = null;
		try {
			list = schedulingJobManager.getJobList();
		} catch (Exception e) {
			log.error("QuartzJobController init error when load job list", e);
		}

		if (list != null) {
			for (SchedulingJob sj : list) {
				if (SchedulingJobManager.JOB_STATUS_ON.equalsIgnoreCase(sj.getJobStatus())) {
					try {
						log.error("start job ->" + sj.getJobName());
						schedulingJobManager.start(sj);
					} catch (Exception e) {
						log.error("QuartzJobController init error when start job " + sj.toString(), e);
					}
				}
			}
		}

		log.error("end init QuartzJobController");
	}

}
