package com.app.dc.service.job;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.dc.util.SpiPictUtils;
import com.gateway.connector.utils.SSLClient;
import com.mysql.jdbc.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CointelegraphSpiPictJob implements Job {

	private static boolean runFlag = false;
	public static String picPath = "pics/";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("CointelegraphSpiPictJob execute");
		try {
			cointelegraphPic();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public synchronized static void cointelegraphPic() throws Exception {
		if (!runFlag) {
			runFlag = true;
			String[] categorys = new String[] { "altcoin", "bitcoin", "defi", "economics", "ethereum", "metaverse",
					"Mining", "NFT", "Trading", "dex" };

			SSLClient httpclient = new SSLClient();
			int pageSize = 2000;
			for (String category : categorys) {
				int size = 0;
				int index = 0;
				do {
					int offset = index * pageSize;
					List<String> picUrls = new ArrayList<String>();
					URI url = new URI("https://graphcdn.cointelegraph.com");
					HttpPost httppost = new HttpPost(url);

					String body = "{\"operationName\":\"SearchPagePostsQuery\",\"variables\":{\"query\":\"" + category
							+ "\",\"offset\":" + offset + ",\"length\":" + pageSize
							+ ",\"short\":\"en\",\"cacheTimeInMS\":300000},\"query\":\"query SearchPagePostsQuery($short: String = \\\"en\\\", $offset: Int!, $length: Int!, $query: String!) {\\n  locale(short: $short) {\\n    postsSearch(offset: $offset, length: $length, query: $query) {\\n      data {\\n        id\\n        slug\\n        views\\n        postTranslate {\\n          cacheKey\\n          id\\n          title\\n          leadText\\n          avatar\\n          published\\n          publishedHumanFormat\\n          author {\\n            cacheKey\\n            id\\n            slug\\n            authorTranslates {\\n              cacheKey\\n              id\\n              name\\n            }\\n          }\\n        }\\n        category {\\n          cacheKey\\n          id\\n          slug\\n          categoryTranslates {\\n            cacheKey\\n            id\\n            title\\n          }\\n        }\\n        author {\\n          cacheKey\\n          id\\n          slug\\n          authorTranslates {\\n            cacheKey\\n            id\\n            name\\n          }\\n        }\\n        postBadge {\\n          cacheKey\\n          id\\n          label\\n          postBadgeTranslates {\\n            cacheKey\\n            id\\n            title\\n          }\\n        }\\n        showShares\\n        showStats\\n      }\\n      postsCount\\n      hasMorePosts\\n    }\\n  }\\n}\\n\"}";

					StringEntity entity = new StringEntity(body.toString(), ContentType.APPLICATION_JSON);
					httppost.addHeader("Content-Type", "application/json");
					httppost.addHeader("Accept", "application/json");
					httppost.addHeader("Cache-Control", "no-cache");
					httppost.setEntity(entity);
					ResponseHandler<String> responseHandler = new BasicResponseHandler();

					String result = httpclient.execute(httppost, responseHandler);
					if (!StringUtils.isNullOrEmpty(result)) {
						JSONObject map = (JSONObject) JSON.parse(result);
						List<JSONObject> datas = (List<JSONObject>) ((JSONObject) ((JSONObject) ((JSONObject) map
								.get("data")).get("locale")).get("postsSearch")).get("data");

						for (JSONObject object : datas) {
							try {
								String picUrl = ((JSONObject) object.get("postTranslate")).get("avatar") + "";
								picUrls.add(picUrl);
							} catch (Exception e) {
								log.error(e.getMessage());
							}
						}
					}
					httppost.releaseConnection();
					log.info(category + " offset:" + offset + " size:" + picUrls.size());
					size = picUrls.size();
					Runnable target = () -> {
						try {
							List<String> tmp = new ArrayList<String>();
							for (String pic : picUrls) {
								pic = pic.trim().toLowerCase();
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
							log.info(category + " offset:"+offset+" download:" + count);
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
