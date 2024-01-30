package com.app.dc.batchsvr;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.dc.service.job.BinanceSpiArticlesJob;
import com.app.dc.service.job.BinanceSpiPictJob;
import com.app.dc.service.job.CointelegraphSpiPictJob;
import com.app.dc.util.SpiPictUtils;
import com.gateway.connector.utils.SSLClient;
import com.google.common.collect.Collections2;
import com.mysql.jdbc.StringUtils;

public class TestJob {

	@Test
	public void CointelegraphSpiPictJobTest() throws Exception {
		CointelegraphSpiPictJob job = new CointelegraphSpiPictJob();
		CointelegraphSpiPictJob.picPath = "../../data/pics";
		job.execute(null);
		System.in.read();
	}

	@Test
	public void BinanceSpiPictJobTest() throws Exception {
		BinanceSpiPictJob job = new BinanceSpiPictJob();
		BinanceSpiPictJob.picPath = "bian";
		job.execute(null);
		System.in.read();
	}

	@Test
	public void BinanceSpiArticlesJobTest() throws Exception {
		BinanceSpiArticlesJob job = new BinanceSpiArticlesJob();
		BinanceSpiArticlesJob.articlesPath = "bian";
		job.execute(null);
		System.in.read();
	}

	@Test
	public void BinanceSpiArticlesJobTest2() throws Exception {

		int index = 1;
		String path = "./binance";
		int count = 18;
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
 			System.out.println("offset:" + index + " count:" + count);
			for (Object object : datas) {

				String slug = (String) ((JSONObject) object).get("slug");
				System.out.println(slug);

				doc = Jsoup.connect(baseUrl + "/" + slug).get();
				eleBody = doc.body();
				eleData = eleBody.getElementById("__APP_DATA");
				data = eleData.data();
 				map = (JSONObject) JSON.parse(data);
				JSONObject article = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) map
						.get("appState")).get("loader")).get("dataByRouteId")).get("32bc")).get("article");

				String title = (String) article.get("title");
				String content = (String) article.get("content");

				Document contentDoc = Jsoup.parseBodyFragment(content);
				content = contentDoc.text();
			 
				SpiPictUtils.saveArticle(path, slug, title, content);
			}
			index++;
		} while (count != 0);
		System.in.read();
	}


	@Test
	public void test4(){
		String  create_time = "2023-01-1";
		if (create_time.length() >= 10) {
			create_time = create_time.substring(0, 10);
			System.out.println(create_time);
		}
	}

}
