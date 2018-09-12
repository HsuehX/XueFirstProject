package com.example.lenovo.textviewspannerdalogexercise.network.practice;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
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
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

/**
 * Created by xue on 2018/6/20.
 */

public class PracticeHttpClient {
    public static final String CHARTYPE = HTTP.UTF_8;
    private static final long OUTTIME = 1000;//从连接池中取连接的超时时间
    private static final int CONNECTIONTIME = 2000;//连接超时
    private static final int REQUESTTIME = 4000;//请求超时
    private static final String TIMEOUTSETTING = "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
            + "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1";

    private static HttpClient httpClient;

    private PracticeHttpClient() {
    }

    public static HttpClient getHttpClient() {
        if (null == httpClient) {
            HttpParams params = new BasicHttpParams();
            //http协议设置部分
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);//设置版本
            HttpProtocolParams.setContentCharset(params, CHARTYPE);//设置编码
            HttpProtocolParams.setUseExpectContinue(params, true);//设置是否延时发送
            HttpProtocolParams.setUserAgent(params, TIMEOUTSETTING);//????
            //链接设置
            ConnManagerParams.setTimeout(params, OUTTIME);//连接池获取连接超时  只有这个是ConnManagerParams
            //HTTP连接参数设置部分
            HttpConnectionParams.setConnectionTimeout(params, CONNECTIONTIME);//连接超时
            HttpConnectionParams.setSoTimeout(params, REQUESTTIME);//请求超时（读超时）
            // 设置支持HTTP和HTTPS
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
            // 使用线程安全的连接管理来创建HttpClient
            ClientConnectionManager clientConnectionManager = new ThreadSafeClientConnManager(params, schemeRegistry);
            httpClient = new DefaultHttpClient(clientConnectionManager, params);
        }
        return httpClient;
    }
}


//        activity中的使用
//    //Login Post请求方式
//    public  static boolean Login(String login_name, String password) {
//        List<NameValuePair> nvpsList =new ArrayList<NameValuePair>();
//        try {
//            nvpsList.add(new BasicNameValuePair("login_name",login_name));
//            nvpsList.add(new BasicNameValuePair("password",password));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String url  = "Your Url";
//        return onLinkNetPost(url,nvpsList);
//    }