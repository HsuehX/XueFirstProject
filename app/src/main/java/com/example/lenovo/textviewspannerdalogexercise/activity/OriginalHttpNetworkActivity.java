package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.bean.GsonBean;
import com.example.lenovo.textviewspannerdalogexercise.network.ContentHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xue on 2018/4/26.
 */

public class OriginalHttpNetworkActivity extends Activity {
    @Bind(R.id.send_request_tv)
    TextView send_request_tv;

    @Bind(R.id.response_tv)
    TextView response_tv;

    @Bind(R.id.timer_tv)
    TextView timer_tv;

    @Bind(R.id.show_timer_tv)
    TextView show_timer_tv;

    @Bind(R.id.send_request_okhttp_tv)
    TextView send_request_okhttp_tv;

    @Bind(R.id.send_request_xml_tv)
    TextView send_request_xml_tv;

    @Bind(R.id.send_request_json_tv)
    TextView send_request_json_tv;

    @Bind(R.id.send_request_gson_tv)
    TextView send_request_gson_tv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original_http_network);
        ButterKnife.bind(this);
//        init();
    }

//    private void init() {
//
//    }

    @OnClick({R.id.send_request_tv, R.id.timer_tv, R.id.send_request_okhttp_tv, R.id.send_request_xml_tv, R.id.send_request_json_tv,
            R.id.send_request_gson_tv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_request_tv:
                Log.e("text:", "1111");
                sendRequestHttp();
                break;
            case R.id.timer_tv:
                timer();
                break;
            case R.id.send_request_okhttp_tv:
                sendRequestOkHttp();
                break;
            case R.id.send_request_xml_tv:
                sendRequestXML();
                Log.e("text:", "xml");
                break;
            case R.id.send_request_json_tv:
                sendRequestJson();
                Log.e("text:", "json");
                break;
            case R.id.send_request_gson_tv:
                sendRequsetGson();
                break;
            default:
                break;
        }
    }

    private void sendRequsetGson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://10.0.2.2/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<GsonBean> appList = gson.fromJson(jsonData, new TypeToken<List<GsonBean>>() {
        }.getType());
        String s = null;
        for (GsonBean app : appList) {
            Log.e("MainActivity", "id is " + app.getId());
            Log.e("MainActivity", "name is " + app.getName());
            Log.e("MainActivity", "version is " + app.getVersion());
            s = s + app.getId() + "   " + app.getName() + "   " + app.getVersion();
        }
        showResponse(s);
    }

    private void sendRequestHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com/");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(88000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //对获取到的输入流进行读取
                    reader = new BufferedReader((new InputStreamReader(in)));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    showResponse(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (connection != null) {
                            connection.disconnect();
                        }
                    }
                }

            }
        }).start();
    }

    /**
     * 发送请求（用户名密码什么的）
     */
    private void sendRequset() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com/");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("PSOT");
                    connection.setConnectTimeout(88000);
                    connection.setReadTimeout(8000);
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    outputStream.writeBytes("username=admin&password=123456");//用&分割属性
                    InputStream in = connection.getInputStream();
                    //对获取到的输入流进行读取
                    reader = new BufferedReader((new InputStreamReader(in)));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    showResponse(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (connection != null) {
                            connection.disconnect();
                        }
                    }
                }

            }
        }).start();
    }

    private void showResponse(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //对UI的操作
                response_tv.setText(s);
                Log.e("text:", s);
            }
        });
    }


    private void sendRequestOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url("https://www.baidu.com").build();
                    Response response = okHttpClient.newCall(request).execute();
                    String okhttpData = "OKhTTP" + response.body().string();
                    showResponse(okhttpData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * xml
     */
    private void sendRequestXML() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient xmlClient = new OkHttpClient();
                    Request xmlRequest = new Request.Builder().url("http://10.0.2.2/get_data.xml").build();
                    Response xmlResponse = xmlClient.newCall(xmlRequest).execute();
                    String xmlData = xmlResponse.body().string();
                    parseXMLWithSAX(xmlData);
                    Log.e("text:", "xml success");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void parseXMLWithSAX(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            // 将ContentHandler的实例设置到XMLReader中
            xmlReader.setContentHandler(handler);
            // 开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
            showResponse(xmlData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 以本机为服务器，获取数据
     */
    private void parseXMLWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    // 开始解析某个结点
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    // 完成解析某个结点
                    case XmlPullParser.END_TAG: {
                        if ("app".equals(nodeName)) {
                            Log.d("MainActivity", "id is " + id);
                            Log.d("MainActivity", "name is " + name);
                            Log.d("MainActivity", "version is " + version);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sendRequestJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://10.0.2.2/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * json
     */
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.e("MainActivity", "id is " + id);
                Log.e("MainActivity", "name is " + name);
                Log.e("MainActivity", "version is " + version);
                showResponse(id + name + version + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void timer() {
        /** 倒计时60秒，一次1秒 */
        //需要全部倒计时时间，过多少秒一跳
        CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                show_timer_tv.setText("还剩" + millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                show_timer_tv.setText("倒计时完毕了+获取验证码");
            }
        }.start();
    }
}
