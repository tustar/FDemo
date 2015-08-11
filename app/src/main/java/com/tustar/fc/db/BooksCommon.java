package com.tustar.fc.db;

import android.net.Uri;

/**
 * Created by tustar on 8/1/15.
 */
public interface BooksCommon {

    public static final String AUTHORITY = "com.tustar.fc.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);


    public interface UriType {
        public static final String VND_DIR_PREFIX = "vnd.android.cursor.dir/vnd";
        public static final String VND_ITEM_PREFIX = "vnd.android.cursor.item/vnd";
        public static final String BOOK_DIR = VND_DIR_PREFIX + "." + BooksCommon.AUTHORITY + "."
                + BooksCommon.Table.BOOK;
        public static final String BOOK_ITEM = VND_ITEM_PREFIX + "." + BooksCommon.AUTHORITY + "."
                + BooksCommon.Table.BOOK;
        public static final String CATEGORY_DIR = VND_DIR_PREFIX + "." + BooksCommon.AUTHORITY + "."
                + BooksCommon.Table.CATEGORY;
        public static final String CATEGORY_ITEM = VND_ITEM_PREFIX + "." + BooksCommon.AUTHORITY + "."
                + BooksCommon.Table.CATEGORY;
    }



    public interface Table {
        public static final String BOOK = "books";
        public static final String CATEGORY = "categories";
    }

    public interface BooksColumns {
        public static final String AUTHOR = "author";
        public static final String PRICE = "price";
        public static final String PAGES = "pages";
        public static final String NAME = "name";
    }

    public interface CategorysColumns {
        public static final String NAME = "name";
        public static final String CODE = "code";
    }
}
