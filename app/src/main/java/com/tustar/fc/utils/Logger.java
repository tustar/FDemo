package com.tustar.fc.utils;

import android.util.Log;

/**
 * Created by tustar on 8/1/15.
 */


public class Logger {


    private static final boolean DEBUG = true;

    private Logger() {
    }

    public static int v(String tag, String msg) {
        if (DEBUG) {
            return Log.v(tag, msg);
        }

        return 0;
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            return Log.v(tag, msg, tr);
        }
        return 0;
    }

    public static int d(String tag, String msg) {
        if (DEBUG) {
            return Log.d(tag, msg);
        }

        return 0;
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            return Log.d(tag, msg, tr);
        }
        return 0;
    }

    public static int i(String tag, String msg) {
        if (DEBUG) {
            return Log.i(tag, msg);
        }

        return 0;
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            return Log.i(tag, msg, tr);
        }
        return 0;
    }

    public static int w(String tag, String msg) {
        if (DEBUG) {
            return Log.w(tag, msg);
        }

        return 0;
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            return Log.w(tag, msg, tr);
        }
        return 0;
    }

    public static int e(String tag, String msg) {
        if (DEBUG) {
            return Log.e(tag, msg);
        }

        return 0;
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            return Log.e(tag, msg, tr);
        }
        return 0;
    }

    public static int t(String TAG, String msg, Object... args) {
        if (DEBUG) {
            return Log.v(TAG, String.format(msg, args));
        }
        return 0;
    }
}
