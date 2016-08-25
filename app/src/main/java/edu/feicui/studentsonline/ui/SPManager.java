package edu.feicui.studentsonline.ui;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/8/17.
 */
public class SPManager {

    public static SPManager spManager;
    private Context context;

    private SPManager(Context context) {
        this.context = context;
    }

    public static SPManager getSpManager(Context context){
        if (spManager ==null){
            spManager = new SPManager(context);
        }
        return spManager;
    }


    //    第一次登陆
    public String isFirst(){
//        取
        SharedPreferences sp1 = context.getSharedPreferences("isFirst", context.MODE_PRIVATE);
        String isfirst = sp1.getString("first", "true");

//        存
        SharedPreferences sp = context.getSharedPreferences("isFirst",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("first", "false");
        editor.commit();
        return isfirst;
    }

    public void setFirst(String first){
        SharedPreferences sp = context.getSharedPreferences("isFirst",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("first", first);
        editor.commit();
    }


//    粗存权限
    public void setQuan(int position){
        SharedPreferences sp = context.getSharedPreferences("isUser",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("isposition", position);
        editor.commit();
    }

    public int getQuan(){
        SharedPreferences sp1 = context.getSharedPreferences("isUser", context.MODE_PRIVATE);
        int isposition = sp1.getInt("isposition", -1);

        return isposition;
    }


//    存储账号
    public void setuser(String user){
        SharedPreferences sp = context.getSharedPreferences("isUser",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("user", user);
        editor.commit();
    }

    public String getuser(){
        SharedPreferences sp1 = context.getSharedPreferences("isUser", context.MODE_PRIVATE);
        String isposition = sp1.getString("user", "这货没有账号");

        return isposition;
    }



    public void setxiugai(int position){
        SharedPreferences sp = context.getSharedPreferences("isXiugai",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("xiugai", position);
        editor.commit();
    }

    public int getxiugai(){
        SharedPreferences sp1 = context.getSharedPreferences("isXiugai", context.MODE_PRIVATE);
        int isposition = sp1.getInt("xiugai", -1);

        return isposition;
    }



    //存储颜色
    public void setSzColor(String color){
        SharedPreferences sp = context.getSharedPreferences("Color",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("color", color);
        editor.commit();
    }

    //获取颜色
    public String getSzColor(){
        SharedPreferences sp = context.getSharedPreferences("Color", context.MODE_PRIVATE);
        String color = sp.getString("color", "#0393cc");

        return color;
    }




}
