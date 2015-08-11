package com.tustar.fc.base;

import android.app.Activity;
import android.os.Bundle;

import com.tustar.fc.utils.Logger;

/**
 * Created by tustar on 8/12/15.
 */
public class BaseActivity extends Activity {

    protected static String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tag = getClass().getSimpleName();
        Logger.d(tag, "onCreate :: ");
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onStart() {
        Logger.d(tag, "onStart :: ");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Logger.d(tag, "onRestart :: ");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Logger.d(tag, "onResume :: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Logger.d(tag, "onPause :: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Logger.d(tag, "onStop :: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Logger.d(tag, "onDestroy :: ");
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
