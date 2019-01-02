package com.example.hepengview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * はすてすゃの
 * 2019-01-02.
 */
public class MyView extends View {

    private Paint paint;
    private int circleX;
    private int circleY;
    private int mRaduis = 100;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context,AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(circleX,circleY,mRaduis,paint);
    }

    //初始化的方法啊
    private void init(){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);//空心
        paint.setStrokeWidth(20);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://按下
                circleX = (int) event.getX();//获取距离父控件的x轴坐标
//                circleX = (int) event.getRawX();//获取距离屏幕边缘的x轴坐标
                circleY = (int) event.getY();
                invalidate();//重绘

                break;
            case MotionEvent.ACTION_MOVE://滑动
                circleX = (int) event.getX();
                circleY = (int) event.getY();
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
    /*public MyView(Context context,AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/
}
