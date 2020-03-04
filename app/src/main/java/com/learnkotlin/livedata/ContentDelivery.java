package com.learnkotlin.livedata;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.learnkotlin.livedata.room.RoomDb;

public class ContentDelivery extends ContentProvider {

    static final String SINGLE_RECORD_MIME_TYPE =
            "vnd.android.cursor.item/vnd.com.example.provider.words";
    static final String MULTIPLE_RECORDS_MIME_TYPE =
            "vnd.android.cursor.item/vnd.com.example.provider.words";

    public static String TAG = "com.learnkotlin.livedata";
    public static String TODO = "todo";
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private RoomDb db;

    static {
        /*
         * The calls to addURI() go here, for all of the content URI patterns that the provider
         * should recognize. For this snippet, only the calls for table 3 are shown.
         */

        /*
         * Sets the integer value for multiple rows in table 3 to 1. Notice that no wildcard is used
         * in the path
         */
        uriMatcher.addURI(TAG, TODO, 1);

        /*
         * Sets the code for a single row to 2. In this case, the "#" wildcard is
         * used. "content://com.example.app.provider/table3/3" matches, but
         * "content://com.example.app.provider/table3 doesn't.
         */
        uriMatcher.addURI(TAG, TODO +"/#", 2);
    }

    @Override
    public boolean onCreate() {
        db = Room.databaseBuilder(getContext().getApplicationContext(), RoomDb.class, "RoomDb").build();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        switch (uriMatcher.match(uri)) {
            case 1:
                String id = "2";
                if(getContext() != null){
//                    long userId = ContentUris.parseId(uri);
                    return db.todoDao().todoCursor(Integer.parseInt(id));

                }
                break;
            case 2:

                 id = uri.getLastPathSegment();
                if(getContext() != null){
//                    long userId = ContentUris.parseId(uri);
                    return db.todoDao().todoCursor(Integer.parseInt(id));

                }
                break;
        }



        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.item/" + TAG + "." + TODO;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
