package com.baoke.log.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baoke.log.domain.LogStatPv;
import com.baoke.log.domain.LogStatUv;
import com.baoke.log.manager.LogStatPvManager;
import com.baoke.log.manager.LogStatUvManager;

/**
 * pv,uv,入库处理定时类
 * 
 * @author zjm
 * @Date: 2018年5月18日 上午10:04:44
 */
@Service("StatExcutorTask")
public class StatExcutorTask {
	private static final Logger logger = LoggerFactory.getLogger(StatExcutorTask.class);
	@Resource
	private LogStatPvManager logStatPvManager;

	@Resource
	private LogStatUvManager logStatUvManager;

	private static final ScheduledExecutorService excutor = Executors.newSingleThreadScheduledExecutor();

	private static final int MAX_SIZE = 10000;
	public static BlockingDeque<LogStatPv> pvDeque = new LinkedBlockingDeque<LogStatPv>(MAX_SIZE);
	public static BlockingDeque<LogStatUv> uvDeque = new LinkedBlockingDeque<LogStatUv>(MAX_SIZE);

	private static final long TIME_SECONDS = 10;// 秒

	@PostConstruct
	public void init() {
		excutor.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				try {
					// PV
					Object[] pvList = pvDeque.toArray();
					pvDeque.clear();
					savePvLog(pvList);

					// UV
					Object[] uvList = uvDeque.toArray();
					uvDeque.clear();
					saveUvLog(uvList);

				} catch (Exception e) {
					logger.error("save pv/uv log error:", e);
				}
			}

			private void saveUvLog(Object[] uvList) {
				Map<String, LogStatUv> uvMap = new HashMap<String, LogStatUv>();
				for (Object object : uvList) {
					LogStatUv logStatUv = (LogStatUv) object;
					if (null == uvMap.get(logStatUv.getKey())) {
						uvMap.put(logStatUv.getKey(), logStatUv);
					} else {
						LogStatUv logStatUvTemp = uvMap.get(logStatUv.getKey());
						logStatUvTemp.setValue(logStatUvTemp.getValue() + logStatUv.getValue());
					}
				}

				for (Entry<String, LogStatUv> enty : uvMap.entrySet()) {
					if (null != enty.getValue().getUnionId() && !"".equals(enty.getValue().getUnionId())) {
						int count = logStatUvManager.selectByScen(enty.getValue());
						if (count > 0) {
							logStatUvManager.updateByScen(enty.getValue());
						} else {
							logStatUvManager.insert(enty.getValue());
						}

						logger.info(String.format("save uv log, key={%s}, value={%s}",
								new Object[] { enty.getKey(), enty.getValue() }));
					}
				}
			}

			private void savePvLog(Object[] pvList) {
				Map<String, LogStatPv> pvMap = new HashMap<String, LogStatPv>();
				for (Object object : pvList) {
					LogStatPv logStatPv = (LogStatPv) object;
					if (null == pvMap.get(logStatPv.getKey())) {
						pvMap.put(logStatPv.getKey(), logStatPv);
					} else {
						LogStatPv logStatPvTemp = pvMap.get(logStatPv.getKey());
						logStatPvTemp.setValue(logStatPvTemp.getValue() + logStatPv.getValue());
					}

				}

				for (Entry<String, LogStatPv> enty : pvMap.entrySet()) {
					int pvCount = logStatPvManager.selectByScen(enty.getValue());
					if (pvCount > 0) {
						logStatPvManager.updateByScen(enty.getValue());
					} else {
						logStatPvManager.insert(enty.getValue());
					}
					logger.info(String.format("save pv log, key={%s}, value={%s}",
							new Object[] { enty.getKey(), enty.getValue() }));

				}
			}
		}, 0, TIME_SECONDS, TimeUnit.SECONDS);

	}

}
