package com.qualcom.inatel.usagelog.DAO.log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qualcom.inatel.usagelog.DbHelper;
import com.qualcom.inatel.usagelog.model.log.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

/**
 * Created by fabianaff on 06/08/2015.
 */
public class LogDAO {

    private DbHelper dbHelper;

    public LogDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public long insert(Log log){
        long log_id;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Log.KEY_LOGTYPE, log.getLog_type());
        values.put(Log.KEY_DATE, new Date().toString());

        log_id = db.insert(Log.TABLE, null, values);
        db.close();
        return (log_id);
    }

    public void delete(long log_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Log.TABLE, Log.KEY_ID + "= ?", new String[]{String.valueOf(log_id)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getLogs(String logType){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Log.TABLE;

        ArrayList<HashMap<String, String>> all_logs = new ArrayList<HashMap<String, String>>();
        String[]params = null;

        if(logType!= null){
            query += Log.KEY_LOGTYPE + " = ?";
            params = new String[]{logType};
        }

        Cursor cursor = db.rawQuery(query, params);

        if(cursor.moveToFirst()) {
            do {
                HashMap<String, String> log = new HashMap<String, String>();
                log.put("id", cursor.getString(cursor.getColumnIndex(Log.KEY_ID)));
                log.put("log_type", cursor.getString(cursor.getColumnIndex(Log.KEY_LOGTYPE)));
                log.put("date", cursor.getString(cursor.getColumnIndex(Log.KEY_DATE)));
                all_logs.add(log);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return all_logs;
    }

    public Log getById(long log_id){
        Log log = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM "+ Log.TABLE + " where " + Log.KEY_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String []{String.valueOf(log_id)});

        if(cursor.moveToFirst()){
            log.setId(cursor.getInt(cursor.getColumnIndex(Log.KEY_ID)));
            log.setLog_type(cursor.getString(cursor.getColumnIndex(Log.KEY_LOGTYPE)));
            log.setDate(cursor.getString(cursor.getColumnIndex(Log.KEY_DATE)));
        }

        cursor.close();
        db.close();
        return log;
    }
}
