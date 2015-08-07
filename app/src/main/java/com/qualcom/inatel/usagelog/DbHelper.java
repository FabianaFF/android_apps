package com.qualcom.inatel.usagelog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.qualcom.inatel.usagelog.model.log.Log;
import com.qualcom.inatel.usagelog.model.user.User;

/**
 * Created by fabianaff on 06/08/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UsageLog.db";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_create_table_user = "CREATE TABLE " + User.TABLE + " (" +
                User.KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" +
                User.KEY_USER + " TEXT" +
                User.KEY_PASS + " TEXT);";

        String sql_create_table_log = "CREATE TABLE " + Log.TABLE + " (" +
                Log.KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" +
                Log.KEY_LOGTYPE + " TEXT" +
                Log.KEY_DATE + " DATE);";

        db.execSQL(sql_create_table_user);
        db.execSQL(sql_create_table_log);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Log.TABLE);

        onCreate(db);
    }
}
