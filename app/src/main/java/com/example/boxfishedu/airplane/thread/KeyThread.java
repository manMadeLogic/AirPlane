package com.example.boxfishedu.airplane.thread;

import com.example.boxfishedu.airplane.MainActivity;

// +----------------------------------------------------------------------
// | CreateTime: 17/1/4 
// +----------------------------------------------------------------------
// | Author:     xab(http://www.xueyong.net.cn)
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public class KeyThread extends Thread {

    int SPAN_SLEEP=10;
    MainActivity father;
    boolean flag=true;

    public KeyThread(MainActivity father)
    {
        this.father=father;
    }

    public void run()
    {
        while(flag)
        {

            try
            {
//                if(GameData.state==2)
//                {
//                    father.nt.dout.writeUTF("<#KEY#>"+father.KeyDispX+"|"+father.KeyDispY);
//                }
                Thread.sleep(SPAN_SLEEP);

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
