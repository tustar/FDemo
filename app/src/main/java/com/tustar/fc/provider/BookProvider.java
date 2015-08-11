package com.tustar.fc.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.tustar.fc.db.BookDbHelper;
import com.tustar.fc.db.BooksCommon;
import com.tustar.fc.utils.Logger;

/**
 * Created by tustar on 8/1/15.
 */
public class BookProvider extends ContentProvider {

    private static final String TAG = BookProvider.class.getSimpleName();

    // Case
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;

    // BooksCommon.AUTHORITY
    public static final String CONTENT_PREFIX = "content://";

    // UriMatcher
    private static UriMatcher uriMatcher;

    // Db helper
    private BookDbHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(BooksCommon.AUTHORITY, BooksCommon.Table.BOOK, BOOK_DIR);
        uriMatcher.addURI(BooksCommon.AUTHORITY, BooksCommon.Table.BOOK + "/#", BOOK_ITEM);
        uriMatcher.addURI(BooksCommon.AUTHORITY, BooksCommon.Table.CATEGORY, CATEGORY_DIR);
        uriMatcher.addURI(BooksCommon.AUTHORITY, BooksCommon.Table.CATEGORY + "/#", CATEGORY_ITEM);
    }


    @Override
    public boolean onCreate() {
        Logger.i(TAG, "onCreate ::");
        dbHelper = new BookDbHelper(getContext().getApplicationContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query(BooksCommon.Table.BOOK, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query(BooksCommon.Table.BOOK, projection, BaseColumns._ID + " = ?",
                        new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query(BooksCommon.Table.CATEGORY, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query(BooksCommon.Table.CATEGORY, projection, BaseColumns._ID + " = ?",
                        new String[]{categoryId},   null, null, sortOrder);
                break;
            default:
                break;
        }

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        String type = "";
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                type = BooksCommon.UriType.BOOK_DIR;
                break;
            case BOOK_ITEM:
                type = BooksCommon.UriType.BOOK_ITEM;
                break;
            case CATEGORY_DIR:
                type = BooksCommon.UriType.CATEGORY_DIR;
                break;
            case CATEGORY_ITEM:
                type = BooksCommon.UriType.CATEGORY_ITEM;
                break;
            default:
                break;
        }
        return type;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri returnUri = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert(BooksCommon.Table.BOOK, null, values);
                returnUri = Uri.parse(CONTENT_PREFIX + BooksCommon.AUTHORITY + "/"
                        + BooksCommon.Table.BOOK + "/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert(BooksCommon.Table.CATEGORY, null, values);
                returnUri = Uri.parse(CONTENT_PREFIX + BooksCommon.AUTHORITY + "/"
                        + BooksCommon.Table.CATEGORY + "/" + newCategoryId);
                break;
            default:
                break;
        }
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                rows = db.delete(BooksCommon.Table.BOOK, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                rows = db.delete(BooksCommon.Table.BOOK, BaseColumns._ID + " = ?",
                        new String[]{bookId});
                break;
            case CATEGORY_DIR:
                rows = db.delete(BooksCommon.Table.CATEGORY, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                rows = db.delete(BooksCommon.Table.CATEGORY, BaseColumns._ID + " = ?",
                        new String[]{categoryId});
                break;
            default:
                break;
        }
        return rows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                rows = db.update(BooksCommon.Table.BOOK, values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                rows = db.update(BooksCommon.Table.BOOK, values, BaseColumns._ID + " = ?",
                        new String[]{bookId});
                break;
            case CATEGORY_DIR:
                rows = db.update(BooksCommon.Table.CATEGORY, values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                rows = db.update(BooksCommon.Table.CATEGORY, values, BaseColumns._ID + " = ?",
                        new String[]{categoryId});
                break;
            default:
                break;
        }
        return rows;
    }
}
