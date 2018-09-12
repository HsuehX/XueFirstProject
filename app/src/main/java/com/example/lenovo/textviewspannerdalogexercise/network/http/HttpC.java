package com.example.lenovo.textviewspannerdalogexercise.network.http;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by xue on 2018/6/21.
 */

public class HttpC {
    /**
     * 设置一些基本的参数来获取线程安全的HttpClient对象
     *
     * @return 返回线程安全的HttpClient对象
     */
    private static HttpClient getHttpClient() {
        /* 1. 设置一些基本参数，如Http版本、编码格式和参数设置 */
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
        HttpProtocolParams.setUseExpectContinue(httpParams, true);

        /* 2. 超时设置 */
        /* 从连接池中取连接的超时时间 */
        ConnManagerParams.setTimeout(httpParams, 20000);
        /* 连接超时 */
        HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
        /* 请求超时 */
        HttpConnectionParams.setSoTimeout(httpParams, 20000);

        /* 3. 设置我们的HttpClient支持HTTP和HTTPS两种模式 */
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        registry.register(new Scheme("https", SSLSocketFactory
                .getSocketFactory(), 443));

        /* 4. 使用线程安全的连接管理来创建HttpClient */
        ClientConnectionManager manager = new ThreadSafeClientConnManager(
                httpParams, registry);
        return new DefaultHttpClient(manager, httpParams);
    }


    /**
     * HttpClient Post请求方式
     */
    public static boolean onLinkNetPost(String url, List<NameValuePair> nvpsList) {

        try {
            // 判断传递进来的url连接地址是否为空
            if (null == url) {
                return false;
            }
            /* 1.1 创建POST请求，并设置Url地址的名值对及其编码格式，然后设置Entity */
            Log.e("post", url);
            HttpPost httpPost = new HttpPost(url);
            if (null != nvpsList) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                        nvpsList, "UTF-8");
                httpPost.setEntity(entity);
            }
            /* 1.2获取HttpClient对象，并发送请求，得到响应 */
            HttpClient httpClient = getHttpClient();

            // 1.3发送请求，获取服务器返回的相应对象
            HttpResponse httpResponse = httpClient.execute(httpPost);
            /* 1.4 从响应中获取数据 */
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return false;
            }
            HttpEntity httpEntity = httpResponse.getEntity();

            String object = (httpEntity == null) ? null : (EntityUtils
                    .toString(httpEntity, "UTF-8"));
            Log.e("object", object);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception=", e.getMessage() + "");
            Log.e("e", e.getMessage() + "");
            return false;
        }
    }


    /**
     * HttpClient Get请求方式
     */
    public static boolean onLinkNetGet(String url, Map<String, String> params) {
        try {
            // GET方式参数拼接在URL结尾
            StringBuilder sb = new StringBuilder();
            sb.append(url).append("?");
            if (params != null && params.size() != 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    // 如果请求参数中有中文，需要进行URLEncoder编码
                    sb.append(entry.getKey())
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "utf-8"));
                    sb.append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
                System.out.println(sb.toString());
                Log.d("sb", sb.toString());
            }

        /* 1.1 创建Get请求，并设置Url地址 */
            HttpGet httpGet = new HttpGet(sb.toString());
            Log.e("get", sb.toString());
        /* 1.2 获取HttpClient对象，并发送请求，得到响应 */
            HttpClient httpClient = getHttpClient();
            // 1.3发送请求，获取服务器返回的相应对象
            HttpResponse httpResponse = httpClient.execute(httpGet);
        /* 1.4 从响应中获取数据 */
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return false;
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            String object = (httpEntity == null) ? null : (EntityUtils
                    .toString(httpEntity, "UTF-8"));
            System.out.println(object);
            Log.e("object", object);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception=", e.getMessage() + "");
            Log.e("e", e.getMessage() + "");
            return false;
        }

    }


    // Put方式
    public static boolean onLinkNetPut(String url, Map<String, String> params) {
        try {
    /* 1. 判断传递进来的url连接地是否为空 */
            if (null == url) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(url).append("?");
            if (params != null && params.size() != 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    // 如果请求参数中有中文，需要进行URLEncoder编码
                    sb.append(entry.getKey())
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "utf-8"));
                    sb.append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
                System.out.println(sb.toString());
                Log.d("sb", sb.toString());
            }

    /* 1.1 创建httpPut请求，并设置Url地址 */
            HttpPut httpPut = new HttpPut(sb.toString());
            Log.e("start", sb.toString());

    /* 1.2 获取HttpClient对象，并发送请求，得到响应 */
            HttpClient httpClient = getHttpClient();
            // 1.3发送请求，获取服务器返回的相应对象
            HttpResponse httpResponse = httpClient.execute(httpPut);

    /* 1.4从响应中获取数据 */
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return false;
            }
            HttpEntity httpEntity = httpResponse.getEntity();

            String object = (httpEntity == null) ? null : (EntityUtils
                    .toString(httpEntity, "UTF-8"));
            Log.e("object", object);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception=", e.getMessage() + "");
            Log.e("e", e.getMessage() + "");
            return false;
        }
    }
}