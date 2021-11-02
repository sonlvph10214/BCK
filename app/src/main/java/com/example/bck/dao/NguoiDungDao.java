package com.example.bck.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.bck.model.NguoiDung;
import com.example.bck.sqlite.MySQLite;

import java.util.ArrayList;
import java.util.List;


public class NguoiDungDao {
    MySQLite mySQLite;
    SQLiteDatabase sqLiteDatabase;
    List<NguoiDung> nguoiDungList;

    public static final String TABLE_NAME = "NguoiDung";
    public static final String COL_FULLNAME = "fullName";
    public static final String COL_USER = "userName";
    public static final String COL_PASS = "pass";
    public static final String COL_EMAIL = "email";



    public NguoiDungDao(Context context){
        mySQLite = new MySQLite(context);
        this.sqLiteDatabase=mySQLite.getWritableDatabase();
    }

    public static final String SQL_NGUOIDUNG = "create table "
            + TABLE_NAME + " ("  + COL_USER +" text primary key,"
                                 + COL_FULLNAME +" text NOT NULL,"
                                 + COL_EMAIL +" text NOT NULL,"
                                 + COL_PASS +" text NOT NULL)";


    public long addNguoiDung(NguoiDung nguoiDung){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER,nguoiDung.userName);
        contentValues.put(COL_FULLNAME,nguoiDung.fullName);
        contentValues.put(COL_EMAIL,nguoiDung.email);
        contentValues.put(COL_PASS,nguoiDung.pass);
        return  sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

    }
    public int updatePass(NguoiDung nguoiDung){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER,nguoiDung.userName);
        contentValues.put(COL_FULLNAME,nguoiDung.fullName);
        contentValues.put(COL_EMAIL,nguoiDung.email);
        contentValues.put(COL_PASS,nguoiDung.pass);
        return sqLiteDatabase.update(TABLE_NAME,contentValues,COL_USER + "=?",new String[]{nguoiDung.getUserName()});
    }
    public int check(String tk,String mk){
        String check = "select * from " +TABLE_NAME +" where "+COL_PASS +" = "+ "'"+mk+"' and "+COL_USER+ " = "+" '"+tk+"'" ;
        SQLiteDatabase sqLiteDatabase = mySQLite.getReadableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery(check,null);
        if(cursor.getCount() > 0){
            return 1;
        }else{
            return -1;
        }

    }


    public List<NguoiDung> getData(String sql,String...selectionArgs){
        List<NguoiDung> list = new ArrayList<NguoiDung>();
         sql = "select * from " +TABLE_NAME +" where "+COL_USER+"=?" ;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.userName = cursor.getString(cursor.getColumnIndex(COL_USER));
            nguoiDung.fullName = cursor.getString(cursor.getColumnIndex(COL_FULLNAME));
            nguoiDung.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
            nguoiDung.pass = cursor.getString(cursor.getColumnIndex(COL_PASS));
            list.add(nguoiDung);
        }
        return list;
    }
    public NguoiDung getFullName(){

        NguoiDung nguoiDung = new NguoiDung();
        String name = "select * from " +TABLE_NAME +" where "+COL_FULLNAME+"=?" ;
        Cursor cursor = sqLiteDatabase.rawQuery(name, null);
        while (cursor.moveToNext()){

            nguoiDung.userName = cursor.getString(cursor.getColumnIndex(COL_USER));
            nguoiDung.fullName = cursor.getString(cursor.getColumnIndex(COL_FULLNAME));
            nguoiDung.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
            nguoiDung.pass = cursor.getString(cursor.getColumnIndex(COL_PASS));
            cursor.close();
        }
        return nguoiDung;
    }


    public NguoiDung getID(String id){
        String sql = "select * from " +TABLE_NAME +" where "+COL_USER+"=?" ;
        List<NguoiDung> list = getData(sql,id);
        return list.get(0);
    }



}
