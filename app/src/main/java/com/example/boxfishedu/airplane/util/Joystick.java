package com.example.boxfishedu.airplane.util;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.boxfishedu.airplane.MainActivity;
import com.example.boxfishedu.airplane.R;

// +----------------------------------------------------------------------
// | CreateTime: 17/1/4 
// +----------------------------------------------------------------------
// | Author:     xab(http://www.xueyong.net.cn)
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public class Joystick {
    public MainActivity activity;
    public GameView view;
    public int length;
    public float x;
    public float y;
    public Joystick( GameView view,MainActivity activity,float x,float y)
    {
        this.view=view;
        this.activity=activity;
        this.x=x;
        this.y=y;
    }

    public void drawJoystick(Canvas canvas)
    {
        canvas.drawBitmap(BitmapFactory.decodeResource(this.activity.getResources(), R.drawable.yaogan1),
                Constant.JK_START_X,Constant.JK_START_Y, null);
        canvas.drawBitmap(BitmapFactory.decodeResource(this.activity.getResources(),R.drawable.yaogan2),
                x,y,null);
    }


    public boolean change(float x,float y)
    {
        length=Constant.getLength(this.view.pCenter.x,this.view.pCenter.y, x,y);
        if(length>Constant.RADIO)
        {//如果手指触点不在大环范围内
            return false;
        }
        else if(length<Constant.RADIO)
        { //如果手指在摇杆活动范围内，则摇杆处于手指触摸位置
            this.view.mJoystick.x=x;
            this.view.mJoystick.y=y;
        }
        else
        {//设置摇杆位置，使其处于手指触摸方向的 摇杆活动范围边缘
            float angle=Constant.getRadian(this.view.pCenter.x,this.view.pCenter.y, x, y);
            this.view.mJoystick.x=(int)(this.view.pCenter.x+Constant.RADIO * Math.cos(angle));
            this.view.mJoystick.y=(int)(this.view.pCenter.y+Constant.RADIO * Math.sin(angle));
        }

        int tempX = (int) (x-this.view.pCenter.x);
        int tempY = (int) (y-this.view.pCenter.y);
        this.activity.gd.rx +=tempX - this.activity.KeyDispX;
        this.activity.gd.ry +=tempY - this.activity.KeyDispY;
        this.activity.KeyDispX = tempX;
        this.activity.KeyDispY = tempY;

        //方向
//        this.activity.KeyDispX=(int) (x-this.view.pCenter.x);//x偏移量
//        this.activity.KeyDispY=(int) (y-this.view.pCenter.y);//y偏移量


        return true;
    }
}
