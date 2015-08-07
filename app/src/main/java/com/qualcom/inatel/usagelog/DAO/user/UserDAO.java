package com.qualcom.inatel.usagelog.DAO.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import com.qualcom.inatel.usagelog.DbHelper;
import com.qualcom.inatel.usagelog.model.user.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by fabianaff on 06/08/2015.
 */
public class UserDAO {
    private DbHelper dbHelper;

    public UserDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public long insert(User user){
        long user_id;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_USER, user.getUser());
        values.put(User.KEY_PASS, user.getPass());

        user_id = db.insert(User.TABLE, null, values);
        db.close();
        return (user_id);
    }

    public void delete(long user_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(User.TABLE, User.KEY_ID + "= ?", new String[]{String.valueOf(user_id)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getUserList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + User.TABLE;

        ArrayList<HashMap<String, String>> all_users = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(cursor.getColumnIndex(User.KEY_ID)));
                user.put("user", cursor.getString(cursor.getColumnIndex(User.KEY_USER)));
                user.put("pass", cursor.getString(cursor.getColumnIndex(User.KEY_PASS)));
                all_users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return all_users;
    }

    public User getById(long user_id){
        User user = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM "+ User.TABLE + " where " + User.KEY_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String []{String.valueOf(user_id)});

        if(cursor.moveToFirst()){
            user.setId(cursor.getInt(cursor.getColumnIndex(User.KEY_ID)));
            user.setUser(cursor.getString(cursor.getColumnIndex(User.KEY_USER)));
            user.setPass(cursor.getString(cursor.getColumnIndex(User.KEY_PASS)));
        }

        cursor.close();
        db.close();
        return user;
    }
}
