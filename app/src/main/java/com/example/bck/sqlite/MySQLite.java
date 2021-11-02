package com.example.bck.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.bck.dao.NguoiDungDao;


public class MySQLite extends SQLiteOpenHelper {
    public static final String SQL = "BAICUOIKHOA.db";
    public static final int VERSION = 1;


    public MySQLite(Context context) {
        super(context, SQL, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDao.SQL_NGUOIDUNG);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
