package com.example.androidinterviewjxd.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidinterviewjxd.R;

import org.greenrobot.eventbus.EventBus;

public class EventBusFirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_first);

        EventBus.getDefault().post(new EventOne());

        EventTwo eventTwo = new EventTwo();
        EventBus.getDefault().postSticky(eventTwo);

        //移除粘性事件 方法1
       // boolean b = EventBus.getDefault().removeStickyEvent(eventTwo);
       // Log.d("eventbustest","removeStickyEvent============="+b);

        //移除粘性事件 方法2
//        EventTwo stickyEventData = EventBus.getDefault().getStickyEvent(EventTwo.class);
//        if(stickyEventData != null) {
//            EventBus.getDefault().removeStickyEvent(stickyEventData);
//        }

        //移除粘性事件 方法3
        // EventBus.getDefault().removeAllStickyEvents();
    }


    public void enterSecond(View view){
        startActivity(new Intent(EventBusFirstActivity.this,EventBusSecondActivity.class));
    }
}