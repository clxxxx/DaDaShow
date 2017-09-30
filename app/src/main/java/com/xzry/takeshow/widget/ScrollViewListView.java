package com.xzry.takeshow.widget;

import android.widget.ListView;

/**
 * Created by 周东阳 on 2017/8/21 0021.
 */

public class ScrollViewListView extends ListView {


    public ScrollViewListView(android.content.Context context,android.util.AttributeSet attrs){
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
