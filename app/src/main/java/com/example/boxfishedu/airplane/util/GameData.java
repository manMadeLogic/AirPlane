package com.example.boxfishedu.airplane.util;

// +----------------------------------------------------------------------
// | CreateTime: 17/1/4 
// +----------------------------------------------------------------------
// | Author:     xab(http://www.xueyong.net.cn)
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public class GameData {

    public static int state=1;//0--未连接  1---成功连接  2--游戏开始

    public Object lock=new Object();

    public int rx=150;
    public int ry=750;
//    public int gx=460;
//    public int gy=750;
}
