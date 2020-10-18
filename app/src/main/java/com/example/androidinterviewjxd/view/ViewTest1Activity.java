package com.example.androidinterviewjxd.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidinterviewjxd.R;

public class ViewTest1Activity extends AppCompatActivity {
    private TextView textB,textC,textD;
    private static final String TAG = "ViewTest1Activity_jxd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test1);

        textB = findViewById(R.id.text1);
        textC = findViewById(R.id.text2);
        textD = findViewById(R.id.text3);

        textB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG,"textB "+event.toString());
                return false;
            }
        });

        textC.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG,"textC "+event.toString());
                return false;
            }
        });

        textD.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG,"textD "+event.toString());
                return false;
            }
        });
    }
}
