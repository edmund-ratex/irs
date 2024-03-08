package com.app.dc.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import com.gateway.connector.utils.SSLClient;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.XHTMLValidationType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpiPictUtils {
	static ArrayList<String> picFormats = new ArrayList<String>();

	public static ArrayList<String> getPicFormats() {
		return picFormats;
	}

	static {
		picFormats.add("jpg");
		picFormats.add("gif");
		picFormats.add("png");
		picFormats.add("bmp");
		picFormats.add("jpeg");
	}

	public static void saveArticle(String path, String fileName, String title, String content) throws Exception {

		String savePath = path + "/" + fileName + ".docx";
		File dir = new File(path);

		FileUtils.forceMkdir(dir);
		Collection<String> lines = new ArrayList<String>();

		lines.add(content);
 
		String tmpSavePath = path + "/" + fileName + ".html";
		File file = new File(tmpSavePath);
		FileUtils.writeLines(file, lines);

		Document doc = new Document();
		doc.loadFromFile(tmpSavePath, FileFormat.Html, XHTMLValidationType.None);
		doc.saveToFile(savePath, FileFormat.Docx);
		FileUtils.forceDelete(file);
		log.info("save success:" + savePath);

	}

	@SuppressWarnings({ "resource", "deprecation" })
	public static int downloadImg(List<String> picUrls, String savePath) throws Exception {
		SSLClient client = new SSLClient();
		int count = 0;
		File dir = new File(savePath);

		if (!dir.exists() && dir.isDirectory()) {
			dir.mkdirs();
		}
		for (String imgUrl : picUrls) {
			String[] strs = imgUrl.split("/");
			String downPath = savePath + strs[strs.length - 1];
			File f = new File(downPath);
			if (!f.exists()) {

				HttpGet request = new HttpGet(imgUrl);
				try {

					RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000)
							.setConnectTimeout(50000).build();

					request.setHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");

					request.setConfig(requestConfig);

					CloseableHttpResponse response = client.execute(request);

					if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
						HttpEntity entity = response.getEntity();

						InputStream in = entity.getContent();

						FileUtils.copyInputStreamToFile(in, f);
						log.info("download success:" + downPath);
						count++;
					}

				} catch (Exception e) {
					e.printStackTrace();
					log.error("imgurl:" + imgUrl + e.getMessage());

				} finally {
					request.releaseConnection();

				}
			}
		}

		client.getConnectionManager().shutdown();
		return count;
	}
}
