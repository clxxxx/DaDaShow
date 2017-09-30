package com.xzry.takeshow.utils;

import android.content.Context;

/**
 * @author: luosy
 * @date: 2017-9-27
 * @description:
 */


public class ImageLoaderConfiguration{
    public void getConfig(Context context){
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration
//                .Builder(context)
//                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
//                .threadPoolSize(3)//线程池内加载的数量
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .denyCacheImageMultipleSizesInMemory()
////                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
//                .memoryCacheSize(2 * 1024 * 1024)
//                .discCacheSize(50 * 1024 * 1024)
////                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
////                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .discCacheFileCount(100) //缓存的文件数量
//                .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
//                .writeDebugLogs() // Remove for release app
//                .build();//开始构建
//         Initialize ImageLoader with configuration.
    }
}
