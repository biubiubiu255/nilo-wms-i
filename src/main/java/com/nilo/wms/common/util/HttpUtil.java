/**
 * KILIMALL Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ronny.zeng
 * @version $Id: HttpUtil.java, v 0.1 2016年9月15日 下午1:11:35 Exp $
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(HttpUtil.class);

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static CloseableHttpClient httpClient = null;
    private static final int REQUEST_TIMEOUT = 30 * 1000;   // 设置请求超时
    private static final int TIMEOUT = 30 * 1000;   // 连接超时时间
    private static final int SO_TIMEOUT = 30 * 1000;   // 数据传输超时

    static {
        // 设置连接池  
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小  
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        requestConfig = RequestConfig.custom().setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(REQUEST_TIMEOUT).setSocketTimeout(SO_TIMEOUT).build();
        // Create httpClient  
        httpClient = HttpClients.custom().disableRedirectHandling().setConnectionManager(connMgr)
                .build();
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        return get(url, new HashMap<String, Object>());
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        CloseableHttpResponse response = null;
        HttpGet httpGet = null;
        try {

            httpGet = new HttpGet(apiUrl);
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("执行状态码 : " + statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println("error");
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    //ignore
                }
            }
            if (httpGet != null) {
                try {
                    httpGet.releaseConnection();
                } catch (Exception e) {
                    //ignore
                }
            }
        }
        return result;
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param apiUrl
     * @return
     */
    public static String post(String apiUrl) {
        return post(apiUrl, new HashMap<String, String>());
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static String post(String apiUrl, Map<String, String> params) {

        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(),
                        entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                httpStr = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    //ignore
                }
            }
            if (httpPost != null) {
                try {
                    httpPost.releaseConnection();
                } catch (Exception e) {
                    //ignore
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static String postJson(String apiUrl, String json) {
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题  
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            if (entity != null) {
                httpStr = EntityUtils.toString(entity, "UTF-8");
            }
            System.out.println(httpStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    //ignore
                }
            }
            if (httpPost != null) {
                try {
                    httpPost.releaseConnection();
                } catch (Exception e) {
                    //ignore
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），Xml形式
     *
     * @param apiUrl
     * @return
     */
    public static String doPostXml(String apiUrl, String xmlStr) {
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(xmlStr, "UTF-8");//解决中文乱码问题  
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("text/xml");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            if (entity != null) {
                httpStr = EntityUtils.toString(entity, "UTF-8");
            }
            System.out.println(httpStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    //ignore
                }
            }
            if (httpPost != null) {
                try {
                    httpPost.releaseConnection();
                } catch (Exception e) {
                    //ignore
                }
            }
        }
        return httpStr;
    }

}
