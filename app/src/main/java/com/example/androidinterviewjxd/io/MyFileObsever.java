package com.example.androidinterviewjxd.io;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.FileObserver;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.List;

public class MyFileObsever extends FileObserver {
    private Context context;
    /**
     * Equivalent to FileObserver(file, FileObserver.ALL_EVENTS).
     *
     * @param file
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public MyFileObsever(@NonNull File file, Context context) {
        super(file,FileObserver.ALL_EVENTS);
        this.context = context;
    }

    /**
     * The event handler, which must be implemented by subclasses.
     *
     * <p class="note">This method is invoked on a special FileObserver thread.
     * It runs independently of any threads, so take care to use appropriate
     * synchronization!  Consider using {@link Handler#post(Runnable)} to shift
     * event handling work to the main thread to avoid concurrency problems.</p>
     *
     * <p>Event handlers must not throw exceptions.</p>
     *
     * @param event The type of event which happened
     * @param path  The path, relative to the main monitored file or directory,
     *              of the file or directory which triggered the event.  This value can
     *              be {@code null} for certain events, such as {@link #MOVE_SELF}.
     */
    @Override
    public void onEvent(final int event, @Nullable String path) {
        Log.d("FileObseverTest","event "+event+" path: "+path);
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,"event "+event,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
