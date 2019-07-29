package com.example.jihwa.project_sw;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by YM on 2018-11-19.
 */

public class DBManager extends SQLiteOpenHelper{
    private Context context;
    public DBManager(Context context) {
        super(context, "COLAPP", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RATE( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, lecture INTEGER, quiz INTEGER);");
        db.execSQL("INSERT INTO RATE values(null, 'CoL', 0, 0);");
        db.execSQL("INSERT INTO RATE values(null, 'atomicTask', 0, 0);");
        db.execSQL("INSERT INTO RATE values(null, 'choose', 0, 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "DB 버전이 올라갔습니다.",Toast.LENGTH_SHORT).show();
    }

    public int select_Lec(String input){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM RATE WHERE name='"+input+"';", null);
        if(cursor.moveToFirst()) {
            String result = cursor.getString(2);
            return Integer.parseInt(result);
        }
        else
            return 0;
    }
}
