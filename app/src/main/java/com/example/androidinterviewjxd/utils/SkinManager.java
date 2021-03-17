package com.example.androidinterviewjxd.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Method;

public class SkinManager {
    private static volatile SkinManager instance = null;
    private Context context;
    private Resources resources;
    private String packageName;

    private SkinManager() {
    }

    public void init(Context context){
        this.context = context;
    }
    //懒汉式
    public static SkinManager getInstance() {
        if (instance == null) {
            synchronized(SkinManager.class){
                if(instance == null){
                    instance = new SkinManager();
                }
            }
        }
        return instance;
    }

    /**
     * 根据资源包的存储路径去加载
     */
    public void loadResourceApk(String path){
        try {
            //获取到包管理器
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
            packageName = packageArchiveInfo.packageName;
            //Android真正的资源管理者
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath",String.class);
            addAssetPath.invoke(assetManager,path);
            //因为只要手机不变，DisplayMetrics和Configuration就不会变，所以可以从传入的context去获取
            resources = new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据名字和类型去资源包中匹配资源
     * @param name
     * @param type
     * @return
     */
    public int getColor(String name,String type){
        if(name.isEmpty() || type.isEmpty()){
            return 0;
        }
        int identifier = resources.getIdentifier(name,type,packageName);
        if(identifier==0){
            return 0;
        }
        return resources.getColor(identifier);
    }

    /**
     * 根据名字和类型去资源包中匹配资源
     * @param name
     * @param type
     * @return
     */
    public Drawable getDrawable(String name, String type){
        if(name.isEmpty() || type.isEmpty()){
            return null;
        }
        int identifier = resources.getIdentifier(name,type,packageName);
        if(identifier==0){
            return null;
        }
        return resources.getDrawable(identifier);
    }

}
