package edu.feicui.studentsonline.ui.add;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import edu.feicui.studentsonline.R;
import edu.feicui.studentsonline.ui.DBManager;
import edu.feicui.studentsonline.ui.SPManager;

/**
 * Created by Administrator on 2016/8/17.
 */
public class AddFragment extends Fragment{

    private View view;
    private RelativeLayout rl_add_xingbie,rl_add_age;
    private EditText et_add_name,et_add_xingbie,et_add_age;
//    private TextView tv_add_xiugai;
    private Button btn_add_ok,btn_add_xiugai,btn_add_find;

    private String name,xingbie,age;
    private int xiugai;
    private DBManager dbManager;
    private SPManager spManager;

    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    et_add_name.setText("");
                    et_add_xingbie.setText("");
                    et_add_age.setText("");

                    btn_add_find.setVisibility(View.GONE);
                    btn_add_ok.setVisibility(View.VISIBLE);
                    btn_add_xiugai.setVisibility(View.VISIBLE);

                    rl_add_xingbie.setVisibility(View.VISIBLE);
                    rl_add_age.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_fragment, container, false);

        //所有控件的初始化
        initall(view);

        btn_add_find.setVisibility(View.GONE);

        //添加页面时候 控件的监听
        onclickadd();

        return view;
    }


    private void onclickadd() {
        et_add_name.setOnClickListener(alllistener);
        et_add_xingbie.setOnClickListener(alllistener);
        et_add_age.setOnClickListener(alllistener);
        btn_add_ok.setOnClickListener(alllistener);
        btn_add_xiugai.setOnClickListener(alllistener);
        btn_add_find.setOnClickListener(alllistener);
    }

    private void initall(View view) {
        rl_add_xingbie = (RelativeLayout) view.findViewById(R.id.rl_add_xingbie);
        rl_add_age = (RelativeLayout) view.findViewById(R.id.rl_add_age);

        et_add_name = (EditText) view.findViewById(R.id.et_add_name);
        et_add_xingbie = (EditText) view.findViewById(R.id.et_add_xingbie);
        et_add_age = (EditText) view.findViewById(R.id.et_add_age1);

        btn_add_ok = (Button) view.findViewById(R.id.btn_add_ok);
        btn_add_xiugai = (Button) view.findViewById(R.id.btn_add_xiugai);
        btn_add_find = (Button) view.findViewById(R.id.btn_add_find);

        dbManager = new DBManager(getContext());
        spManager = SPManager.getSpManager(getContext());
    }

    //添加 页面时候的点击事件
    View.OnClickListener alllistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_add_ok:

                    name = et_add_name.getText().toString().trim();
                    xingbie = et_add_xingbie.getText().toString().trim();
                    age = et_add_age.getText().toString().trim();

                    if (name!=null && xingbie!=null && age!=null){
                        if (!dbManager.studentuser(name)){
                            dbManager.student(name,xingbie,age);
                            Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "该学员已存在", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(), "Zz", Toast.LENGTH_SHORT).show();
                    }

                    break;

                case R.id.btn_add_xiugai:
                    et_add_name.setText("");
                    btn_add_ok.setVisibility(View.GONE);
                    rl_add_xingbie.setVisibility(View.GONE);
                    rl_add_age.setVisibility(View.GONE);
                    btn_add_xiugai.setVisibility(View.GONE);
                    btn_add_find.setVisibility(View.VISIBLE);

                    break;

                case R.id.btn_add_find:
                    name = et_add_name.getText().toString().trim();
                    if (dbManager.studentuser(name)){
                        xuanzebtn(name);
                    }else {
                        Toast.makeText(getContext(), "没有该成员", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void xuanzebtn(final String stuname) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("请输入名字/性别/年龄");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        view = LayoutInflater.from(getActivity()).inflate(R.layout.item_add_btn, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);

        final EditText name = (EditText)view.findViewById(R.id.et_name);
        final EditText username = (EditText)view.findViewById(R.id.et_user);
        final EditText password = (EditText)view.findViewById(R.id.et_password);

        name.setText(stuname);
        username.setText(dbManager.getxingbie(stuname));
        password.setText(dbManager.getage(stuname));

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String a = name.getText().toString().trim();
                String b = username.getText().toString().trim();
                String c = password.getText().toString().trim();
                dbManager.studelete(stuname);
                dbManager.student(a,b,c);
                Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();

                handler.sendEmptyMessage(0);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}
