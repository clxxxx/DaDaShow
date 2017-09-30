package com.xzry.takeshow.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.xzry.takeshow.R;




/**
 * Created by 周东阳 on 2017/6/15 0015.
 */

public class SquareRoundCornerImageView extends ImageView {

    private final String TAG = "RoundView";

    /**绘制模式*/
    private static final Xfermode MASK_XFERMODE;
    /**绘制的图片*/
    private Bitmap mask;
    /**画笔*/
    private Paint paint;

    /**默认的圆角半径*/
    private int mRadius;

    //这边是要添加一些绘制图片的模式，这边的模式是这样子的，绘制出里面的内容。
    static{
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }

    public SquareRoundCornerImageView(Context context){
        this(context, null);
    }

    public SquareRoundCornerImageView(Context context, AttributeSet attributeSet){
        this(context, attributeSet, 0);
    }

    public SquareRoundCornerImageView(Context context, AttributeSet attributeSet, int styleInt){
        super(context, attributeSet, styleInt);
        //获取属性集合
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RoundCornerImageView);

        //取出其中的各个属性值
        mRadius = typedArray.getInt(R.styleable.RoundCornerImageView_radius, 0);
    }


    //传入参数widthMeasureSpec、heightMeasureSpec
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private boolean useDefaultStyle = false ;

    private void setUserDefaultStyle(boolean style){
        useDefaultStyle = style;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //this is important
        if (useDefaultStyle || mRadius == 0){
            super.onDraw(canvas);
            return;
        }
        final Drawable localDrawable = getDrawable();
        if (localDrawable == null){
            return;
        }
        if (localDrawable instanceof NinePatchDrawable){
            return;
        }

        //set paint object
        if (this.paint == null){
            final Paint localPaint = new Paint();
            localPaint.setFilterBitmap(false);
            localPaint.setAntiAlias(true);
            localPaint.setXfermode(MASK_XFERMODE);
            this.paint = localPaint;
        }

        final int width = getWidth();
        final int height = getHeight();

        //save layer
        int layer = canvas.saveLayer(0f, 0f, width,height, null, Canvas.ALL_SAVE_FLAG);
        //set size of drawable
        localDrawable.setBounds(0, 0, width, height);
        //here just let drawable bind to canvas
        localDrawable.draw(canvas);
        if (this.mask == null || this.mask.isRecycled()){
            this.mask = createOvalBitmap(width, height);
        }
        //draw the bitmap
        canvas.drawBitmap(this.mask, 0f, 0f, this.paint);
        //save the canvas to layout
//        canvas.restoreToCount(layer);

    }


    /**create the circle bitmap*/
    private Bitmap createOvalBitmap(int width, int height){
        //set bitmap config
        Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;
        Bitmap localBitmap = Bitmap.createBitmap(width, height, bitmapConfig);
        Canvas localCanvas = new Canvas(localBitmap);
        Paint localPaint = new Paint();
        //in order to have more better effect

        RectF rectF = new RectF(0,0,width, height);
        localCanvas.drawRoundRect(rectF, mRadius, mRadius, localPaint);
        return localBitmap;


    }
}


