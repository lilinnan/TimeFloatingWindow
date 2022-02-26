package com.lln.time;

import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.content.Context;

public class TimeFloatingWindowUtils {

    private static final String MIUI_TIME_FLOATING_WINDOW = "miui_time_floating_window";
    private static final ContentObserver mObserver
            = new ContentObserver(new Handler(Looper.getMainLooper())) {
        @Override
        public void onChange(boolean selfChange) {
            if (mChangedRunnable == null) {
                return;
            }
            mChangedRunnable.run();
        }
    };
    private static Runnable mChangedRunnable;

    public static boolean isTimeFloatingWindowOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), MIUI_TIME_FLOATING_WINDOW,
                0) == 1;
    }

    public static void setTimeFloatingWindowIsOn(Context context, boolean on) {
        Settings.System.putInt(context.getContentResolver(), MIUI_TIME_FLOATING_WINDOW, on ? 1 : 0);
    }

    public static void registerSettingsChangedUpdateListener(Context context, Runnable runnable) {
        if (mChangedRunnable != null) {
            return;
        }
        mChangedRunnable = runnable;
        context.getContentResolver()
                .registerContentObserver(Settings.System.getUriFor(MIUI_TIME_FLOATING_WINDOW),
                        false, mObserver);
    }

    public static void unRegisterSettingsChangedUpdateListener(Context context) {
        context.getContentResolver().unregisterContentObserver(mObserver);
        mChangedRunnable = null;
    }

}
