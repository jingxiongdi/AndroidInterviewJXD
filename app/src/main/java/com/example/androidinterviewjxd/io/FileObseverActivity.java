package com.example.androidinterviewjxd.io;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidinterviewjxd.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileObseverActivity extends AppCompatActivity {
    private File file = null;
    private MyFileObsever myFileObsever = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_obsever);
        file = new File(getExternalCacheDir().getAbsolutePath()+File.separator+"test.txt");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            myFileObsever = new MyFileObsever(file,FileObseverActivity.this);
            myFileObsever.startWatching();
        }
    }

    public void createFile(View v){
        try {
            if(!file.exists())
                file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(View v){
        try {
            if(file.exists())
                file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifyFile(View v){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath(),true);
            fileOutputStream.write("dddd".getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}