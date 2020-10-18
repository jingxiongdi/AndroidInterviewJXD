package com.example.androidinterviewjxd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidinterviewjxd.R;

public class AActivity extends AppCompatActivity {
    /**
     * onstart 用户可见 onresume 用户可交互  onstop 用户隐藏 onpause 用户不可交互
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Log.d("AActivity","onCreate");
    }

    public void startB(View v){
        startActivity(new Intent(AActivity.this,BActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AActivity","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AActivity","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AActivity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AActivity","onStop");
    }
}
