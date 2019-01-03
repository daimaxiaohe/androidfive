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
    private int cx=50;
    private int cy=50;
    private Paint paint;
    private int radius=50;
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
    //测量的方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    //布局的方法
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    //绘制的方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx,cy,radius,paint);
    }

    //初始化画笔的方法
    private void init(){
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(70);
    }
    //事件分发

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //将当前得位置告诉x y轴
                cx = (int) event.getX();
                cy = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                //将当前得位置告诉x y轴
                cx = (int) event.getX();
                cy = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    /*public MyView(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/
}
