package com.sxjs.common.util;

public class LogUtils {

//    public static void i(String TAG,String msg) {
//        if (BuildConfig.ISLOGSHOW) {
//            Log.i(TAG, getLogTitle() + msg);
//        }
//    }
//    public static void d(String TAG,String msg) {
//        if (BuildConfig.ISLOGSHOW) {
//            Log.i(TAG, getLogTitle() + msg);
//        }
//    }
//    public static void e(String TAG,String msg) {
//        if (BuildConfig.ISLOGSHOW) {
//            Log.i(TAG, getLogTitle() + msg);
//        }
//    }
//    public static void w(String TAG,String msg) {
//        if (BuildConfig.ISLOGSHOW) {
//            Log.i(TAG, getLogTitle() + msg);
//        }
//    }
    private static String getLogTitle() {
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int index = stackTraceElements.length > 4 ? 4 : stackTraceElements.length - 1;
        stringBuilder.append("[");
        stringBuilder.append(stackTraceElements[index].getClassName()).append(".");
        stringBuilder.append(stackTraceElements[index].getMethodName()).append("()").append(":");
        stringBuilder.append("lineNumber=").append(stackTraceElements[index].getLineNumber());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
