package com.example.lenovo.textviewspannerdalogexercise.network.aboutupload;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xue on 2018/6/26.
 */

public class UpLoadUtil {
    private static UpLoadUtil upLoadUtil;
    private static final String BOUNDARY = UUID.randomUUID().toString();// 边界标识 随机生成  UUID唯一通用识别码
    private static final String PREFIX = "--";//（格式）前缀
    private static final String LINE_END = "\r\n";//单行末尾后缀
    private static final String CONTENT_TYPE = "multipart/form-data";//内容类型

    private UpLoadUtil() {
    }

    /**
     * 单例模式获取上传工具类
     */
    public static UpLoadUtil getInstance() {
        if (null == upLoadUtil) {
            upLoadUtil = new UpLoadUtil();
        }
        return upLoadUtil;
    }

    private static final String TAG = "UpLoadUtil";
    private int readTimeOut = 10 * 1000;//读取超时
    private int connectTimeOut = 10 * 1000;//超时时间
    private static int requestTime = 0;//请求使用时间
    private static final String CHATSET = "utf-8";//设置编码

    public static final int UPLOAD_SUCCESS_CODE = 1;//上传成功
    public static final int UPLOAD_FILE_NOT_EXISTS_CODE = 2;//文件不存在
    public static final int UPLOAD_SERVER_ERROR_CODE = 3;//服务出错

    protected static final int WHAT_TO_UPLOAD = 1;
    protected static final int WHAT_UPLOAD_DONE = 2;

    /**
     * android 上传文件到服务器
     *
     * @param filePath   需要上传的文件的路径
     * @param fileKey    在网页上<input type=file name=xxx/> xxx就是这里的fileKey
     * @param RequestURL
     * @param param
     */
    public void upLoadFile(String filePath, String fileKey, String RequestURL, Map<String, String> param) {
        if (filePath == null) {
            sendMessage(UPLOAD_FILE_NOT_EXISTS_CODE, "文件不存在");
            return;
        }
        try {
            File file = new File(filePath);
            upLoadFile(String.valueOf(file), fileKey, RequestURL, param);
        } catch (Exception e) {
            sendMessage(UPLOAD_FILE_NOT_EXISTS_CODE, "文件不存在");
            e.printStackTrace();
            return;
        }
    }

    /**
     * android上传文件到服务器
     *
     * @param file       需要上传的文件
     * @param fileKey    在网页上<input type=file name=xxx/> xxx就是这里的fileKey
     * @param RequestURL 请求的URL
     */
    public void upLoadFile(final File file, final String fileKey, final String RequestURL, final Map<String, String> param) {
        if (file == null || (!file.exists())) {
            sendMessage(UPLOAD_FILE_NOT_EXISTS_CODE, "文件不存在");
            return;
        }
        Log.e(TAG, "请求的URL=" + RequestURL);
        Log.e(TAG, "请求的fileName=" + file.getName());
        Log.e(TAG, "请求的fileKey=" + fileKey);
        new Thread(new Runnable() {
            @Override
            public void run() {
                toUpLoadFile(file, fileKey, RequestURL, param);
            }
        }).start();
    }

    private void toUpLoadFile(File file, String fileKey, String RequestURL, Map<String, String> param) {
        String result = null;
        requestTime = 0;

        long requestTime = System.currentTimeMillis();//当前系统时间
        long responseTime = 0;

        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut);//读取超时时间
            conn.setConnectTimeout(connectTimeOut);//链接超时时间
            conn.setDoInput(true);//允许输入流
            conn.setDoOutput(true);//允许输出流
            conn.setUseCaches(false);//不允许使用缓存
            conn.setRequestMethod("POST");//请求方式
            //设置请求属性
            conn.setRequestProperty("Charset", CHATSET);//设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

            // 当文件不为空，把文件包装并且上传
            //要想使用DataOutputStream写入数据的话，则必须指定好数据的输出格式。
            // 每一行的数据，通过'n\'完结，每行的各个数据之间通过"\t“完结。
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            StringBuffer sb = null;
            String params = "";

            //以下用于上传参数
            if (param != null && param.size() > 0) {
                Iterator<String> it = param.keySet().iterator();
                while (it.hasNext()) {
                    sb = null;
                    sb = new StringBuffer();
                    String key = it.next();
                    String value = param.get(key);
                    sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
                    sb.append("Content-Disposition: form-data; name=\"")
                            .append(key).append("\"")
                            .append(LINE_END)
                            .append(LINE_END);
                    sb.append(value).append(LINE_END);
                    dos.write(params.getBytes());
                }
            }

            //这段话是干啥的
            sb = null;
            params = null;
            sb = new StringBuffer();

            //这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件  filename是文件的名字，包含后缀名的 比如:abc.png
            sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
            sb.append("Content-Disposition:form-data; name=\"" + fileKey + "\"; filename=\"" + file.getName() + "\"" + LINE_END);
            sb.append("Content-Type:image/pjpeg" + LINE_END); // 这里配置的Content-type很重要的 ，用于服务器端辨别文件的类型的
            sb.append(LINE_END);
            sb = null;
            Log.i(TAG, file.getName() + "=" + params + "##");
            dos.write(params.getBytes());


            //上传文件  上传文件也是遍历上传的 额大概吧
            InputStream is = new FileInputStream(file);
            onUpLoadProcessListener.initUpload((int) file.length());
            byte[] bytes = new byte[1024];
            int len = 0;
            int curLen = 0;
            while ((len = is.read(bytes)) != -1) {
                curLen += len;
                dos.write(bytes, 0, len);
                onUpLoadProcessListener.onUpLoadProcess(curLen);
            }
            is.close();


            dos.write(LINE_END.getBytes());
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
            dos.write(end_data);
            dos.flush();//刷新输出流

            //获取响应码 200=成功 当响应成功，获取响应的流
            int res = conn.getResponseCode();
            responseTime = System.currentTimeMillis();
            this.readTimeOut = (int) ((responseTime - requestTime) / 1000);
            if (res == 200) {
                InputStream inputStream = conn.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();
                int ss;
                while ((ss = inputStream.read()) != -1) {
                    stringBuffer.append((char) ss);
                }
                result = stringBuffer.toString();
                Log.e(TAG, "result : " + result);
                sendMessage(UPLOAD_SUCCESS_CODE, "上传结果：" + result);
                return;
            } else {
                sendMessage(UPLOAD_SERVER_ERROR_CODE, "上传失败：code=" + res);
                return;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送长传结果
     */
    private void sendMessage(int responseCode, String responseMessage) {
        onUpLoadProcessListener.onUpLoadDone(responseCode, responseMessage);
    }


    /**
     * 自定义的回调函数
     * 上传文件是否完成
     */
    public static interface OnUpLoadProcessListener {
        /**
         * 上传响应
         *
         * @param responseCode
         * @param message
         */
        void onUpLoadDone(int responseCode, String message);

        /**
         * 上传中
         *
         * @param upLoadSize
         */
        void onUpLoadProcess(int upLoadSize);

        /**
         * 准备上传
         *
         * @param fileSize
         */
        void initUpload(int fileSize);
    }

    private OnUpLoadProcessListener onUpLoadProcessListener;

    public void setOnUploadProcessListener(OnUpLoadProcessListener onUploadProcessListener) {
        this.onUpLoadProcessListener = onUploadProcessListener;
    }

    /**
     * 读取超时
     */
    public int getReadTimeOut() {
        return readTimeOut;
    }

    /**
     * 读取超时
     */
    public void setReadTimeOut() {
        this.readTimeOut = readTimeOut;
    }

    /**
     * 超时时间
     */
    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    /**
     * 超时时间
     */
    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    /**
     * 请求使用时间
     */
    public static int getRequestTime() {
        return requestTime;
    }

    public static interface upLoadProcessListener {

    }

    /**
     * 将Bitmap转换成文件
     * 保存文件
     *
     * 压缩图片
     * @param bm
     * @param path
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File saveFile(Bitmap bm, String path, String fileName) throws IOException {
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path, fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);//将图片设置为jpeg格式，压缩为原来的80%
        bos.flush();
        bos.close();
        return myCaptureFile;
    }
}
