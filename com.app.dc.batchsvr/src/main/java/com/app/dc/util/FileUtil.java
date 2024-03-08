package com.app.dc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * @Description
 * @Author
 * @Date
 **/
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void writeFile(String filePath, String content) {
        FileWriter fw = null;
        try {

            File file = new File(filePath);

            fw = new FileWriter(file, false);

            fw.write(content);

        } catch (IOException e) {
            logger.error("writeFile exception:{}", e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    logger.error("writeFile exception:{}", e);
                }
            }
        }
    }

    public static String readFile(String filePath) {
        String result = "";
        FileReader fr = null;
        try {

            File file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();
            }
            fr = new FileReader(file);

            char[] buf = new char[1024];
            int num = 0;
            while ((num=fr.read(buf)) != -1) {
                result = new String(buf, 0, num);
            }
        } catch (IOException e) {
            logger.error("readFile exception:{}", e);
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    logger.error("readFile exception:{}", e);
                }
            }
        }

        return result;
    }


    public static Properties readProperties(String filePath) {

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(filePath);
            Properties properties = new Properties();
            properties.load(inputStream);    

            return properties;

        } catch (Exception e) {
            logger.error("readProperties exception:{}", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("readProperties close IOException:{}", e);
                }
            }
        }
        return null;
    }

}
