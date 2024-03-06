package com.app.dc.service;

import com.app.common.db.DBUtils;
import com.app.common.db.IDatabaseConnection;
import com.app.dc.service.job.*;
import com.app.dc.util.Consts;
import com.app.dc.utils.FeeRateUtils;
import com.app.dc.utils.TradeSvrClient;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.common.utils.Schedule;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;

/**
 * @Description
 * @Author
 * @Date
 **/
@Slf4j
@Component
public class BatchStart implements CommandLineRunner {
	@Value("${Schedule.Config}")
	private String scheduleConfig;
	@Value("${Cointelegraph.SpiPictCron}")
	private String CointelegraphSpiPictCron;

	@Value("${Cointelegraph.SpiPictSavePath}")
	private String CointelegraphSpiPictSavePath;
	@Value("${Cointelegraph.SpiPictEnable}")
	private boolean CointelegraphSpiPictEnable;

	@Value("${Binance.SpiPictCron}")
	private String BinanceSpiPictCron;

	@Value("${Binance.SpiPictSavePath}")
	private String BinanceSpiPictSavePath;
	@Value("${Binance.SpiPictEnable}")
	private boolean BinanceSpiPictEnable;

	@Value("${Binance.SpiArticlesCron}")
	private String BinanceSpiArticlesCron;

	@Value("${Binance.SpiArticlesSavePath}")
	private String BinanceSpiArticlesSavePath;
	@Value("${Binance.SpiArticlesEnable}")
	private boolean BinanceSpiArticlesEnable;

	@Value("${Kol.Cron}")
	private String KolCron;
	@Value("${Kol.Enable}")
	private boolean KolEnable;


	@Value("${Kol.grade.Cron}")
	private String KolGradeCron;

	private Schedule schedule = new Schedule();


	@Value("${activity2Cron}")
	private String activity2Cron;

	@Value("${runActivity2Enable:false}")
	private boolean runActivity2Enable;

	@Value("${activityTotalAssets12Cron}")
	private String activityTotalAssets12Cron;

	@Value("${activityTotalAssetsCron}")
	private String activityTotalAssetsCron;

	@Value("${runActivityTotalAssets12Enable:false}")
	private boolean runActivityTotalAssets12Enable;


	@Value("${runActivityTotalAssetsEnable:false}")
	private boolean runActivityTotalAssetsEnable;

	@Value("${runFeeDiscountRatioEnable:false}")
	private boolean runFeeDiscountRatioEnable;
	@Value("${feeDiscountRatioCron}")
	private String feeDiscountRatioCron;


	@Value("${runDataSummaryEnable:false}")
	private boolean runDataSummaryEnable;
	@Value("${dataSummaryCron}")
	private String dataSummaryCron;

	@Value("${BachCount:10000}")
	private int BachCount;


	@Autowired
	private TradeSvrClient tradeSvrClient;

	@Autowired
	private FeeRateUtils feeRateUtils;


	@Override
	public void run(String... args) throws Exception {
		IDatabaseConnection databaseConnection=new IDatabaseConnection() {

			@Override
			public Connection getConnection() {
				try {
					return DBUtils.dbpool.getLongConnection();
				}
				catch(Exception e) {
					return null;
				}
			}

			@Override
			public void freeConnection(Connection connection) {
				try {
					DBUtils.dbpool.freeConnection(connection);
				}
				catch(Exception e) {

				}
			}
		};

		DBUtils.setDatabaseConnection(databaseConnection);

		tradeSvrClient.init();
		Consts.BachCount = BachCount;
		log.info("BachCount:{}", Consts.BachCount);
		log.info("Schedule.Config:{}", scheduleConfig);
		log.info("Cointelegraph.SpiPictCron:{}", CointelegraphSpiPictCron);
		log.info("Cointelegraph.SpiPictSavePath:{}", CointelegraphSpiPictSavePath);
		log.info("Cointelegraph.SpiPictEnable:{}", CointelegraphSpiPictEnable);

		log.info("Binance.SpiPictCron:{}", BinanceSpiPictCron);
		log.info("Binance.SpiPictSavePath:{}", BinanceSpiPictSavePath);
		log.info("Binance.SpiPictEnable:{}", BinanceSpiPictEnable);

		CointelegraphSpiPictJob.picPath = CointelegraphSpiPictSavePath;
		BinanceSpiPictJob.picPath = BinanceSpiPictSavePath;
		schedule.init(scheduleConfig);
		runSpiPictJob();
		if (BinanceSpiPictEnable) {
			schedule.updateJob(BinanceSpiPictCron, "", BinanceSpiPictJob.class);
		}
		if (CointelegraphSpiPictEnable) {
			schedule.updateJob(CointelegraphSpiPictCron, "", CointelegraphSpiPictJob.class);
		}

		log.info("Binance.SpiArticlesCron:{}", BinanceSpiArticlesCron);
		log.info("Binance.SpiArticlesSavePath:{}", BinanceSpiArticlesSavePath);
		log.info("Binance.SpiArticlesEnable:{}", BinanceSpiArticlesEnable);
		BinanceSpiArticlesJob.articlesPath = BinanceSpiArticlesSavePath;
		runBinanceSpiArticlesJob();
		if (BinanceSpiArticlesEnable) {
			schedule.updateJob(BinanceSpiArticlesCron, "", BinanceSpiArticlesJob.class);
		}

		log.info("Kol.Cron:{}", KolCron);

		log.info("Kol.Enable:{}", KolEnable);
		if(KolEnable) {
			schedule.updateJob(KolCron, "", KolJob.class);

		}
		log.info("Kol.grade.Cron:{}", KolGradeCron);
		schedule.updateJob(KolGradeCron, "", KolGradeJob.class);


		log.info("activity2Cron:{}", activity2Cron);
		schedule.updateJob(activity2Cron, "", Activity2Job.class);

		log.info("runActivity2Enable:{}", runActivity2Enable);
		log.info("runActivityTotalAssets12Enable:{}", runActivityTotalAssets12Enable);
		log.info("activityTotalAssets12Cron:{}", activityTotalAssets12Cron);
		schedule.updateJob(activityTotalAssets12Cron, "1", ActivityTotalAssets12Job.class);

		log.info("runActivityTotalAssetsEnable:{}", runActivityTotalAssetsEnable);
		log.info("activityTotalAssetsCron:{}", activityTotalAssetsCron);
		schedule.updateJob(activityTotalAssetsCron, "2", ActivityTotalAssetsJob.class);




		if (runActivityTotalAssetsEnable) {
			runActivityTotalAssetsJob();
		}

		if (runActivityTotalAssets12Enable) {
			runActivityTotalAssets12Job();
		}

		runKolGradeJob();
		runKolJob();
		if(!runActivityTotalAssets12Enable && !runActivityTotalAssetsEnable) {
//			runActivity2Job();
			if (!KolEnable && runActivity2Enable) {
				runActivity2Job();
			}
		}



		log.info("runFeeDiscountRatioEnable:{}", runFeeDiscountRatioEnable);
		log.info("feeDiscountRatioCron:{}", feeDiscountRatioCron);
		if (runFeeDiscountRatioEnable){
			runFeeDiscountRatioJob();
		}
		schedule.updateJob(feeDiscountRatioCron, "feeDiscountRatioCron", FeeDiscountRatioJob.class,feeRateUtils);

		log.info("runDataSummaryEnable:{}", runDataSummaryEnable);
		log.info("dataSummaryCron:{}", dataSummaryCron);
		if (runDataSummaryEnable){
			runDataSummaryJob();
		}
		schedule.updateJob(dataSummaryCron, "DataSummaryJob", DataSummaryJob.class);

		schedule.start();
	}

	private void runSpiPictJob() {
		if (CointelegraphSpiPictEnable) {
			Runnable target = () -> {
				CointelegraphSpiPictJob job = new CointelegraphSpiPictJob();
				try {
					job.execute(null);
				} catch (JobExecutionException e) {
					log.error(e.getMessage());
				}
			};
			new Thread(target).start();
		}
		if (BinanceSpiPictEnable) {
			Runnable target2 = () -> {
				BinanceSpiPictJob job = new BinanceSpiPictJob();
				try {
					job.execute(null);
				} catch (JobExecutionException e) {
					log.error(e.getMessage());
				}
			};
			new Thread(target2).start();
		}

	}

	private void runBinanceSpiArticlesJob() {
		if (BinanceSpiArticlesEnable) {
			Runnable target3 = () -> {
				BinanceSpiArticlesJob job = new BinanceSpiArticlesJob();
				try {
					job.execute(null);
				} catch (JobExecutionException e) {
					log.error(e.getMessage());
				}
			};
			new Thread(target3).start();
		}
	}

	private void runKolJob() {
		if (KolEnable) {
			Runnable target3 = () -> {
				KolJob job = new KolJob();
				try {
					job.execute(null);
				} catch (JobExecutionException e) {
					log.error(e.getMessage());
				}
			};
			new Thread(target3).start();
		}
	}

	private void runActivityTotalAssetsJob() {
		Runnable target3 = () -> {
			ActivityTotalAssetsJob job = new ActivityTotalAssetsJob();
			try {
				job.execute(null);
			} catch (JobExecutionException e) {
				log.error(e.getMessage());
			}
		};
		new Thread(target3).start();
	}

	private void runActivityTotalAssets12Job() {
		Runnable target3 = () -> {
			ActivityTotalAssets12Job job = new ActivityTotalAssets12Job();
			try {
				job.execute(null);
			} catch (JobExecutionException e) {
				log.error(e.getMessage());
			}
		};
		new Thread(target3).start();
	}

	private void runActivity2Job() {
		Runnable target3 = () -> {
			Activity2Job job = new Activity2Job();
			try {
				job.execute(null);
			} catch (JobExecutionException e) {
				log.error(e.getMessage());
			}
		};
		new Thread(target3).start();

	}

	private void runKolGradeJob() {
		Runnable target3 = () -> {
			KolGradeJob job = new KolGradeJob();
			try {
				job.execute(null);
			} catch (JobExecutionException e) {
				log.error(e.getMessage());
			}
		};
		new Thread(target3).start();

	}


	private void runFeeDiscountRatioJob() {
		if (runFeeDiscountRatioEnable) {
			Runnable target3 = () -> {
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {

				}
				FeeDiscountRatioJob job = new FeeDiscountRatioJob();
				job.setFeeRateUtils(feeRateUtils);
				try {
					job.execute(null);
				} catch (JobExecutionException e) {
					log.error(e.getMessage());
				}
			};
			new Thread(target3).start();
		}
	}


	private void runDataSummaryJob() {
		if (runDataSummaryEnable) {
			Runnable target3 = () -> {
				DataSummaryJob job = new DataSummaryJob();
				try {
					job.execute(null);
				} catch (JobExecutionException e) {
					log.error(e.getMessage());
				}
			};
			new Thread(target3).start();
		}
	}
}
