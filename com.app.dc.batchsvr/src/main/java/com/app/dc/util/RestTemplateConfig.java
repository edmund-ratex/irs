package com.app.dc.util;

import com.app.dc.service.day.DepositCheckTask;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Component
public class RestTemplateConfig {

    private Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);
    private RestTemplate restTemplate;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }


    @Bean
    public RestTemplate restTemplate()
    {
        if (restTemplate == null) {
            ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
            restTemplate = new RestTemplate(requestFactory);

            MappingJackson2HttpMessageConverter mmc = new MappingJackson2HttpMessageConverter();
            mmc.setSupportedMediaTypes(Arrays.asList(
                    MediaType.TEXT_HTML, MediaType.TEXT_PLAIN));

            restTemplate.getMessageConverters().add(mmc);
        }
        return restTemplate;//new RestTemplate(requestFactory);
    }

    private HttpHeaders getHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        return headers;
    }

    public String post(String url, String requstData) {
        HttpEntity entity = new HttpEntity(requstData, getHttpHeaders());
        logger.info("post url:{}, requestData:{}", url, requstData);
        ResponseEntity<String> resp = restTemplate.postForEntity(url, entity, String.class);
        HttpStatus httpStatus = resp.getStatusCode();

        logger.info("HttpStatus:{}", httpStatus);
        logger.info("Resp data:{}", resp.getBody());

        return resp.getBody();
    }


    /**
     * Apache HttpClient
     *
     * @return
     */
    private HttpClient httpClient()
    {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
        ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
        registryBuilder.register("http", plainSF);
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            TrustStrategy anyTrustStrategy = new TrustStrategy() {

                @Override
                public boolean isTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                    // TODO Auto-generated method stub
                    return true;
                }
            };
            SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy).build();
            LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            registryBuilder.register("https", sslSF);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Registry<ConnectionSocketFactory> registry = registryBuilder.build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);

        connManager.setMaxTotal(50);
        connManager.setDefaultMaxPerRoute(20);
        connManager.setValidateAfterInactivity(2000);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(65000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(1000)
                .build();
        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setConnectionManager(connManager).build();

    }

}


