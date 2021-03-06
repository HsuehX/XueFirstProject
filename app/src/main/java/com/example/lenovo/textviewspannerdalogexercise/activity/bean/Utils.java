package com.example.lenovo.textviewspannerdalogexercise.activity.bean;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by xue on 2018/7/28.
 */

public class Utils {
    //    private static final String URL = "http://www.tuling123.com/openapi/api";
//    private static final String API_KEY = "6bb5146414bb4fd9a8cf192be580d367";
    private static final String URL = "";
    private static final String API_KEY = "";

    /**
     * @param msg 发送的内容
     * @return 远程返回的JSON格式的字符串
     * @throws IOException
     */
    public static String doGet(String msg) throws IOException {
        String result = "";
        String url = getUrl(msg);
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
//            java.net.URL urlConn = new URL(url);
            java.net.URL urlConn = new java.net.URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlConn.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            is = conn.getInputStream();
            int len = -1;
            byte[] byteRead = new byte[128];
            baos = new ByteArrayOutputStream();

            while ((len = is.read(byteRead)) != -1) {
                baos.write(byteRead, 0, len);

            }
            baos.flush();
            result = new String(baos.toByteArray());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (baos != null) {
                baos.close();

            }
        }
        return result;


    }

    /**
     * @param msg 发送的内容
     * @return 拼接的URL
     */
    private static String getUrl(String msg) {
        String result = null;
        try {
            result = URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg, "UTF-8") + "&userid=" + "12345678";

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送一个消息 返回消息
     *
     * @param msg 发送的内容
     * @return 从JSON数据中去除的返回内容
     */
    public static MsgBean getMessage(String msg) {
        MsgBean msgBean = new MsgBean();
        try {
            String jsonString = doGet(msg);
            Gson gson = new Gson();
            Result result = gson.fromJson(jsonString, Result.class);
            msgBean.setMsg(result.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        msgBean.setDate(new Date());
        msgBean.setType(MsgBean.Type.INCOMING);


        return msgBean;
    }

}
