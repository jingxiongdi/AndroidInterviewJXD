package com.example.androidinterviewjxd.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidinterviewjxd.R;

public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        Log.d("BActivity","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("BActivity","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("BActivity","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("BActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("BActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("BActivity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("BActivity","onDestroy");
    }
}
