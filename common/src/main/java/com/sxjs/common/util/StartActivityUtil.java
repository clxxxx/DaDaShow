package com.sxjs.common.util;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

/**
 * @author: luosy
 * @date: 2017-8-25
 * @description:
 */


public class StartActivityUtil {
    public static void toActivity(Context context, Class clss){
        Intent intent=new Intent(context,clss);
        context.startActivity(intent);
    }
    public static void toActivity(Context context, Class clss,String str){
        Intent intent=new Intent(context,clss);
        intent.putExtra("str", str);
        context.startActivity(intent);
    }
    public static void toActivity(Context context, Class clss,Object obj){
        Intent intent=new Intent(context,clss);
        intent.putExtra("obj", (Serializable) obj);
        context.startActivity(intent);
    }
}
