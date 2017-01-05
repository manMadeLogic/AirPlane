package com.example.boxfishedu.airplane.util;

// +----------------------------------------------------------------------
// | CreateTime: 17/1/4 
// +----------------------------------------------------------------------
// | Author:     xab(http://www.xueyong.net.cn)
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public class Constant {

    public static final int RADIO=80;//半径
    public static final int JK_START_X=30;//摇杆大环起点x
    public static final int JK_START_Y=830;//摇杆大环起点y
    public static final int  X_JOY_STICK=80;//摇杆小环x
    public static final int  Y_JOY_STICK=880;//摇杆小环y


    //获取水平线夹角弧度
    public static float getRadian (float x1,float y1,float x2,float y2)
    {
        float lenA=x2-x1;
        float lenB=y2-y1;
        float lenC=(float) Math.sqrt(lenA*lenA+lenB*lenB);
        float angle=(float)Math.acos(lenA/lenC);
        angle=angle*(y2<y1?-1:1);
        return angle;
    }

    //获取长度
    public static int getLength(float centerX,float centerY,float x,float y)
    {
        return (int)Math.sqrt(Math.pow(x-centerX, 2)+Math.pow(y-centerY, 2));
    }
}
