package com.example.androidinterviewjxd.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

public class NetworkUtils {
    /**
     * 判断当前网络是否连接
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = null;
        NetworkCapabilities networkCapabilities = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            network = connectivityManager.getActiveNetwork();
            networkCapabilities = connectivityManager.getNetworkCapabilities(network);
        }
        return networkCapabilities!=null;
    }

    /**
     * 判断当前的网络类型是wifi，流量还是有线网络
     * @param context
     * @return
     */
    public static String getCurrentNetworkType(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = null;
        NetworkCapabilities networkCapabilities = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            network = connectivityManager.getActiveNetwork();
            networkCapabilities = connectivityManager.getNetworkCapabilities(network);
        }
        if(networkCapabilities==null){
            return "no network";
        }else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
            return "Wifi";
        }else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
            return "Mobile";
        }
//        else if(networkCapabilities.hasCapability(NetworkCapabilities.TRANSPORT_ETHERNET)){
//            return "Ethernet";
//        }
        return "other";
    }


    private static ConnectivityManager connectivityManager;
    public static void registerNetworkListener(Context context){
        if (connectivityManager==null){
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        NetworkRequest request = builder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();
        connectivityManager.requestNetwork(request,networkCallback);
    }

    public static void unRegisterNetworkListener(Context context){
        if (connectivityManager==null){
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }

    private static ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback(){
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
        }

        @Override
        public void onLosing(@NonNull Network network, int maxMsToLive) {
            super.onLosing(network, maxMsToLive);
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
        }

        @Override
        public void onUnavailable() {
            super.onUnavailable();
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
        }

        @Override
        public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
            super.onLinkPropertiesChanged(network, linkProperties);
        }

        @Override
        public void onBlockedStatusChanged(Network network, boolean blocked) {
            super.onBlockedStatusChanged(network, blocked);
        }
    };


}
