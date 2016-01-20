package me.smart.order.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangxiong on 16/1/15.
 */
public class HttpCallService {
    private Logger logger = LoggerFactory.getLogger(HttpCallService.class);

    // 连接超时时间，默认10秒
    private int socketTimeout = 10000;

    // 传输超时时间，默认30秒
    private int connectTimeout = 30000;

    // 请求器的配置
    private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
            .setConnectTimeout(connectTimeout).build();

    // HTTP请求器
    private HttpClient httpClient = HttpClients.createDefault();

    public static String TYPE_TEXT = "text";
    public static String TYPE_JSON = "json";
    public static String TYPE_XML = "xml";

    /**
     * @param data      请求参数
     * @param url       请求路径
     * @param paramType 请求参数的类型
     * @return
     */
    public String get(String url) throws Exception {
        String response = "";
        try {
            HttpGet httpGet = new HttpGet();
            httpGet.setConfig(requestConfig);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    response = IOUtils.toString(httpEntity.getContent(), "utf-8");
                    logger.info("Http get return {}", response);
                } else {
                    logger.info("Http get return null");
                    throw new Exception("Http get return null");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Exception("Http get timeout!");
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return response;
    }
}
