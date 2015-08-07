package com.qualcom.inatel.usagelog.model.log;

/**
 * Created by fabianaff on 06/08/2015.
 */
public class Log {
    public final static String TABLE = "Log";

    public final static String KEY_ID = "id";
    public final static String KEY_LOGTYPE = "log_type";
    public final static String KEY_DATE = "DATE";

    private int id;
    private String log_type;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLog_type() {
        return log_type;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

