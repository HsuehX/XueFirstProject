package com.example.lenovo.textviewspannerdalogexercise;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.activity.AddressActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.ArrayAdapterActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.ChoosePictureActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.CopyQQListViewActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.DynamicLayoutActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.GetMapActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.ItemCheckBoxActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.JsonChangeActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.DrawingActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.LineDrawingActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.ListActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.PictureActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.PictureLubanActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.PortActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.PortDrawingActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.RadioButtonActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.RemeberActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.SideslipActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.SwipeListViewActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.SwipeListViewDataActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.TestLoadViewActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.WebActivtiy;
import com.example.lenovo.textviewspannerdalogexercise.bean.SpannerDialogBean;
import com.example.lenovo.textviewspannerdalogexercise.utils.PermissionUtils;
import com.example.lenovo.textviewspannerdalogexercise.view.CommonSpinDialog;
import com.example.lenovo.textviewspannerdalogexercise.view.PCCodeDialog;
import com.example.lenovo.textviewspannerdalogexercise.view.SimpleAdapter;
import com.example.lenovo.textviewspannerdalogexercise.view.SimpleDialog;
import com.example.lenovo.textviewspannerdalogexercise.view.wheelview.DataPickerPopWindow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;


public class MainActivity extends Activity implements View.OnClickListener, DataPickerPopWindow.PopDataPickerWindow, View.OnTouchListener, PermissionUtils.PermissionGrant {

    private CommonSpinDialog mDialog;
    private int mCurrent;
    private TextView mXiaLa;
    private Button mBtn;
    private int mCurrentResId;
    private List<String> mNewList = new ArrayList<>();
    private List<SpannerDialogBean> mProvinceList;

    private DataPickerPopWindow dataPickerPopWindow;
    private LinearLayout main;
    private TextView text;
    private int year;
    private int month;
    private int day;
    private String mShowTime;

    private EditText mPickUpNumSixEt;

    private EditText one_Ed;
    private EditText two_Ed;
    //    private EditText edt_second;
    private TextView mAddressTv;
    private TextView v;

    private TextView two_tv;

    private SimpleAdapter simpleAdapter;
    private SimpleDialog simpleDialog;


    private TextView mCheckBoxTv;

    private EditText mInputOneEt;
    private EditText mInputTwoEt;

    private Button web;
    private Button array;
    private Button picture;
    private Button drawing;
    private Button line_drawing;
    private Button port_drawing;
    private Button port;
    private Button getmap_tv;


    private TextView mListCheckBoxTv;
    private TextView mTestViewTv;

    private TextView mSimpleSwpeListViewTv;
    private TextView mSwpeListViewTv;
    private TextView mSwpeListViewDataTv;
    private TextView mCopyQQListViewDeleteTv;

    private TextView mRemeberTv;
    private TextView radio_tv;

    private TextView choose_picture_tv;
    private TextView photo_picture_tv;

    private TextView dynamic_layout_tv;

    private TextView second_activity;

//
//    // 要申请的权限
//    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//    private AlertDialog dialog;

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        view = View.inflate(this, R.layout.activity_main, null);
//        setContentView(view);


        checkUriPermission(this, this);
        /**
         * 动态权限获取
         **/
//        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            // 检查该权限是否已经获取
//            int i = ContextCompat.checkSelfPermission(this, permissions[0]);
//            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
//            if (i != PackageManager.PERMISSION_GRANTED) {
//                // 如果没有授予该权限，就去提示用户请求
//                showDialogTipUserRequestPermission();
//            }
//        }

        second_activity = (TextView) findViewById(R.id.second_activity);
        second_activity.setOnClickListener(this);

        dynamic_layout_tv = (TextView) findViewById(R.id.dynamic_layout_tv);
        dynamic_layout_tv.setOnClickListener(this);

        photo_picture_tv = (TextView) findViewById(R.id.photo_picture_tv);
        photo_picture_tv.setOnClickListener(this);

        choose_picture_tv = (TextView) findViewById(R.id.choose_picture_tv);
        choose_picture_tv.setOnClickListener(this);


        radio_tv = (TextView) findViewById(R.id.radio_tv);
        radio_tv.setOnClickListener(this);

        mRemeberTv = (TextView) findViewById(R.id.remeber_tv);
        mRemeberTv.setOnClickListener(this);

        mCopyQQListViewDeleteTv = (TextView) findViewById(R.id.copy_qq_lisview_tv);
        mCopyQQListViewDeleteTv.setOnClickListener(this);


        mSwpeListViewDataTv = (TextView) findViewById(R.id.swipelist_data_tv);
        mSwpeListViewDataTv.setOnClickListener(this);
        mSwpeListViewTv = (TextView) findViewById(R.id.swipelist_tv);
        mSwpeListViewTv.setOnClickListener(this);
        mSimpleSwpeListViewTv = (TextView) findViewById(R.id.swipe_list_tv);
        mSimpleSwpeListViewTv.setOnClickListener(this);


        mTestViewTv = (TextView) findViewById(R.id.test_load_list_tv);
        mTestViewTv.setOnClickListener(this);


        mListCheckBoxTv = (TextView) findViewById(R.id.list_tv);
        mListCheckBoxTv.setOnClickListener(this);


        Button drawing = (Button) findViewById(R.id.drawing);
        drawing.setOnClickListener(this);

        Button getmap_tv = (Button) findViewById(R.id.getmap_tv);
        getmap_tv.setOnClickListener(this);


        Button port = (Button) findViewById(R.id.port);
        port.setOnClickListener(this);

        /**
         * edittext获取焦点，弹出软键盘
         */
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        Button port_drawing = (Button) findViewById(R.id.port_drawing);
        port_drawing.setOnClickListener(this);


        Button line_drawing = (Button) findViewById(R.id.line_drawing);
        line_drawing.setOnClickListener(this);

        Button picture = (Button) findViewById(R.id.picture);
        picture.setOnClickListener(this);

        Button array = (Button) findViewById(R.id.array);
        array.setOnClickListener(this);

        Button web = (Button) findViewById(R.id.web);
        web.setOnClickListener(this);

        TextView v = (TextView) findViewById(R.id.callback_tv);
        v.setOnClickListener(this);

        mCheckBoxTv = (TextView) findViewById(R.id.checkbox_test_tv);
        mCheckBoxTv.setOnClickListener(this);


        two_tv = (TextView) findViewById(R.id.two_tv);
        two_tv.setOnClickListener(this);

        mAddressTv = (TextView) findViewById(R.id.address);
        mAddressTv.setOnClickListener(this);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());
        //获取当前时间
        mShowTime = formatter.format(curDate);

        main = (LinearLayout) findViewById(R.id.main);
        text = (TextView) findViewById(R.id.text);
        text.setText(mShowTime);
        mXiaLa = (TextView) findViewById(R.id.xiala);
        mXiaLa.setOnClickListener(this);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);

        mNewList = new ArrayList<String>();
        mNewList.add("北京");
        mNewList.add("大连");
        mNewList.add("沈阳");
        mNewList.add("营口");
        simpleDialog = new SimpleDialog(this, 2);
        simpleDialog.setOnListItemClickListener(new SimpleDialog.ListViewOnItemCLickListener() {
            @Override
            public void onListItemClickListener(String text, int resId, int position) {
                switch (resId) {
                    case R.id.btn:
                        mCurrent = position;
                        mXiaLa.setText(text);
                        simpleDialog.setCurrentSelectionWithTheme3(position);
                        break;
                    default:
                        break;
                }
            }
        });
        simpleDialog.create();
        simpleDialog.listViewClickSelf(R.id.xiala);


        mProvinceList = new ArrayList<SpannerDialogBean>();
//        mProvinceList.add(new SpannerDialogBean("111", "北京"));
//        mProvinceList.add(new SpannerDialogBean("222", "大连"));
//        mProvinceList.add(new SpannerDialogBean("333", "沈阳"));
//        mProvinceList.add(new SpannerDialogBean("444", "营口"));

        mProvinceList.add(new SpannerDialogBean("111", "北京", "123456789"));
        mProvinceList.add(new SpannerDialogBean("222", "大连", "123451234"));
        mProvinceList.add(new SpannerDialogBean("333", "沈阳", "678956789"));
        mProvinceList.add(new SpannerDialogBean("444", "营口", "123456789"));
//        mProvinceList.add("北京");
//        mProvinceList.add("大连");
//        mProvinceList.add("沈阳");
//        mProvinceList.add("营口");
        mXiaLa.setText(mProvinceList.get(0).getText());
        mDialog = new CommonSpinDialog(this, 3);
        mDialog.setOnListItemClickListener(new CommonSpinDialog.ListViewOnItemCLickListener() {
            @Override
            public void onListItemClickListener(String text, int resId, int position) {
                switch (resId) {
                    case R.id.btn:
                        mCurrent = position;
                        mXiaLa.setText(text);
                        mDialog.setCurrentSelectionWithTheme3(position);
                        break;
                    default:
                        break;
                }
            }
        });
        mDialog.create();
        mDialog.listViewClickSelf(R.id.xiala);

        mPickUpNumSixEt = (EditText) findViewById(R.id.edit_et);


        final PCCodeDialog dialog = new PCCodeDialog(MainActivity.this);
        dialog.setCanceledOnTouchOutside(false);


        TextWatcher tw = new TextWatcher() {

            //@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            //@Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            //@Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    if (one_Ed.isFocused()) {
                        one_Ed.clearFocus();
                        two_Ed.requestFocus();
                    } else if (two_Ed.isFocused()) {
                        two_Ed.clearFocus();
                        mPickUpNumSixEt.requestFocus();
                    }
                }
            }
        };
        one_Ed = (EditText) findViewById(R.id.edit_one);
        one_Ed.setText("0");
        two_Ed = (EditText) findViewById(R.id.edit_two);
        one_Ed.addTextChangedListener(tw);
        two_Ed.addTextChangedListener(tw);
//        edt_hour = (EditText) findViewById(R.id.hour);
//        edt_hour.addTextChangedListener(tw);
//        edt_minute = (EditText) findViewById(R.id.minute);
//        edt_minute.addTextChangedListener(tw);
//        edt_second = (EditText) findViewById(R.id.second);
//        edt_second.addTextChangedListener(tw);
        mInputOneEt = (EditText) findViewById(R.id.edit_input_one);
        mInputOneEt.setText("0");
        mInputOneEt.setOnTouchListener(this);
        mInputTwoEt = (EditText) findViewById(R.id.edit_input_two);
        mInputTwoEt.setText("0");
        mInputTwoEt.setOnTouchListener(this);

        /**
         * 回车事件监听
         */
        mPickUpNumSixEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //软键盘的判断处理要在这里写
                String ss = mPickUpNumSixEt.getText().toString();
                /**
                 * 线判断第一个是否是0，然后判断下一位是否还是0，如果是，继续判断。。。。
                 * 如果下一位不为0，则判断是否为 .  如果是 .  输出时前面加一位0，如果不是，则正常输出
                 */
                String text = "";
                text = ss;
                for (int i = 0; i < ss.length(); i++) {
                    if ("0".equals(text.substring(0, 1))) {
                        if (i == ss.length() - 1) {
                            text = "0";
                            break;
                        } else {

                        }
                        text = text.substring(1);
                        continue;
                    } else {
                        break;
                    }
                }
                if (text.startsWith(".", 0)) {
                    text = "0" + text;
                }
//                ss = ss.substring(0,0);

                mXiaLa.setText(text);
                if (v.getId() == R.id.edit_et && actionId == EditorInfo.IME_ACTION_DONE) {
                    String barcode = mPickUpNumSixEt.getText().toString();
                    Toast.makeText(getApplicationContext(), "回车事件监听" + mPickUpNumSixEt.getText().toString(), Toast.LENGTH_SHORT);
                    return true;
                }
                Toast.makeText(getApplicationContext(), "回车事件监听" + mPickUpNumSixEt.getText().toString(), Toast.LENGTH_SHORT);
                return false;
            }
        });
//        mPickUpNumSixEt.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if(keyCode==KeyEvent.KEYCODE_NUMPAD_ENTER){
//                    Toast.makeText(MainActivity.this,"回车事件监听"+mPickUpNumSixEt.getText().toString(),Toast.LENGTH_SHORT);
//                    return true;
//                }
//                return false;
//            }
//        });

        mPickUpNumSixEt.setOnFocusChangeListener(new View.OnFocusChangeListener()

        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mPickUpNumSixEt.setRawInputType(InputType.TYPE_CLASS_NUMBER);
                    Toast.makeText(MainActivity.this, "回车事件监听" + mPickUpNumSixEt.getText().toString(), Toast.LENGTH_SHORT);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (v.getId()) {
            case R.id.btn:
                if (mDialog != null) {
                    if (mCurrentResId != id) {
                        mDialog.setCurrentSelectionWithTheme3(mCurrent);
                        mDialog.setTitleText("请选择");
                        mDialog.notifyDataSetAllChanged(toList(mProvinceList));
                    }
                    mDialog.show(id);
                }
                break;
            case R.id.address:
                Intent intent = new Intent(MainActivity.this, AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.callback_tv:
//                testCallBack();
                pickUpNumber();
                break;
            case R.id.two_tv:
                if (simpleDialog != null) {
                    if (mCurrentResId != id) {
                        simpleDialog.setCurrentSelectionWithTheme3(mCurrent);
                        simpleDialog.setTitleText("请选择");
                        simpleDialog.notifyDataSetAllChanged(toTwoList(mNewList));
                    }
                    simpleDialog.show(id);
                }
                break;
            case R.id.checkbox_test_tv:
                Intent intentCheckBox = new Intent(MainActivity.this, ItemCheckBoxActivity.class);
                startActivity(intentCheckBox);
                break;
            case R.id.xiala:
                Intent intentJSon = new Intent(MainActivity.this, JsonChangeActivity.class);
                startActivity(intentJSon);
                break;
            case R.id.web:
                Intent intentWeb = new Intent(MainActivity.this, WebActivtiy.class);
                startActivity(intentWeb);
                break;
            case R.id.array:
                Intent intentArray = new Intent(MainActivity.this, ArrayAdapterActivity.class);
                startActivity(intentArray);
                break;
            case R.id.picture:
                Intent intentPicture = new Intent(MainActivity.this, PictureActivity.class);
                startActivity(intentPicture);
                break;
            case R.id.drawing:
                Intent intentDrawing = new Intent(MainActivity.this, DrawingActivity.class);
                startActivity(intentDrawing);
                break;
            case R.id.line_drawing:
                Intent intentLineDrawing = new Intent(MainActivity.this, LineDrawingActivity.class);
                startActivity(intentLineDrawing);
                break;
            case R.id.port_drawing:
                Intent intentPortDrawing = new Intent(MainActivity.this, PortDrawingActivity.class);
                startActivity(intentPortDrawing);
                break;
            case R.id.port:
                Intent intentPort = new Intent(MainActivity.this, PortActivity.class);
                startActivity(intentPort);
                break;
            case R.id.getmap_tv:
                Intent intentMap = new Intent(MainActivity.this, GetMapActivity.class);
                startActivity(intentMap);
                break;
            case R.id.list_tv:
                Intent intentList = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intentList);
                break;
            case R.id.test_load_list_tv:
                Intent intentTestList = new Intent(MainActivity.this, TestLoadViewActivity.class);
                startActivity(intentTestList);
                break;
            case R.id.swipe_list_tv:
                Intent intentSwipeList = new Intent(MainActivity.this, SideslipActivity.class);
                startActivity(intentSwipeList);
                break;
            case R.id.swipelist_tv:
                Intent intentSwipeListView = new Intent(MainActivity.this, SwipeListViewActivity.class);
                startActivity(intentSwipeListView);
                break;
            case R.id.swipelist_data_tv:
                Intent intentSwipeDataList = new Intent(MainActivity.this, SwipeListViewDataActivity.class);
                startActivity(intentSwipeDataList);
                break;
            case R.id.copy_qq_lisview_tv:
                Intent intenCopyQQListViewDelete = new Intent(MainActivity.this, CopyQQListViewActivity.class);
                startActivity(intenCopyQQListViewDelete);
                break;
            case R.id.remeber_tv:
                Intent intenRemeber = new Intent(MainActivity.this, RemeberActivity.class);
                startActivity(intenRemeber);
                break;
            case R.id.radio_tv:
                Intent radio_tv = new Intent(MainActivity.this, RadioButtonActivity.class);
                startActivity(radio_tv);
                break;
            case R.id.choose_picture_tv:
                Intent choose_picture_tv = new Intent(MainActivity.this, ChoosePictureActivity.class);
                startActivity(choose_picture_tv);
                break;
            case R.id.photo_picture_tv:
                Intent photo_picture_tv = new Intent(MainActivity.this, PictureLubanActivity.class);
                startActivity(photo_picture_tv);
                break;
            case R.id.dynamic_layout_tv:
                Intent dynamic_layout_tv = new Intent(MainActivity.this, DynamicLayoutActivity.class);
                startActivity(dynamic_layout_tv);
                break;
            case R.id.second_activity:
                Intent second_activityIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(second_activityIntent);
                break;
            default:
                break;
        }
    }

//    private void initializeSpin(List<KeyToValueList> prompts) {
//        ArrayList<String> list = new ArrayList<String>();
//        for (KeyToValueList keyToValue : prompts) {
//            list.add(keyToValue.getText());
//        }
//        if (mDialog != null) {
//            mDialog.setDataList(list);
//        }
//    }

//    private ArrayList<String> toList(List<String> list) {
//        ArrayList<String> arrayList = new ArrayList<String>();
//        if (list == null) {
//            return arrayList;
//        }
//        int size = list.size();
//        for (int i = 0; i < size; i++) {
//            arrayList.add(list.get(i));
//        }
//        return arrayList;
//    }

    private ArrayList<SpannerDialogBean> toList(List<SpannerDialogBean> list) {
        ArrayList<SpannerDialogBean> arrayList = new ArrayList<SpannerDialogBean>();
        if (list == null) {
            return arrayList;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(list.get(i));
        }
        return arrayList;
    }


    private ArrayList<String> toTwoList(List<String> list) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (list == null) {
            return arrayList;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(list.get(i));
        }
        return arrayList;
    }


    // 日期选择器
    public void Data(View view) {
//        String regex = "[^0-9]";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(mShowTime);
//        m.replaceAll("").trim();

        year = Integer.parseInt(mShowTime.substring(0, 4));
        month = Integer.parseInt(mShowTime.substring(5, 7)) - 1;
        day = Integer.parseInt(mShowTime.substring(8, 10));
//        if (dataPickerPopWindow == null) {
        dataPickerPopWindow = new DataPickerPopWindow(getApplicationContext(), year, month, day);
        dataPickerPopWindow.setOnBirthdayListener(this);
//        }
        dataPickerPopWindow.showAtLocation(main, Gravity.CENTER | Gravity.BOTTOM, 0, 0);//显示位置
    }

    @Override
    public void SaveData(String birthday) {
        text.setText(birthday);
    }

    /**
     * callback
     */
    private void testCallBack() {
        final TextView v = (TextView) findViewById(R.id.callback_tv);
        /**
         * 使用自定义构造方法创建对话框
         * 第二个参数实现回调
         * 当点击对话框按钮时，在监听处理中调用回调方法，
         * 即现在实现的，传入的callback方法
         * 此方法接收 自定义对话框传入的文本框字串 ，设置到界面上
         */
        CustomAlertDialog dialog = new CustomAlertDialog(MainActivity.this, new CustomAlertDialog.ICallBack() {
            /** 通过自定义对话框所属的回调，传递参数到界面 **/
            @Override
            public void callback(String str) {
                v.setText(str);
            }
        });

		/*
        // 如果不是通过上面传入两个参数创建对话框，则须如此手动设置回调
		dialog.setCallBack(new ICallBack() {

			@Override
			public void callback(String str) {
				v.setText(str);
			}
		});
		*/

        dialog.show();
    }


    /**
     * 取货码
     */
    private void pickUpNumber() {
//        final String mailNo = mMailNoET.getText().toString();
//        PickUpCodeDialog pickUpCodeDialog = new PickUpCodeDialog(this);
        final TextView v = (TextView) findViewById(R.id.callback_tv);
        PickUpCodeDialog pickUpCodeDialog = new PickUpCodeDialog(MainActivity.this, new PickUpCodeDialog.ICallBack() {
            /** 通过自定义对话框所属的回调，传递参数到界面 **/
            @Override
            public void callback(String str) {
                v.setText(str);
            }
        });
//        pickUpCodeDialog.setCanceledOnTouchOutside(false);
        pickUpCodeDialog.show();
//        queryBatch(mailNo,pickUpCodeDialog.getReturnNumber());//取货码验证
        //取货码重发
        pickUpCodeDialog.findViewById(R.id.repeat_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                repeatPickUp(mailNo);
            }
        });
    }


    /**
     * 没看出来有用
     */
    private void hideInputMethod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mInputOneEt != null && imm != null) {
            imm.hideSoftInputFromWindow(mInputOneEt.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
//            case R.id.edit_input_one:
//                mInputOneEt.requestFocus();
//                mInputOneEt.selectAll();
//                break;
//            case R.id.edit_input_two:
//                mInputTwoEt.requestFocus();
//                mInputTwoEt.selectAll();
//                break;
//            default:
//                break;
        }
        return false;
    }

    /**
     * 没看出来有用
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }


    /****
     * 动态权限
     *
     * 下面的方法显示的是一个自定义的dialog
     */
//    // 提示用户该请求权限的弹出框
//    private void showDialogTipUserRequestPermission() {
//
//        new AlertDialog.Builder(this)
//                .setTitle("存储权限不可用")
//                .setMessage("由于支付宝需要获取存储空间，为你存储个人信息；\n否则，您将无法正常使用支付宝")
//                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startRequestPermission();
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                }).setCancelable(false).show();
//    }
//
//    // 开始提交请求权限
//    private void startRequestPermission() {
//        ActivityCompat.requestPermissions(this, permissions, 321);
//    }
//
//    // 用户权限 申请 的回调方法
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == 321) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
//                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
//                    if (!b) {
//                        // 用户还是想用我的 APP 的
//                        // 提示用户去应用设置界面手动开启权限
//                        showDialogTipUserGoToAppSettting();
//                    } else
//                        finish();
//                } else {
//                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
//
//    // 提示用户去应用设置界面手动开启权限
//
//    private void showDialogTipUserGoToAppSettting() {
//
//        dialog = new AlertDialog.Builder(this)
//                .setTitle("存储权限不可用")
//                .setMessage("请在-应用设置-权限-中，允许支付宝使用存储权限来保存用户数据")
//                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // 跳转到应用设置界面
//                        goToAppSetting();
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                }).setCancelable(false).show();
//    }
//
//    // 跳转到当前应用的设置界面
//    private void goToAppSetting() {
//        Intent intent = new Intent();
//
//        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        Uri uri = Uri.fromParts("package", getPackageName(), null);
//        intent.setData(uri);
//
//        startActivityForResult(intent, 123);
//    }
//
//    //
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 123) {
//
//            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                // 检查该权限是否已经获取
//                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
//                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
//                if (i != PackageManager.PERMISSION_GRANTED) {
//                    // 提示用户应该去应用设置界面手动开启权限
//                    showDialogTipUserGoToAppSettting();
//                } else {
//                    if (dialog != null && dialog.isShowing()) {
//                        dialog.dismiss();
//                    }
//                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }


    /****
     *动态加载，下面的方法显示的是系统自带的提示
     */
    protected boolean checkUriPermission(Activity activity, PermissionUtils.PermissionGrant grant) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            if (!Settings.canDrawOverlays(this)) {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivityForResult(intent, 1);
//            }
            int permission0 = checkSelfPermission(PermissionUtils.requestPermissions[0]);
            int permission1 = checkSelfPermission(PermissionUtils.requestPermissions[1]);
            int permission2 = checkSelfPermission(PermissionUtils.requestPermissions[2]);

            if (permission0 != PackageManager.PERMISSION_GRANTED
                    || permission1 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, PermissionUtils.requestPermissions, PermissionUtils.CODE_MULTI_PERMISSION);

                return false;
            }


//            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(activity, PermissionUtils.requestPermissions,
//                        PermissionUtils.CODE_MULTI_PERMISSION);
////                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
////                        PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE);
////                requestMulti(activity, grant);
//                return false;
//            }
            return true;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
        switch (requestCode) {
            case PermissionUtils.CODE_MULTI_PERMISSION:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
//                        && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED
//                        && grantResults[4] == PackageManager.PERMISSION_GRANTED && grantResults[5] == PackageManager.PERMISSION_GRANTED
//                        && grantResults[6] == PackageManager.PERMISSION_GRANTED && grantResults[7] == PackageManager.PERMISSION_GRANTED
//                        && grantResults[8] == PackageManager.PERMISSION_GRANTED && grantResults[9] == PackageManager.PERMISSION_GRANTED
//                        && grantResults[10] == PackageManager.PERMISSION_GRANTED) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
//                    App.getInstance().configDatabase();
                } else {
//                    showToast("授权失败，退出登录");
                    finish();
                }
                break;
            default:
                break;
        }
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
        }
    };

    @Override
    public void onPermissionGranted(int requestCode) {

    }
}
