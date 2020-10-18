package com.example.androidinterviewjxd.thread;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidinterviewjxd.R;

public class ControlThreadExecuteActivity extends AppCompatActivity {
    /**
     * https://www.cnblogs.com/myseries/p/11575757.html   让线程按顺序执行8种方法
     * https://www.cnblogs.com/robinAndLaurel/p/9932083.html 并发编程面试题
     */
    private static final String TAG = "ControlThreadExecuteActivity_jxd";
    private Thread thread1,thread2,thread3;

    private static Object myLock2 = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_thread_execute);

        //****用来理解join****
       // startThread1();
       // startThread2();
       // startThread3();
        //****用来理解join****

        //wait和notiify
        startThread4();
        startThread5();
        //wait和notity
    }

    private void startThread5() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock2){
                    Log.d(TAG,"startThread5 111");
                    myLock2.notify();
                    try {
                        myLock2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG,"startThread5 222");

                }

            }
        }).start();
    }

    private void startThread4() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"startThread4 111");
                synchronized (myLock2){
                    Log.d(TAG,"startThread4 222");
                    try {
                        myLock2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    myLock2.notify();
                    Log.d(TAG,"startThread4 333");

                }
            }
        }).start();
    }

    private void startThread1(){
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    thread3.join();//join加入的线程会先于调用它的启动，但里面执行的内容
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d(TAG,"startThread1==========");
            }
        });
        thread1.start();
    }

    private void startThread2(){
       thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"startThread2==========");
            }
        });
       thread2.start();
    }

    private void startThread3(){
        thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"startThread3==========");
            }
        });
        thread3.start();
    }
}
