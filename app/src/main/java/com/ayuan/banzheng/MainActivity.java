package com.ayuan.banzheng;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyConnection myConnection;
    private BanZheng.MyBinder myBinder;//自定义的中间人对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //连接服务
        Intent intent = new Intent(this, BanZheng.class);
        myConnection = new MyConnection();
        boolean b = bindService(intent, myConnection, BIND_AUTO_CREATE);
        if (b) {
            Toast.makeText(this, "服务连接成功", Toast.LENGTH_SHORT).show();
        }
        Button viewById = (Button) findViewById(R.id.btn_banzheng);

        //点击按钮调用服务里面的办证方法
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBinder != null) {
                    myBinder.callBanZheng(200);
                }
            }
        });
    }

    //这个类是用来监视服务的状态
    public class MyConnection implements ServiceConnection {
        //当服务连接成功时调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取中间人对象
            myBinder = (BanZheng.MyBinder) service;
        }

        //当服务失去连接时调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当activity销毁的时候解绑服务
        unbindService(myConnection);
    }
}
