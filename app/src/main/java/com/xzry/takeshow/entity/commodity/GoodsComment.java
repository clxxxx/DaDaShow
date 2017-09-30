package com.xzry.takeshow.entity.commodity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 周东阳 on 2017/9/2 0002.
 */

public class GoodsComment implements Serializable{

    public List<String> commentImg;//评论图片，没有图片返回空集合
    public String color; //评论商品的颜色
    public String size;//评论商品的尺寸
    public String nickname;//评论人昵称
    public int count;//评论总数
    public String comment;//评论内容
    public String headerUrl;//评论人头像昵称
}
