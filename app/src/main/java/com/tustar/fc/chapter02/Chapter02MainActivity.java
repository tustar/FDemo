package com.tustar.fc.chapter02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;

import com.tustar.fc.R;
import com.tustar.fc.base.BaseActivity;
import com.tustar.fc.utils.Logger;

public class Chapter02MainActivity extends BaseActivity {

    private static final String TAG = Chapter02MainActivity.class.getSimpleName();
    private static final String DATA_KEY = "data_key";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.d(TAG, "OnCreate :: savedInstanceState = " + savedInstanceState);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chapter02_main);
        mContext = this;

        if (savedInstanceState != null) {
            String data = savedInstanceState.getString(DATA_KEY);
            Logger.d(TAG, "onCreate :: data = " + data);
        }

        Button normalBtn = (Button) findViewById(R.id.normal_btn);
        normalBtn.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, Chapter02NormalActivity.class);
            startActivity(intent);
        });

        Button dialogBtn = (Button) findViewById(R.id.dialog_btn);
        dialogBtn.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, Chapter02DialogActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chapter02_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DATA_KEY, "Helllo world!");
    }
}
