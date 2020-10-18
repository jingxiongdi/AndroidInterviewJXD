package com.example.androidinterviewjxd.designmodel;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidinterviewjxd.R;

//  https://blog.csdn.net/wanlong2713/article/details/45092643
// 里氏替换原则通俗来讲就是：子类可以扩展父类的功能，但不能改变父类原有的功能。
// 里氏替换原则是继承复用的基石，只有当衍生类可以替换基类，软件单位的功能不受到影响时，即基类随便怎么改动子类都不受此影响，那么基类才能真正被复用
public class LiShiReplaceActivity extends AppCompatActivity {
    public static final String TAG = "LiShiReplaceActivity_tag";
    /**
     * 里氏替换原则
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_shi_replace);

        B b = new B();
        Log.d(TAG,b.fun(5,2)+" ");//这里会只运行B的方法
    }

    class A {
        int fun(int a,int b){
            Log.d(TAG,"A fun execute...");
            return a+b;
        }
    }

    class B extends A{
        @Override
        int fun(int a,int b){
            Log.d(TAG,"B fun execute...");
            return a-b;
        }
    }
}
