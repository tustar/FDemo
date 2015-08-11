package com.tustar.fc;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.tustar.fc.db.BooksCommon;
import com.tustar.fc.utils.Logger;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBookBtn = (Button) findViewById(R.id.add_book_btn);
        addBookBtn.setOnClickListener(v -> {
            Uri uri = Uri.withAppendedPath(BooksCommon.AUTHORITY_URI, BooksCommon.Table.BOOK);
            ContentValues values = new ContentValues();
            values.put(BooksCommon.BooksColumns.NAME, "The Well-Grounded Java Developer");
            values.put(BooksCommon.BooksColumns.AUTHOR, "Benjamin J.Evans & Martijin Verburg");
            values.put(BooksCommon.BooksColumns.PAGES, 395);
            values.put(BooksCommon.BooksColumns.PRICE, 89.00);
            Uri newUri = getContentResolver().insert(uri, values);
            newId = newUri.getPathSegments().get(1);
        });

        Button queryBookBtn = (Button) findViewById(R.id.query_book_btn);
        queryBookBtn.setOnClickListener(v -> {
            Uri uri = Uri.withAppendedPath(BooksCommon.AUTHORITY_URI, BooksCommon.Table.BOOK);
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(BooksCommon.BooksColumns.NAME));
                    String author = cursor.getString(cursor.getColumnIndex(BooksCommon.BooksColumns.AUTHOR));
                    int pages = cursor.getInt(cursor.getColumnIndex(BooksCommon.BooksColumns.PAGES));
                    double price = cursor.getDouble(cursor.getColumnIndex(BooksCommon.BooksColumns.PRICE));

                    Logger.d(TAG, "name = " + name);
                    Logger.d(TAG, "author = " + author);
                    Logger.d(TAG, "pages = " + pages);
                    Logger.d(TAG, "price = " + price);
                }
            }
        });

        Button updateBookBtn = (Button) findViewById(R.id.update_book_btn);
        updateBookBtn.setOnClickListener(v -> {
            Uri uri = Uri.withAppendedPath(BooksCommon.AUTHORITY_URI, BooksCommon.Table.BOOK + "/" + newId);
            ContentValues values = new ContentValues();
            values.put(BooksCommon.BooksColumns.AUTHOR, "Tustar");
            values.put(BooksCommon.BooksColumns.PAGES, 395);
            values.put(BooksCommon.BooksColumns.PRICE, 89.00);
            getContentResolver().update(uri, values, null, null);
        });

        Button deleteBookBtn = (Button) findViewById(R.id.delete_book_btn);
        deleteBookBtn.setOnClickListener(v -> {
            Uri uri = Uri.withAppendedPath(BooksCommon.AUTHORITY_URI, BooksCommon.Table.BOOK + "/" + newId);
            getContentResolver().delete(uri, null, null);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
