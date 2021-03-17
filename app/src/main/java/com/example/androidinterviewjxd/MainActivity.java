package com.example.androidinterviewjxd;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.androidinterviewjxd.activity.AActivity;
import com.example.androidinterviewjxd.designmodel.LiShiReplaceActivity;
import com.example.androidinterviewjxd.handler.TestHandlerActivity;
import com.example.androidinterviewjxd.io.FileObseverActivity;
import com.example.androidinterviewjxd.layout.ConstraintLayoutActivity;
import com.example.androidinterviewjxd.protobuf.ProtoBufTestActivity;
import com.example.androidinterviewjxd.thread.ControlThreadExecuteActivity;
import com.example.androidinterviewjxd.utils.FileUtils;
import com.example.androidinterviewjxd.utils.NetworkUtils;
import com.example.androidinterviewjxd.view.ViewTest1Activity;

import java.io.File;
import java.io.IOException;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 适配android11读写权限
            if (Environment.isExternalStorageManager()) {
               //已获取android读写权限
                writeFile();
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_PERMISSION_CODE);
            }
            return;
        }
        //@RequiresApi仅仅只能让编译通过，并不能解决问题。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionArr,REQUEST_PERMISSION_CODE);
        }
    }

    private void writeFile() {
        //这种方法可用，只是文件管理器无法查看应用的专属数据。
        File picFile = getExternalFilesDir("");
        if(!picFile.exists()){
            try {
                picFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("writeFile","path: "+picFile.getAbsolutePath());
        File file = new File( picFile.getAbsolutePath()+File.separator+"222.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //使用MediaStore api读写文件
        Bitmap bitmap = Bitmap.createBitmap(100,100, Bitmap.Config.RGB_565);
        FileUtils.saveBitmapByMediaStore(MainActivity.this,bitmap,"cc");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            FileUtils.saveFileByMediaStore(MainActivity.this,"dd","fwahawhkwahkfwha");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_CODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                //已获取android读写权限
            } else {
                //存储权限获取失败
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //已获取android读写权限
            } else {
                //存储权限获取失败
            }
        }
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
