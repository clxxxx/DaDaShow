package com.sxjs.common.widget.spaceitemline;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * @author: luosy
 * @date: 2017-8-26
 * @description:
 */


public class SpaceItemLine extends RecyclerView.ItemDecoration {

    public static final  int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;

    protected Drawable mDivider;

    private int space;  //分割线高度
    private int orientation = 3;  //分割线位置

    /**
      * @param space 分割线高度
      */
    public SpaceItemLine(int space) {
        this.space = space;
    }

    /**
     * @param space 分割线高度
     */
    public SpaceItemLine(int space, int mColor) {
        this.space = space;
        if (mColor != 0) {
            mDivider = new ColorDrawable(mColor);
        }
    }

    /**
     * @param space 分割线高度
     * @param orientation 分割线的方向
     */
    public SpaceItemLine(int space, int orientation, int mColor) {
        this.space = space;
        this.orientation = orientation;
        if (mColor != 0) {
            mDivider = new ColorDrawable(mColor);
        }
    }


    //获取分割线尺寸
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        switch (orientation)
        {
            case LEFT:
                outRect.left = space;
                outRect.top = 0;
                outRect.right = 0;
                outRect.bottom = 0;
                break;
            case TOP:
                outRect.left = 0;
                outRect.top = space;
                outRect.right = 0;
                outRect.bottom = 0;
                break;
            case RIGHT:
                outRect.left = 0;
                outRect.top = 0;
                outRect.right = space;
                outRect.bottom = 0;
                break;
            case BOTTOM:
                outRect.left = 0;
                outRect.top = 0;
                outRect.right = 0;
                outRect.bottom = space;
                break;
            default:
                outRect.left = 0;
                outRect.top = 0;
                outRect.right = 0;
                outRect.bottom = space;
                break;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        //没有子view或者没有没有颜色直接return
        if (mDivider == null || layoutManager.getChildCount() == 0) {
            return;
        }
        int left;
        int right;
        int top;
        int bottom;
        final int childCount = parent.getChildCount();
        if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                //将有颜色的分割线处于中间位置
                float center = (layoutManager.getTopDecorationHeight(child) - space) / 2;
                //计算下边的
                left = layoutManager.getLeftDecorationWidth(child);
                right = parent.getWidth() - layoutManager.getLeftDecorationWidth(child);
                top = (int) (child.getBottom() + params.bottomMargin + center);
                bottom = top + space * 2;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        } else {
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                //将有颜色的分割线处于中间位置
                float center = (layoutManager.getLeftDecorationWidth(child) - space) / 2;
                //计算右边的
                left = (int) (child.getRight() + params.rightMargin + center);
                right = left + space * 2;
                top = layoutManager.getTopDecorationHeight(child);
                bottom = parent.getHeight() - layoutManager.getTopDecorationHeight(child);
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

}
