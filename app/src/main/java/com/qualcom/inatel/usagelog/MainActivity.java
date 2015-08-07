package com.qualcom.inatel.usagelog;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.qualcom.inatel.usagelog.view.user.add_user;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuItem item = (MenuItem)findViewById(R.id.menu_add_user);
        if(item != null) {
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(getApplicationContext(), "Mostra tela de cadastro", Toast.LENGTH_SHORT);
                    return true;
                }
            });
        }

        db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users(user VARCHAR, password varchar)");
        db.execSQL("INSERT INTO users VALUES('inatel', 'inatel')");
        Cursor c = db.rawQuery("SELECT * FROM users", null);
        if(c.getCount() == 0){
            Toast.makeText(getApplicationContext(),"Nenhum registro encontrado", Toast.LENGTH_SHORT);
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            buffer.append("User: "+ c.getString(0)+ "\n");
            buffer.append("pass: "+ c.getString(1)+ "\n");
        }
        Toast.makeText(getApplicationContext(),buffer.toString(), Toast.LENGTH_LONG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_add_user) {
            Intent it = new Intent(this, add_user.class);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
