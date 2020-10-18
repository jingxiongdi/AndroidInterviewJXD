package com.example.androidinterviewjxd.utils;

import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static void writeByteArrToFile(String path,byte[] content){
        try {
            Log.d("FileUtils_tag",path);
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(content);
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
