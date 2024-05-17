package com.app.dc.service.job;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.common.utils.JsonUtils;
import com.app.dc.util.SpiPictUtils;
import com.gateway.connector.utils.SSLClient;
import com.mysql.jdbc.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinanceSpiPictJob implements Job {

	private static boolean runFlag = false;
	public static String picPath = "binance/";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("CointelegraphSpiPictJob execute");
		try {
			cointelegraphPic();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@SuppressWarnings("resource")
	public synchronized static void cointelegraphPic() throws Exception {
		if (!runFlag) {
			runFlag = true;
			String[] categorys = new String[] { "altcoin", "bitcoin", "defi", "economics", "ethereum", "metaverse",
					"Mining", "NFT", "Trading", "dex" };

			SSLClient httpclient = new SSLClient();
			int pageSize = 100;
			for (String category : categorys) {
				int size = 0;
				int index = 0;
				do {
					int offset = index * pageSize;
					List<String> picUrls = new ArrayList<String>();
					URI url = new URI("https://www.binance.com/bapi/composite/v1/public/content/blog/search?keywords="
							+ category + "&page=" + index + "&size=" + pageSize);
					HttpGet httpget = new HttpGet(url);

					httpget.addHeader("Content-Type", "application/json");
					httpget.addHeader("Accept", "application/json");
					httpget.addHeader("Cache-Control", "no-cache");

					ResponseHandler<String> responseHandler = new BasicResponseHandler();

					String result = httpclient.execute(httpget, responseHandler);
					if (!StringUtils.isNullOrEmpty(result)) {
						JSONObject map = (JSONObject) JSON.parse(result);
						JSONArray datas = (JSONArray) ((JSONObject) map.get("data")).get("blogList");
						size = datas.size();
						for (int i = 0; i < datas.size(); i++) {
							JSONObject object = (JSONObject) datas.get(i);
							try {

								String picUrl = object.get("banner") + "";
								if (!StringUtils.isNullOrEmpty(picUrl)&&picUrl.contains("}")) {
									HashMap<String, Object> tmpM = JsonUtils.Deserialize(picUrl.toLowerCase(),
											HashMap.class);
									picUrl = tmpM.get("bannerurl") + "";
									picUrls.add(picUrl);
								}
							} catch (Exception e) {
								log.error(e.getMessage());
							}
						}
					}
					httpget.releaseConnection();
					log.info(category + " offset:" + offset + " size:" + picUrls.size());

					Runnable target = () -> {
						try {
							List<String> tmp = new ArrayList<String>();
							for (String pic : picUrls) {
								pic = pic.trim();
								if (!StringUtils.isNullOrEmpty(pic) && !tmp.contains(pic)) {
									if (!pic.startsWith("https://")) {
										pic = "https://" + pic;
									}
									boolean flag = false;
									for (String picFormat : SpiPictUtils.getPicFormats()) {
										if (pic.endsWith(picFormat)) {
											flag = true;
											break;
										}
									}
									if (flag) {
										tmp.add(pic);
									}
								}
							}
							log.info(category + " offset:" + offset + " valid size:" + tmp.size());
							int count = SpiPictUtils.downloadImg(tmp, picPath + "/" + category + "/");
							log.info(category + " offset:" + offset + " download:" + count);
						} catch (Exception e) {
							log.error(e.getMessage());
						}
					};
					new Thread(target).start();
					index++;
				} while (size == pageSize);

			}

			httpclient.getConnectionManager().shutdown();
			runFlag = false;
		} else {
			log.warn("spiPic job is runing.");

		}

	}

}
