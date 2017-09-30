package com.xzry.takeshow.utils;


import com.google.gson.Gson;

/**
 * Created by cb on 2016/5/9.
 */
public class GsonUtil {
    public static <T> T json2bean(String result,
                                  Class<T> clazz) {

        Gson gson = new Gson();

        T t = gson.fromJson(result, clazz);

        return t;
    }
}
