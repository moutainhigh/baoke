package com.baoke.job.task;

public class Job {

	// private Log log = LogFactory.getLog(Job.class);
	//
	// private MasterManager masterManager;
	//
	// public void setMasterManager(MasterManager masterManager) {
	// this.masterManager = masterManager;
	// }
	//
	/**
	 * 无线日活跃用户统计报表
	 */
	public void insertReportRecord() {
		System.out.println("insertReportRecord定时启动");
	}
	//
	// /**
	// * 无线日活跃用户统计报表
	// */
	// public void insertVisitUserReportRecord() {
	// log.error("begin to start insertVisitUserReportRecord");
	// try {
	// masterManager.insertVisitUserReportRecord();
	// } catch (ManagerException e) {
	// log.error("JOB insertVisitUserReportRecord error" + e.getMessage(), e);
	// }
	// log.error("end to start insertVisitUserReportRecord");
	// }
	//
	// /**
	// * 无线日新增设备统计报表
	// */
	// public void insertNewDeviceReportRecord() {
	// log.error("begin to start insertReportRecord");
	// try {
	// masterManager.insertNewDeviceReportRecord();
	// } catch (ManagerException e) {
	// log.error("JOB insertNewDeviceReportRecord error" + e.getMessage(), e);
	// }
	// log.error("end to start insertReportRecord");
	// }

}
