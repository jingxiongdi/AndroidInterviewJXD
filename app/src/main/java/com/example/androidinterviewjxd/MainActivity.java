package com.example.androidinterviewjxd;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidinterviewjxd.activity.AActivity;
import com.example.androidinterviewjxd.designmodel.LiShiReplaceActivity;
import com.example.androidinterviewjxd.handler.TestHandlerActivity;
import com.example.androidinterviewjxd.io.FileObseverActivity;
import com.example.androidinterviewjxd.layout.ConstraintLayoutActivity;
import com.example.androidinterviewjxd.protobuf.ProtoBufTestActivity;
import com.example.androidinterviewjxd.thread.ControlThreadExecuteActivity;
import com.example.androidinterviewjxd.utils.NetworkUtils;
import com.example.androidinterviewjxd.view.ViewTest1Activity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_CODE = 1;
    private String[] permissionArr = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.ACCESS_WIFI_STATE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestNeedPermission();

        judgeNetwork();
    }

    private void judgeNetwork() {
       boolean hasNetwork =  NetworkUtils.isNetworkConnected(this);
       String networkType = NetworkUtils.getCurrentNetworkType(this);
       Toast.makeText(this,"hasNetwork: "+hasNetwork+" networkType: "+networkType,Toast.LENGTH_SHORT).show();
    }

    private void requestNeedPermission() {
        //@RequiresApi仅仅只能让编译通过，并不能解决问题。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionArr,REQUEST_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onABActivityChange(View v){
        startActivity(new Intent(MainActivity.this, AActivity.class));
    }

    public void intoTestHandler(View v){
        startActivity(new Intent(MainActivity.this, TestHandlerActivity.class));
    }

    public void viewTest1(View v){
        startActivity(new Intent(MainActivity.this, ViewTest1Activity.class));
    }

    public void controlThread(View v){
        startActivity(new Intent(MainActivity.this, ControlThreadExecuteActivity.class));
    }

    public void designModel(View v){
        startActivity(new Intent(MainActivity.this, LiShiReplaceActivity.class));
    }

    public void useProtobuf(View v){
        startActivity(new Intent(MainActivity.this, ProtoBufTestActivity.class));
    }

    public void useConstraintLayout(View v){
        startActivity(new Intent(MainActivity.this, ConstraintLayoutActivity.class));
    }

    public void testFileObsever(View v){
        startActivity(new Intent(MainActivity.this, FileObseverActivity.class));
    }
}
