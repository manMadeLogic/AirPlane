package com.example.boxfishedu.airplane.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.boxfishedu.airplane.MainActivity;
import com.example.boxfishedu.airplane.thread.DrawThread;

// +----------------------------------------------------------------------
// | CreateTime: 17/1/4 
// +----------------------------------------------------------------------
// | Author:     xab(http://www.xueyong.net.cn)
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    MainActivity activity;
    DrawThread drawThread;
    Paint paint;//画笔
    String str2="等待连接。。。";

    public Point pCenter=new Point(100,900);//遥感中心点
    public Joystick mJoystick;//摇杆

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.activity = (MainActivity) context;
        this.getHolder().addCallback(this);
        this.drawThread = new DrawThread(this.getHolder(), this);
        this.paint = new Paint();
        this.mJoystick = new Joystick(this,this.activity,Constant.X_JOY_STICK,Constant.Y_JOY_STICK);//摇杆
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas==null)
        {
            return;
        }
        if(GameData.state==0)
        {//游戏未连接
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.WHITE);//白色画笔
            paint.setTextSize(50);
            canvas.drawText(str2,200,700,paint);
        }
        else if(GameData.state==1){
            // start
            int rx = 0, ry = 0;
            synchronized (this.activity.gd.lock) {
                rx = this.activity.gd.rx;
                ry = this.activity.gd.ry;
            }
            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(this.activity.planer, rx, ry, paint);
            this.mJoystick.drawJoystick(canvas);//绘制摇杆

        }
//        else if(GameData.state==1)
//        {//游戏未开始
//            canvas.drawColor(Color.BLACK);
//            paint.setColor(Color.WHITE);//白色画笔
//            paint.setTextSize(100);
//            str2="等待开始。。。";
//            canvas.drawText(str2,150,700,paint);
//        }
//        else if(GameData.state==2)
//        {
//            //拷贝数据，准备绘制
//            int rx=0;
//            int gx=0;
//            int ry=0;
//            int gy=0;
//            synchronized(this.activity.gd.lock)
//            {
//                rx=this.activity.gd.rx;
//                ry=this.activity.gd.ry;
//                gx=this.activity.gd.gx;
//                gy=this.activity.gd.gy;
//            }
//            canvas.drawColor(Color.BLACK);
//            canvas.drawBitmap(this.activity.planer, rx, ry, paint);
//            canvas.drawBitmap(this.activity.planeg, gx, gy, paint);
//            this.mJoystick.drawJoystick(canvas);//绘制摇杆
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.mJoystick.change(x, y);//移动摇杆
                break;
            case MotionEvent.ACTION_MOVE:
                this.mJoystick.change(x, y);//移动摇杆
                break;
            case MotionEvent.ACTION_UP:
                this.activity.KeyDispX=0;//x偏移量为0
                this.activity.KeyDispY=0;//y偏移量为0
                this.mJoystick.x=this.pCenter.x;//回到中心点
                this.mJoystick.y=this.pCenter.y;
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.drawThread.setFlag(true);
        if(!drawThread.isAlive())//如果后台重绘线程没起来,就启动它
        {
            try
            {
                drawThread.start();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
