package com.example.boxfishedu.airplane;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.boxfishedu.airplane.util.GameData;
import com.example.boxfishedu.airplane.util.GameView;

public class MainActivity extends AppCompatActivity {
    public int KeyDispX=0;//方向x
    public int KeyDispY=0;//方向y

    public Bitmap planer;
//    public Bitmap planeg;
    public GameData gd=new GameData();
//    public KeyThread kt=new KeyThread(this);
//    public 	NetworkThread nt;
    GameView gv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        planer = BitmapFactory.decodeResource(getResources(), R.drawable.red);
        gv = (GameView) this.findViewById(R.id.mf1);
//        GameData.state=1;
    }
}
