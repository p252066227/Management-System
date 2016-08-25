package edu.feicui.studentsonline.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.studentsonline.DB.DBOpenHelper;
import edu.feicui.studentsonline.entity.Student;

/**
 * Created by Administrator on 2016/8/17.
 */
public class DBManager  {

    private Context context;
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        this.context = context;
        dbOpenHelper = new DBOpenHelper(context);
        db = dbOpenHelper.getWritableDatabase();
    }


//        学生注册
    public void sonline(String user,String password){
        ContentValues cv = new ContentValues();
        cv.put("user",user);
        cv.put("password",password);
        db.insert("sonline",null,cv);
    }

    //    学生是否重复注册
    public boolean shas(String user){
        Cursor cursor = db.rawQuery("select * from sonline where user=?",new String[]{user});
        while (cursor.moveToNext()){
            String suser = cursor.getString(cursor.getColumnIndex("user"));
            if (user.equals(suser)){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

//    学生登陆
    public boolean sonhas(String user,String password ){

        Cursor cursor = db.rawQuery("select * from sonline where user=? and password=?", new String[]{user,password});
        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("user"));
            String password1 = cursor.getString(cursor.getColumnIndex("password"));

            if (user.equals(username) && password.equals(password1)){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }



//老师注册
    public void tonline(String user,String password){
        ContentValues cv = new ContentValues();
        cv.put("user",user);
        cv.put("password",password);
        db.insert("tonline",null,cv);
    }

//    老师是否重复注册
    public boolean thas(String user){
        Cursor cursor = db.rawQuery("select * from tonline where user=?",new String[]{user});
        while (cursor.moveToNext()){
            String suser = cursor.getString(cursor.getColumnIndex("user"));
            if (user.equals(suser)){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }



//    老师登陆
    public boolean tonhas(String user,String password ){

        Cursor cursor = db.rawQuery("select * from tonline where user=? and password=?", new String[]{user,password});
        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("user"));
            String password1 = cursor.getString(cursor.getColumnIndex("password"));

            if (user.equals(username) && password.equals(password1)){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }



    public void student(String name,String xingbie,String age){
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("xingbie",xingbie);
        cv.put("age",age);
        db.insert("student",null,cv);
    }

    //    所有列表是否有重复
    public boolean studentuser(String name){
        Cursor cursor = db.rawQuery("select * from student where name=?",new String[]{name});
        while (cursor.moveToNext()){
            String sname = cursor.getString(cursor.getColumnIndex("name"));
            if (name.equals(sname)){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    //    取出数据放到集合中
    public List<Student> lookstudent(){
        List<Student> list = new ArrayList<Student>();
        Cursor cursor = db.rawQuery("select * from student",null);
        while (cursor.moveToNext()){
            String sname = cursor.getString(cursor.getColumnIndex("name"));
            String sxingbie = cursor.getString(cursor.getColumnIndex("xingbie"));
            String sage = cursor.getString(cursor.getColumnIndex("age"));

            list.add(new Student(sname,sage,sxingbie));
        }
        return list;
    }

    //    所有列表是否为空
    public boolean studentnull(){
        Cursor cursor=db.rawQuery("select * from student", null);
        if(cursor.getCount()==0) {
            return true;
        }
        return false;
    }




    //删除所有列表中的某项
    public void studelete(String name){
        db.delete("student", "name=?", new String[]{name});
    }

    //模糊查找
    public List<Student> getName(String name){
        List<Student> list=new ArrayList<>();
        //select  * from 表名 where 列名 like ？ ,new String []{'%'+传递进来查询的值+'%'};注意用rawQuery方法只能用单引号
        Cursor cursor=db.rawQuery("select * from student where name like ?",new String[]{'%'+name+'%'});//Like代表通配符
        if(cursor!=null){
            while(cursor.moveToNext()){
                String stname = cursor.getString(cursor.getColumnIndex("name"));
                String stage = cursor.getString(cursor.getColumnIndex("age"));
                String stxingbie = cursor.getString(cursor.getColumnIndex("xingbie"));

                list.add(new Student(stname,stage,stxingbie));
            }
        }
        return list;
    }

    //查找到对应学员的年龄
    public String getage(String name){
        String stage = null;
        Cursor cursor=db.rawQuery("select * from student where name=?",new String[]{ name });
        if(cursor!=null){
            while(cursor.moveToNext()) {
                stage = cursor.getString(cursor.getColumnIndex("age"));

            }
        }
        return stage;
    }

    //查找到对应学员的性别
    public String getxingbie(String xingbie){
        String stxingbie = null;
        Cursor cursor=db.rawQuery("select * from student where name=?",new String[]{ xingbie });
        if(cursor!=null){
            while(cursor.moveToNext()) {
                stxingbie = cursor.getString(cursor.getColumnIndex("xingbie"));
            }
        }
        return stxingbie;
    }



}
