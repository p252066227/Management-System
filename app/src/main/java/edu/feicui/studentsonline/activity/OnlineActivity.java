package edu.feicui.studentsonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import edu.feicui.studentsonline.R;
import edu.feicui.studentsonline.ui.DBManager;
import edu.feicui.studentsonline.ui.SPManager;

public class OnlineActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout online_layout,zhuce_layout;
    private TextView tv_online,tv_zhuce,tv_ok,tv_quxiao;
    private EditText et_on_zhanghao,et_on_mima,et_zc_zhanghao,et_zc_mima;
    private RadioButton cb_on_student,cb_on_teacher,cb_zc_student,cb_zc_teacher;
    private RadioGroup rg_on_zhiye,rg_zc_zhiye;

    private DBManager dbManager;

    private int position = -1;
    private String user;
    private String password;
    private SPManager spManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        online_layout = (LinearLayout) findViewById(R.id.online_layout);
        zhuce_layout = (LinearLayout) findViewById(R.id.zhuce_layout);
        zhuce_layout.setVisibility(View.GONE);
        online_layout.setVisibility(View.VISIBLE);

        dbManager = new DBManager(this);
        spManager = SPManager.getSpManager(this);

        if (!spManager.isFirst().equals("true")){
            Intent intent =new Intent(OnlineActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }


        tv_online = (TextView) findViewById(R.id.tv_online);
        tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_quxiao = (TextView) findViewById(R.id.tv_quxiao);

        cb_on_student = (RadioButton) findViewById(R.id.cb_on_student);
        cb_on_teacher = (RadioButton) findViewById(R.id.cb_on_teacher);
        cb_zc_student = (RadioButton) findViewById(R.id.cb_zc_student);
        cb_zc_teacher = (RadioButton) findViewById(R.id.cb_zc_teacher);

        rg_on_zhiye = (RadioGroup) findViewById(R.id.rg_on_zhiye);
        rg_zc_zhiye = (RadioGroup) findViewById(R.id.rg_zc_zhiye);

        et_on_zhanghao = (EditText) findViewById(R.id.et_on_zhanghao);
        et_on_mima = (EditText) findViewById(R.id.et_on_mima);
        et_zc_zhanghao = (EditText) findViewById(R.id.et_zc_zhanghao);
        et_zc_mima = (EditText) findViewById(R.id.et_zc_mima);

        tv_online.setOnClickListener(this);
        tv_zhuce.setOnClickListener(this);
        tv_ok.setOnClickListener(this);
        tv_quxiao.setOnClickListener(this);

        rg_on_zhiye.setOnCheckedChangeListener(listener);
        rg_zc_zhiye.setOnCheckedChangeListener(listener);

//        online_layout.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_online:
                user = et_on_zhanghao.getText().toString().trim();
                password = et_on_mima.getText().toString().trim();
//                注册时候，账号密码不能为空
                if (user.equals("")||password.equals("")){
                    Toast.makeText(OnlineActivity.this, "请输入账号密码", Toast.LENGTH_SHORT).show();
                }else {
//                  学生登陆
                    if (position==1||position==3){
                        if (dbManager.sonhas(user,password)){
                            Intent intent =new Intent(OnlineActivity.this,MainActivity.class);
                            spManager.setuser(user);
                            spManager.setQuan(position);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(OnlineActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
//              老师登陆
                    else if (position==2||position==4){
                        if (dbManager.tonhas(user,password)){
                            Intent intent =new Intent(OnlineActivity.this,MainActivity.class);
                            spManager.setuser(user);
                            spManager.setQuan(position);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(OnlineActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(OnlineActivity.this, "请选择权限", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.tv_zhuce:
                zhuce_layout.setVisibility(View.VISIBLE);
                online_layout.setVisibility(View.GONE);
                break;
            case R.id.tv_ok:
                user = et_zc_zhanghao.getText().toString().trim();
                password = et_zc_mima.getText().toString().trim();
//                学生注册
                if (position==3) {
                    if (!dbManager.shas(user)) {
                        dbManager.sonline(user, password);
                        Toast.makeText(OnlineActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        zhuce_layout.setVisibility(View.GONE);
                        online_layout.setVisibility(View.VISIBLE);
                    }else {
                        Toast.makeText(OnlineActivity.this, "账号重复", Toast.LENGTH_SHORT).show();
                    }
                }
//                老师注册
                else if (position == 4){
                    if (!dbManager.thas(user)){
                        dbManager.tonline(user, password);
                        Toast.makeText(OnlineActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        zhuce_layout.setVisibility(View.GONE);
                        online_layout.setVisibility(View.VISIBLE);
                    }else {
                        Toast.makeText(OnlineActivity.this, "账号重复", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(OnlineActivity.this, "请选择权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_quxiao:
                zhuce_layout.setVisibility(View.GONE);
                online_layout.setVisibility(View.VISIBLE);
                break;
        }
    }


    RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == cb_on_student.getId()){
                position=1;
            }else if (i == cb_on_teacher.getId()){
                position=2;
            }else if (i == cb_zc_student.getId()){
                position=3;
            }else if (i == cb_zc_teacher.getId()){
                position=4;
            }
        }
    };
}
