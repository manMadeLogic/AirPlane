package com.example.boxfishedu.airplane.thread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.boxfishedu.airplane.util.GameView;

// +----------------------------------------------------------------------
// | CreateTime: 17/1/4 
// +----------------------------------------------------------------------
// | Author:     xab(http://www.xueyong.net.cn)
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public class DrawThread extends Thread {

    private int SLEEP_SPAN =50;//睡眠的毫秒数
    private SurfaceHolder surfaceHolder;
    private GameView view;
    private boolean flag = true;

    public DrawThread(SurfaceHolder surfaceHolder, GameView view) {//构造器
        this.surfaceHolder = surfaceHolder;
        this.view = view;
    }


    public void setFlag(boolean flag) {//设置循环标记位
        this.flag = flag;
    }


    public void run()
    {
        Canvas c;
        while(flag)
        {
            c = null;
            try
            {// 锁定整个画布，在内存要求比较高的情况下，建议参数不要为null
                c = this.surfaceHolder.lockCanvas(null);
                synchronized (this.surfaceHolder)
                {
                    this.view.draw(c);
                }
            } finally
            {
                if (c != null)
                {
                    //更新屏幕显示内容
                    this.surfaceHolder.unlockCanvasAndPost(c);
                }
            }
            try
            {
                Thread.sleep(SLEEP_SPAN);//睡眠指定毫秒数
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
