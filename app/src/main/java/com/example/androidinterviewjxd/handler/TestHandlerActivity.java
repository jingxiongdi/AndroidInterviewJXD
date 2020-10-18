package com.example.androidinterviewjxd.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidinterviewjxd.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestHandlerActivity extends AppCompatActivity {
    private static final String TAG = "TestHandlerActivity_jxd";
    private static final int NEW_MESSAGE = 111;
    private static final int HANDLER_OBTAIN_MESSAGE = 222;
    private static final int HANDLER_OBTAIN_MESSAGE_2 = 333;
    private static final int HANDLER_OBTAIN_MESSAGE_3 = 444;
    private static final int MESSAGE_OBTAIN_1 = 555;

    private volatile long sendNewMessageTime = 0;
    private volatile long sendHandlerObtainTime = 0;
    private volatile long sendHandlerObtainTime2 = 0;
    private volatile long sendHandlerObtainTime3 = 0;
    private volatile long sendMessageObtain1 = 0;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case NEW_MESSAGE:
                    Log.d(TAG,"NEW_MESSAGE: "+(SystemClock.elapsedRealtimeNanos() -sendNewMessageTime));
                    break;
                case HANDLER_OBTAIN_MESSAGE:
                    Log.d(TAG,"HANDLER_OBTAIN_MESSAGE: "+(SystemClock.elapsedRealtimeNanos() -sendHandlerObtainTime));
                    break;
                case HANDLER_OBTAIN_MESSAGE_2:
                    Log.d(TAG,"HANDLER_OBTAIN_MESSAGE_2: "+(SystemClock.elapsedRealtimeNanos() -sendHandlerObtainTime2));
                    break;
                case HANDLER_OBTAIN_MESSAGE_3:
                    Log.d(TAG,"HANDLER_OBTAIN_MESSAGE_3: "+(SystemClock.elapsedRealtimeNanos() -sendHandlerObtainTime3));
                    break;
                case MESSAGE_OBTAIN_1:
                    Log.d(TAG,"MESSAGE_OBTAIN_1: "+(SystemClock.elapsedRealtimeNanos() -sendMessageObtain1));
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                int i=1;
                while (i<10){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                    if(i%2==0){
                        Message message = new Message();
                        message.what = NEW_MESSAGE;
                        message.obj = i*2;
                        sendNewMessageTime = SystemClock.elapsedRealtimeNanos();
                        handler.sendMessage(message);

                        Message messageHandlerObtain = handler.obtainMessage();
                        messageHandlerObtain.what = HANDLER_OBTAIN_MESSAGE;
                        messageHandlerObtain.obj = i*2;
                        sendHandlerObtainTime = SystemClock.elapsedRealtimeNanos();
                        handler.sendMessage(messageHandlerObtain);

                        Message messageHandlerObtain2 = handler.obtainMessage(HANDLER_OBTAIN_MESSAGE_2);
                        messageHandlerObtain2.obj = i*2;
                        sendHandlerObtainTime2 = SystemClock.elapsedRealtimeNanos();
                        handler.sendMessage(messageHandlerObtain2);

                        Message messageObtain1 = Message.obtain();
                        messageObtain1.what = MESSAGE_OBTAIN_1;
                        messageObtain1.obj = i*2;
                        sendMessageObtain1 = SystemClock.elapsedRealtimeNanos();
                        handler.sendMessage(messageObtain1);

                        Message messageHandlerObtain3 = handler.obtainMessage(HANDLER_OBTAIN_MESSAGE_3);
                        messageHandlerObtain3.obj = i*2;
                        sendHandlerObtainTime3 = SystemClock.elapsedRealtimeNanos();
                        messageHandlerObtain3.sendToTarget();

                    }
                }


            }
        });

        //这里演示idlehandler的调用时机
        handler.getLooper().myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                //你要处理的事情
                Log.d(TAG,"addIdleHandler oncreate ===============");
                return false;
            }
        });
        Log.d(TAG,"oncreate-----");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                Log.d(TAG,"addIdleHandler onResume ===============");
                return false;
            }
        });
        Log.d(TAG,"onresume--------");
    }
}
