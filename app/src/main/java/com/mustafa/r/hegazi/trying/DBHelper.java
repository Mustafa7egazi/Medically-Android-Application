package com.mustafa.r.hegazi.trying;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "usersDB", null, 2);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table users(_id integer primary key autoincrement,username varchar(20) UNIQUE,email TEXT,password TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        onCreate(sqLiteDatabase);
    }
    public boolean checkUserExist(String username,String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username =? or email =?",new String[]{username,email});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkUsername(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username =?",new String[]{username});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkUserRegistered(String username,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where (username =? or email=?)and password =?",new String[]{username,username,password});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean registerUser(String username , String password , String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username.toLowerCase());
        values.put("password",password);
        values.put("email",email);
        long row = db.insert("users",null,values);
        if (row != -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean updatePassword(String password, String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username.toLowerCase());
        values.put("password",password);
        int cursor = db.update("users",values,"username =?",new String[]{String.valueOf(username)});
        db.close();
        if (cursor > 0)
        {
            return  true;
        }
        else
        {

            return false;
        }

    }


    public static class PatientDB extends SQLiteOpenHelper{
        public PatientDB(@Nullable Context context) {
            super(context, "patientsDB", null,2);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql2="create table patients(_id integer primary key autoincrement,name varchar(20)," +
                    "phone varchar(11), natId varchar(14),consultation TEXT,date varchar(12),gender varchar(10))";
            sqLiteDatabase.execSQL(sql2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists patients");
            onCreate(sqLiteDatabase);
        }

        //////////     Patient table deal    ///////////////////
        public boolean patientSearch(String natId)
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("Select * from patients where natId=?",new String[]{natId});
            if (c.getCount()>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public boolean insertPatient(String name , String phone , String natId, String consultation ,String date,String gender )
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",name);
            values.put("phone",phone);
            values.put("natId",natId);
            values.put("consultation",consultation);
            values.put("date",date);
            values.put("gender",gender);
            long row = db.insert("patients",null,values);
            db.close();
            if (row != -1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        // update patient

        public boolean updatePatient(String name , String phone,String natId , String consultation ,String date,String gender)
        {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",name);
            values.put("phone",phone);
            values.put("natId",natId);
            values.put("consultation",consultation);
            values.put("date",date);
            values.put("gender",gender);
            int cursor = db.update("patients",values,"natId=?",
                    new String[]{natId});
            db.close();
            if (cursor > 0)
            {
                return  true;
            }
            else
            {
                return false;
            }
        }

        // delete patient

        public boolean deletePatient(String natId)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            int cursor = db.delete("patients","natId=?",new String[]{natId});
            db.close();
            if (cursor > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        public ArrayList<custom_list> getAllData(){
            ArrayList<custom_list> arrayList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM patients",null);
            while (res.moveToNext()){
                String name = res.getString(1);
                String phone = res.getString(2);
                String natId = res.getString(3);
                String disease = res.getString(4);
                String disease_history = res.getString(5);
                String gender = res.getString(6);
                custom_list customList = new custom_list(natId,name,phone,disease,disease_history,gender);
                arrayList.add(customList);
            }
            return arrayList;
        }
    }

}
