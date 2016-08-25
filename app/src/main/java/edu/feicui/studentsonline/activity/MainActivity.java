package edu.feicui.studentsonline.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.studentsonline.R;
import edu.feicui.studentsonline.ui.SPManager;
import edu.feicui.studentsonline.ui.add.AddFragment;
import edu.feicui.studentsonline.ui.bendi.BenDiFragment;
import edu.feicui.studentsonline.ui.findall.FindAllFragment;
import edu.feicui.studentsonline.ui.finditem.FindItemFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,Toolbar.OnMenuItemClickListener{

    private Toolbar main_toolbar;
    private DrawerLayout main_drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private LinearLayout main_drawer_lLayout;

    private AddFragment addFragment;
    private FindAllFragment findAllFragment;
    private FindItemFragment findItemFragment;
    private BenDiFragment benDiFragment;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private List<Fragment> list;

//    抽屉控件
    private TextView tv_main_name,tv_main_quanxian,tv_main_findall
        ,tv_main_add,tv_main_rem,tv_main_xiugai,tv_main_find
        ,tv_main_online,tv_main_bendi,tv_main_shez;

    private SPManager spManager;
    private String user;
    private int position,xiugai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_drawer_lLayout = (LinearLayout) findViewById(R.id.main_drawer_lLayout);

        //初始化 TB 相关控件
        initTB();
        main_toolbar.setTitle("传说中的师生");
        main_toolbar.inflateMenu(R.menu.toolbar_menu);
        setSupportActionBar(main_toolbar);
        main_toolbar.setOnMenuItemClickListener(this);

        //初始化fragment 及相关控件
        initFragment();

        //初始化抽屉里面的 控件
        initItem();
        onClickItem();

        //取出sp里面的position
        position = spManager.getQuan();
        user = spManager.getuser();

        //判断position的值  来显示权限和功能
        if(position==1|position==3){
            tv_main_name.setText(user);
            tv_main_quanxian.setText("学生");
            tv_main_add.setVisibility(View.GONE);
            tv_main_rem.setVisibility(View.GONE);
            tv_main_xiugai.setVisibility(View.GONE);
            tv_main_find.setVisibility(View.GONE);

        }else if (position ==2|position==4){
            tv_main_name.setText(user);
            tv_main_quanxian.setText("老师");
            tv_main_add.setVisibility(View.VISIBLE);
            tv_main_rem.setVisibility(View.VISIBLE);
            tv_main_xiugai.setVisibility(View.VISIBLE);
            tv_main_find.setVisibility(View.VISIBLE);
        }

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_drawer_lLayout, addFragment)
                .add(R.id.main_drawer_lLayout, findAllFragment)
                .add(R.id.main_drawer_lLayout, findItemFragment)
                .add(R.id.main_drawer_lLayout, benDiFragment);

        fragmentTransaction.hide(addFragment).hide(findAllFragment).hide(findItemFragment).hide(benDiFragment);
        fragmentTransaction.show(findAllFragment);
        fragmentTransaction.commit();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,main_drawerLayout,main_toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        main_drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return  true;
    }


    private void onClickItem() {
//        tv_main_name.setOnClickListener(this);
//        tv_main_quanxian.setOnClickListener(this);
        tv_main_findall.setOnClickListener(this);
        tv_main_add.setOnClickListener(this);
        tv_main_xiugai.setOnClickListener(this);
        tv_main_rem.setOnClickListener(this);
        tv_main_find.setOnClickListener(this);

        tv_main_online.setOnClickListener(this);
        tv_main_bendi.setOnClickListener(this);
        tv_main_shez.setOnClickListener(this);
    }

    private void initItem() {
        tv_main_name = (TextView) findViewById(R.id.tv_main_name);
        tv_main_quanxian = (TextView) findViewById(R.id.tv_main_quanxian);
        tv_main_findall = (TextView) findViewById(R.id.tv_main_findall);
        tv_main_add = (TextView) findViewById(R.id.tv_main_add);
        tv_main_xiugai = (TextView) findViewById(R.id.tv_main_xiugai);
        tv_main_rem = (TextView) findViewById(R.id.tv_main_rem);
        tv_main_find = (TextView) findViewById(R.id.tv_main_find);
        tv_main_online = (TextView) findViewById(R.id.tv_main_online);
        tv_main_bendi = (TextView) findViewById(R.id.tv_main_bendi);
        tv_main_shez = (TextView) findViewById(R.id.tv_main_shez);

        spManager = SPManager.getSpManager(this);

    }

    private void initFragment() {
        addFragment = new AddFragment();
        findAllFragment = new FindAllFragment();
        findItemFragment = new FindItemFragment();
        benDiFragment = new BenDiFragment();
        fragmentManager = getSupportFragmentManager();
        list = new ArrayList<>();

    }

    private void initTB() {
        main_toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        main_drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
    }

    @Override
    public void onClick(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (view.getId()){
            case R.id.tv_main_findall:
                tv_main_findall.setTextColor(Color.parseColor("#ff3c26"));
                tv_main_add.setTextColor(Color.parseColor("#FF000000"));
                tv_main_xiugai.setTextColor(Color.parseColor("#FF000000"));
                tv_main_rem.setTextColor(Color.parseColor("#FF000000"));
                tv_main_find.setTextColor(Color.parseColor("#FF000000"));
                tv_main_online.setTextColor(Color.parseColor("#FF000000"));
                tv_main_bendi.setTextColor(Color.parseColor("#FF000000"));
                tv_main_shez.setTextColor(Color.parseColor("#FF000000"));

                fragmentTransaction.hide(addFragment)
                        .hide(findAllFragment)
                        .hide(findItemFragment)
                        .hide(benDiFragment);
                fragmentTransaction.show(findAllFragment);
//                fragmentTransaction.replace(R.id.main_drawer_lLayout,findAllFragment);
                break;
            case R.id.tv_main_add:
                tv_main_findall.setTextColor(Color.parseColor("#FF000000"));
                tv_main_add.setTextColor(Color.parseColor("#ff3c26"));
                tv_main_xiugai.setTextColor(Color.parseColor("#FF000000"));
                tv_main_rem.setTextColor(Color.parseColor("#FF000000"));
                tv_main_find.setTextColor(Color.parseColor("#FF000000"));
                tv_main_online.setTextColor(Color.parseColor("#FF000000"));
                tv_main_bendi.setTextColor(Color.parseColor("#FF000000"));
                tv_main_shez.setTextColor(Color.parseColor("#FF000000"));

                fragmentTransaction.hide(addFragment)
                        .hide(findAllFragment)
                        .hide(findItemFragment)
                        .hide(benDiFragment);
                fragmentTransaction.show(addFragment);
//                fragmentTransaction.replace(R.id.main_drawer_lLayout,addFragment);
                break;
            case R.id.tv_main_xiugai:
                tv_main_findall.setTextColor(Color.parseColor("#FF000000"));
                tv_main_add.setTextColor(Color.parseColor("#FF000000"));
                tv_main_xiugai.setTextColor(Color.parseColor("#ff3c26"));
                tv_main_rem.setTextColor(Color.parseColor("#FF000000"));
                tv_main_find.setTextColor(Color.parseColor("#FF000000"));
                tv_main_online.setTextColor(Color.parseColor("#FF000000"));
                tv_main_bendi.setTextColor(Color.parseColor("#FF000000"));
                tv_main_shez.setTextColor(Color.parseColor("#FF000000"));

                fragmentTransaction.hide(addFragment)
                        .hide(findAllFragment)
                        .hide(findItemFragment)
                        .hide(benDiFragment);
                fragmentTransaction.show(addFragment);
                xiugai=1;
                spManager.setxiugai(xiugai);
//                fragmentTransaction.replace(R.id.main_drawer_lLayout,addFragment);
                break;
            case R.id.tv_main_rem:
                tv_main_findall.setTextColor(Color.parseColor("#FF000000"));
                tv_main_add.setTextColor(Color.parseColor("#FF000000"));
                tv_main_xiugai.setTextColor(Color.parseColor("#FF000000"));
                tv_main_rem.setTextColor(Color.parseColor("#ff3c26"));
                tv_main_find.setTextColor(Color.parseColor("#FF000000"));
                tv_main_online.setTextColor(Color.parseColor("#FF000000"));
                tv_main_bendi.setTextColor(Color.parseColor("#FF000000"));
                tv_main_shez.setTextColor(Color.parseColor("#FF000000"));

                fragmentTransaction.hide(addFragment)
                        .hide(findAllFragment)
                        .hide(findItemFragment)
                        .hide(benDiFragment);
                fragmentTransaction.show(findAllFragment);
                Toast.makeText(MainActivity.this, "长按即可删除", Toast.LENGTH_SHORT).show();
//                fragmentTransaction.replace(R.id.main_drawer_lLayout,findAllFragment);
                break;
            case R.id.tv_main_find:
                tv_main_findall.setTextColor(Color.parseColor("#FF000000"));
                tv_main_add.setTextColor(Color.parseColor("#FF000000"));
                tv_main_xiugai.setTextColor(Color.parseColor("#FF000000"));
                tv_main_rem.setTextColor(Color.parseColor("#FF000000"));
                tv_main_find.setTextColor(Color.parseColor("#ff3c26"));
                tv_main_online.setTextColor(Color.parseColor("#FF000000"));
                tv_main_bendi.setTextColor(Color.parseColor("#FF000000"));
                tv_main_shez.setTextColor(Color.parseColor("#FF000000"));

                fragmentTransaction.hide(addFragment)
                        .hide(findAllFragment)
                        .hide(findItemFragment)
                        .hide(benDiFragment);
                fragmentTransaction.show(findItemFragment);
//                fragmentTransaction.replace(R.id.main_drawer_lLayout,addFragment);
                break;

            case R.id.tv_main_online:
                tv_main_findall.setTextColor(Color.parseColor("#FF000000"));
                tv_main_add.setTextColor(Color.parseColor("#FF000000"));
                tv_main_xiugai.setTextColor(Color.parseColor("#FF000000"));
                tv_main_rem.setTextColor(Color.parseColor("#FF000000"));
                tv_main_find.setTextColor(Color.parseColor("#FF000000"));
                tv_main_online.setTextColor(Color.parseColor("#ff3c26"));
                tv_main_bendi.setTextColor(Color.parseColor("#FF000000"));
                tv_main_shez.setTextColor(Color.parseColor("#FF000000"));

                Toast.makeText(MainActivity.this, "这里要显示在线视频", Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_main_bendi:
                tv_main_findall.setTextColor(Color.parseColor("#FF000000"));
                tv_main_add.setTextColor(Color.parseColor("#FF000000"));
                tv_main_xiugai.setTextColor(Color.parseColor("#FF000000"));
                tv_main_rem.setTextColor(Color.parseColor("#FF000000"));
                tv_main_find.setTextColor(Color.parseColor("#FF000000"));
                tv_main_online.setTextColor(Color.parseColor("#FF000000"));
                tv_main_bendi.setTextColor(Color.parseColor("#ff3c26"));
                tv_main_shez.setTextColor(Color.parseColor("#FF000000"));

                fragmentTransaction.hide(addFragment)
                        .hide(findAllFragment)
                        .hide(findItemFragment)
                        .hide(benDiFragment);
                fragmentTransaction.show(benDiFragment);

                break;
            case R.id.tv_main_shez:
                tv_main_findall.setTextColor(Color.parseColor("#FF000000"));
                tv_main_add.setTextColor(Color.parseColor("#FF000000"));
                tv_main_xiugai.setTextColor(Color.parseColor("#FF000000"));
                tv_main_rem.setTextColor(Color.parseColor("#FF000000"));
                tv_main_find.setTextColor(Color.parseColor("#FF000000"));
                tv_main_online.setTextColor(Color.parseColor("#FF000000"));
                tv_main_bendi.setTextColor(Color.parseColor("#FF000000"));
                tv_main_shez.setTextColor(Color.parseColor("#ff3c26"));

                Toast.makeText(MainActivity.this, "这里是设置页面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,SetUpActivity.class);
                startActivityForResult(intent,135);
                break;
        }
        fragmentTransaction.commit();
    }

//    带返回值跳转
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            String color =  data.getStringExtra("color");
            main_toolbar.setBackgroundColor(Color.parseColor(color));

        }

    }



//    TB右边三个点 的点击监听
    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_share1:
                Intent intent = new Intent(MainActivity.this,OnlineActivity.class);
                spManager.setFirst("true");
                spManager.setQuan(-1);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_share2:
                finish();
                break;
        }

        return true;
    }



}
