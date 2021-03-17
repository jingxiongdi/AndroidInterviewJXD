package com.example.androidinterviewjxd.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static String saveFileByMediaStore(Context context,String name, String content) {
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.ImageColumns.RELATIVE_PATH, "Download/jxd/");
        contentValues.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.Images.ImageColumns.MIME_TYPE, "text/plain");
        Uri uri = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
        if (uri == null) {
            return "";
        }
        OutputStream out = null;
        InputStream in = null;
        try {
            out = contentResolver.openOutputStream(uri);
            byte[] src = content.getBytes(StandardCharsets.UTF_8);
            if (src.length > 1024) {
                in = new ByteArrayInputStream(src);
                byte[] bytes = new byte[1024];
                while (in.read(bytes) != -1) {
                    out.write(bytes);
                }
            } else {
                out.write(src);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Environment.getExternalStorageDirectory() + "/Download/jxd/" + name + ".txt";
    }

    public static String saveBitmapByMediaStore(Context context,Bitmap bitmap, String name) {
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.ImageColumns.RELATIVE_PATH, "Pictures/jxd/");
        contentValues.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/jpeg");
        contentValues.put(MediaStore.Images.ImageColumns.WIDTH, bitmap.getWidth());
        contentValues.put(MediaStore.Images.ImageColumns.HEIGHT, bitmap.getHeight());
        //会向Pictures/jxd/目录创建name.jpg的文件
        Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (uri == null) {
            return "";
        }
        //写入图片
        OutputStream out = null;
        try {
            out = contentResolver.openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Environment.getExternalStorageDirectory() + "/Pictures/jxd/" + name + ".jpg";
    }
}
