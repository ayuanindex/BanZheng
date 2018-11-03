package com.ayuan.banzheng;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BanZheng extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        //返回自定义的中间人对象
        return new MyBinder();
    }

    public void banZheng(int money) {
        if (1000 < money) {
            Toast.makeText(this, "我是领导把证给你办了", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "这点钱还想办证！！！！！", Toast.LENGTH_SHORT).show();
        }
    }

    //定义中间人对象（IBinder）
    public class MyBinder extends Binder {
        public void callBanZheng(int money) {
            //调用办证的方法
            banZheng(money);
        }
    }
}
