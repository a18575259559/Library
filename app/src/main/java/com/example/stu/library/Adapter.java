package com.example.stu.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.*;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Adapter {
    SQLiteDatabase db=null;
    Context context=null;

    public Adapter(Context context){this.context=context;}

    public void openDB(){
        Myhelper myhelper=new Myhelper(context,"datavase.db",null,1);
        db=myhelper.getWritableDatabase();
    }
    public void closeDB(){
        if (db.isOpen()){
            db.close();
        }
        db=null;
    }
    public boolean insert(Infor infor){
        boolean result =false;
        openDB();
        ContentValues values=new ContentValues();
        values.put("number",infor.getNumber());
        values.put("name",infor.getName());
        values.put("zuozhe",infor.getZuozhe());
        values.put("chubanshe",infor.getChubanshe());

        Long rowid=db.insert("information",null,values);
        if (rowid!=-1){
            result=true;
            Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show();
            Log.i("数据库操作","添加成功");
        }else {
            Toast.makeText(context,"添加失败",Toast.LENGTH_SHORT).show();
            Log.i("数据库操作","添加失败");
        }
        closeDB();
        return result;
    }

    public List<Infor> queryAll(String tiaojian){
        List<Infor>list=new ArrayList<Infor>();
        openDB();
        //Cursor cursor=db.query("information",new String[]{"number","name","zuozhe","chubanshe"},"number = ? or name = ? or zuozhe = ? or chubanshe = ?",new String[]{tiaojian},null, null,null);
            Cursor cursor=db.query("information",null,null,null,null, null,null);
            if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    long number=cursor.getLong(cursor.getColumnIndex("number"));
                    String name=cursor.getString(cursor.getColumnIndex("name"));
                    String zuozhe=cursor.getString(cursor.getColumnIndex("zuozhe"));
                    String chubanshe=cursor.getString(cursor.getColumnIndex("chubanshe"));
                    Infor infor=new Infor();
                    infor.setNumber(number);
                    infor.setName(name);
                    infor.setZuozhe(zuozhe);
                    infor.setChubanshe(chubanshe);
                    list.add(infor);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        closeDB();
        return list;
    }

    public int delete(String name){
        int result=-1;
        openDB();
        result=db.delete("information","name=? or zuozhe=?",new String[]{name});
        closeDB();
        return result;
    }


}
