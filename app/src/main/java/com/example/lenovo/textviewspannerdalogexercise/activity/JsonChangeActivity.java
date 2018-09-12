package com.example.lenovo.textviewspannerdalogexercise.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.bean.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xueww on 2017/12/1.
 */

public class JsonChangeActivity extends Activity implements View.OnClickListener {
    private Button bt_shitiToJson;
    private Button bt_jsonToShiti;
    private Button bt_jsonToList;
    private Button bt_listToJson;

    private Gson gson;
    private GsonBuilder builder;

    private Person person;

    private String jsonTest,jsonListTest;
    private List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonchange);
        init();

    }

    private void init() {
        //寻找控件
        bt_shitiToJson=(Button) findViewById(R.id.bt_shitiToJson);
        bt_jsonToShiti=(Button) findViewById(R.id.bt_jsonToShiti);
        bt_listToJson=(Button) findViewById(R.id.bt_listToJson);
        bt_jsonToList=(Button) findViewById(R.id.bt_jsonToList);
        //增加点击事件
        bt_shitiToJson.setOnClickListener(this);
        bt_jsonToShiti.setOnClickListener(this);
        bt_listToJson.setOnClickListener(this);
        bt_jsonToList.setOnClickListener(this);

        //这两句代码必须的，为的是初始化出来gson这个对象，才能拿来用
        builder=new GsonBuilder();
        gson=builder.create();

        //先制造出一个"人",第一个按钮点击的时候要用到
        person=new Person();
        person.setName("张三");
        person.setAge(20);
        person.setTall(160);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_shitiToJson://实体类转换为json数据
                jsonTest=gson.toJson(person, Person.class);
                Log.e("test", jsonTest);
                //打印出来结果为
                // {"name":"张三","age":20,"tall":160}

                break;
            case R.id.bt_jsonToShiti://json数据转换为实体类
                Person p=gson.fromJson(jsonTest, Person.class);
                Log.e("test", p.getName()+" "+p.getAge()+" "+p.getTall());
                //打印出来结果为
                //张三 20 160

                break;
            case R.id.bt_listToJson://存储实体类的集合转换为json数据集合
                //手动制造一个存有三人信息的集合,以便进行测试
                persons=new ArrayList<Person>();
                for(int i=0;i<3;i++){
                    Person p1=new Person();
                    p1.setName("李四"+i);
                    p1.setAge(23+i);
                    p1.setTall(165+i);
                    persons.add(p1);
                }
                //persons被制造好了，现在开始测试
                //需要注意的是这里的Type导入的是java.lang.reflect.Type的包
                //TypeToken导入的是 com.google.gson.reflect.TypeToken的包
                Type type=new TypeToken<List<Person>>(){}.getType();
                jsonListTest=gson.toJson(persons, type);
                Log.e("test", jsonListTest);
                //打印出来的数据
// [{"name":"李四0","age":23,"tall":165},{"name":"李四1","age":24,"tall":166},{"name":"李四2","age":25,"tall":167}]
                break;
            case R.id.bt_jsonToList://json数据的集合转换为存储实体类的集合
                List<Person> p2=new ArrayList<Person>();
                Type type1=new TypeToken<List<Person>>(){}.getType();
                p2=gson.fromJson(jsonListTest, type1);
                Log.e("test", p2.size()+"");
                //打印了存储实体类集合的大小，不用看啦，，大小肯定是3
                //打印结果
                //3
                break;

        }

    }

}
