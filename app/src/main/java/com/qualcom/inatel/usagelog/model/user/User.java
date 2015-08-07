package com.qualcom.inatel.usagelog.model.user;

/**
 * Created by fabianaff on 06/08/2015.
 */
public class User {
    public final static String TABLE = "User";

    public final static String KEY_ID = "id";
    public final static String KEY_USER = "user";
    public final static String KEY_PASS = "pass";

    private int id;
    private String user;
    private String pass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
