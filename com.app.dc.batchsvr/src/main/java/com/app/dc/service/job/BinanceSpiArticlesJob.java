package com.app.dc.service.job;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.dc.util.SpiPictUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinanceSpiArticlesJob implements Job {

	private static boolean runFlag = false;
	public static String articlesPath = "binance/";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("binanceSpiArticles execute");
		try {
			binanceSpiArticles();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public synchronized static void binanceSpiArticles() throws Exception {
		if (!runFlag) {
			runFlag = true;
			int index = 1;
			String path = articlesPath;
			FileUtils.deleteDirectory(new File(path));

			int count = 0;
			do {
				String baseUrl = "https://academy.binance.com/en/articles";
				Document doc = Jsoup.connect(baseUrl + "?page=" + index).get();

				Element eleBody = doc.body();
				Element eleData = eleBody.getElementById("__APP_DATA");
				String data = eleData.data();
				JSONObject map = (JSONObject) JSON.parse(data);
				JSONObject d96d = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) map.get("appState")).get("loader"))
						.get("dataByRouteId")).get("d96d");
				JSONObject response = (JSONObject) ((JSONObject) d96d.get("response")).get("pages");
	 			JSONArray datas = response.getJSONArray("data");
				count = datas.size();
				log.info("offset:" + index + " count:" + count);
				for (Object object : datas) {

					String slug = (String) ((JSONObject) object).get("slug");
					log.info(slug);

					doc = Jsoup.connect(baseUrl + "/" + slug).get();
					eleBody = doc.body();
					eleData = eleBody.getElementById("__APP_DATA");
					data = eleData.data();
	 				map = (JSONObject) JSON.parse(data);
					JSONObject article = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) map
							.get("appState")).get("loader")).get("dataByRouteId")).get("32bc")).get("article");

					String title = (String) article.get("title");
					String content = (String) article.get("content");
					JSONArray categories = article.getJSONArray("categories");
					String categorie="";
					if(categories!=null&&categories.size()>0)
					{
						categorie=(String) ((JSONObject) categories.get(0)).get("slug");
					}
					content="<h1>"+title+"</h1>"+content;
					Document contentDoc = Jsoup.parseBodyFragment(content);
					contentDoc.select("img").remove();
					 
					contentDoc.select("p").removeAttr("data-block-id");
					contentDoc.select("h2").removeAttr("id");
					contentDoc.select("a").removeAttr("href");
					
					content = contentDoc.outerHtml();
					
					SpiPictUtils.saveArticle(path+"/"+categorie, slug, title, content);
				}
				index++;
			} while (count != 0);
			runFlag = false;
		} else {
			log.warn("binanceSpiArticles job is runing.");

		}

	}

}
