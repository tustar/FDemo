package com.tustar.fc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by tustar on 8/1/15.
 */
public class BookDbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "book.db";

    private Context mContext;

    public static final String CREATE_BOOK = "create table " + BooksCommon.Table.BOOK + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + BooksCommon.BooksColumns.AUTHOR + " text, "
            + BooksCommon.BooksColumns.PRICE + " real, "
            + BooksCommon.BooksColumns.PAGES + " integer, "
            + BooksCommon.BooksColumns.NAME + " text"
            + ")";

    public static final String CREATE_CATEGORY = "create table " + BooksCommon.Table.CATEGORY + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + BooksCommon.CategorysColumns.NAME + " text, "
            + BooksCommon.CategorysColumns.CODE + " integer"
            + ")";



    public BookDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public BookDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
