package com.example.lenovo.textviewspannerdalogexercise.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xueww on 2017/11/24.
 */

public class JsonUtils {
    public static <T> T toClass(String json, Class<T> clazz) {
        return null;
    }

//    public static <T> List<T> toList(String json, Class<T> clazz) {
//        if(json == null) {
//            return null;
//        }
//
//        List<T> result = new ArrayList<T>();
//
//        JSONReader reader = new JSONReader(new JSONLexer(json, JSON.DEFAULT_PARSER_FEATURE ^ Feature.IgnoreNotMatch.mask));
//        reader.startArray();
//        while (reader.hasNext()) {
//            result.add(reader.readObject(clazz));
//        }
//        reader.endArray();
//        return result;
//    }

    public static <T> String toJson(Object object) {
        return JSON.toJSONString(object);
    }

    public static void toClass(String find_pda_pick_up_post_info_quick_resp) {
    }
}
