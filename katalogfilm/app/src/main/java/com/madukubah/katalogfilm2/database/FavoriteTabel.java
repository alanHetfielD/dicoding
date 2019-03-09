package com.madukubah.katalogfilm2.database;

import android.provider.BaseColumns;

public class FavoriteTabel implements BaseColumns {
    public static String TABLE_NAME = "favorite_movie";

    public static String COLUMN_ID = "_id";
    public static String COLUMN_TITLE = "title";
    public static String COLUMN_BACKDROP = "backdrop";
    public static String COLUMN_POSTER = "poster";
    public static String COLUMN_RELEASE_DATE = "release_date";
    public static String COLUMN_VOTE = "vote";
    public static String COLUMN_OVERVIEW = "overview";

    public static String CREATE_TABLE_FAVORITE =
            "create table " +
                    TABLE_NAME + " (" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_TITLE + " text not null, " +
                    COLUMN_BACKDROP + " text not null, " +
                    COLUMN_POSTER + " text not null, " +
                    COLUMN_RELEASE_DATE + " text not null, " +
                    COLUMN_VOTE + " text not null, " +
                    COLUMN_OVERVIEW + " text not null " +
                    ");";
}