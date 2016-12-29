package com.example.CameraEye;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

/**
 * Created by ct_OS on 2016-12-29.
 */

public class CameraWake extends Service {
    private PowerManager.WakeLock wakeLock;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initWakeLock();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseWakeLock();
    }

    private void initWakeLock(){
        if (null == wakeLock)
        {
            PowerManager pm = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock)
            {
                wakeLock.acquire();
            }
        }
    }

    private void releaseWakeLock()
    {
        if (null != wakeLock)
        {
            wakeLock.release();
            wakeLock = null;
        }
    }
}
