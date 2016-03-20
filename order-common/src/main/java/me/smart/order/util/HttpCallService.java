package me.smart.order.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketTimeoutException;

/**
 * Created by zhangxiong on 16/1/15.
 */
public class HttpCallService {
    private Logger logger = LoggerFactory.getLogger(HttpCallService.class);
    // 连接超时时间，默认10秒
    private int socketTimeout = 4000;

    // 传输超时时间，默认30秒
    private int connectTimeout = 4000;

    // 请求器的配置
    private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
            .setConnectTimeout(connectTimeout).build();

    // HTTP请求器
    private HttpClient httpClient;

    public HttpCallService() {
        this.httpClient = HttpClients.createDefault();
    }

    public static String TYPE_TEXT = "text";
    public static String TYPE_JSON = "json";
    public static String TYPE_XML = "xml";

    /**
     * @param url       请求路径
     * @param paramType 请求参数的类型
     * @return
     */
    public String get(String url) throws Exception {
        String response = "";
        try {
            HttpGet httpGet = new HttpGet(url);
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

    /**
     * 发送xml数据
     *
     * @return
     */
    public String postXml(String url, String xmlData) {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(xmlData, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("Host", "api.mch.weixin.qq.com");
        httpPost.setEntity(postEntity);
        try {
            logger.info("executing request" + httpPost.getRequestLine());
            logger.info("httpCallService调用");
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
            logger.error(e.getMessage(), e);
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)", e);

        } catch (ConnectTimeoutException e) {
            logger.error(e.getMessage(), e);
            logger.error("http get throw ConnectTimeoutException", e);

        } catch (SocketTimeoutException e) {
            logger.error(e.getMessage(), e);
            logger.error("http get throw SocketTimeoutException", e);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("http get throw Exception", e);

        } finally {
            httpPost.abort();
        }
        return result;
    }
}
