package com.mustafa.r.hegazi.trying;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "usersDB", null, 4);

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

    public boolean checkEmail(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email =?",new String[]{email});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkPassword(String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select username from users where password =?",new String[]{password});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean checkUserRegistered(String username,String email,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where (username =? or email=?)and password =?",
                new String[]{username,email,password});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor getUsername(String password,String email)

    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select username from users where password = ? AND email=?",new String[]{password,email});
    }
    public Cursor getEmail(String password,String username)
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select email from users where password = ? AND username=?",new String[]{password,username});
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
    public int deleteUser(String registeredUser)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("users","(username=? or email=?)",new String[]{ActionTakeActivity.registeringUserIs});
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
            super(context, "patientsDB", null,5);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql2="create table patients(_id integer primary key autoincrement,name varchar(20)," +
                    "phone varchar(11), natId varchar(14),consultation TEXT,date varchar(12)," +
                    "gender varchar(10),registeringUser varchar(50))";
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

        public boolean insertPatient(String name , String phone , String natId, String consultation
                ,String date,String gender)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",name);
            values.put("phone",phone);
            values.put("natId",natId);
            values.put("consultation",consultation);
            values.put("date",date);
            values.put("gender",gender);
            values.put("registeringUser",ActionTakeActivity.registeringUserIs);
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
        public void deleteAllPatients(String registeringUser)
        {
            SQLiteDatabase db = this.getWritableDatabase();
             db.delete("patients","registeringUser=?",new String[]{ActionTakeActivity.registeringUserIs});
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

            Cursor res = db.rawQuery("SELECT * FROM patients where registeringUser=?",
                    new String[]{ActionTakeActivity.registeringUserIs});
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
