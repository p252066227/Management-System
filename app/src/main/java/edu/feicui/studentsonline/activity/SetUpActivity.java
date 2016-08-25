package edu.feicui.studentsonline.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.feicui.studentsonline.R;
import edu.feicui.studentsonline.service.MusicService;
import edu.feicui.studentsonline.ui.SPManager;

public class SetUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_shez_topcolor,tv_shez_color,tv_color_lan,tv_color_hong
            ,tv_color_huang,tv_color_lv;
    private Button btn_shez_ok,btn_shez_zant,btn_shez_stop,btn_shez_true;
    private String color;
    private SPManager spManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        //所有控件的初始化
        initall();

        //获取上次存储的颜色
        color = spManager.getSzColor();
        tv_shez_topcolor.setBackgroundColor(Color.parseColor(color));
        tv_shez_color.setBackgroundColor(Color.parseColor(color));

        //所有控件的点击监听
        onClickall();

    }


    private void initall() {
        spManager = SPManager.getSpManager(this);

        tv_shez_topcolor = (TextView) findViewById(R.id.tv_shez_topcolor);
        tv_shez_color = (TextView) findViewById(R.id.tv_shez_color);

        tv_color_lan = (TextView) findViewById(R.id.tv_color_lan);
        tv_color_hong = (TextView) findViewById(R.id.tv_color_hong);
        tv_color_huang = (TextView) findViewById(R.id.tv_color_huang);
        tv_color_lv = (TextView) findViewById(R.id.tv_color_lv);

        btn_shez_ok = (Button) findViewById(R.id.btn_shez_ok);
        btn_shez_zant = (Button) findViewById(R.id.btn_shez_zant);
        btn_shez_stop = (Button) findViewById(R.id.btn_shez_stop);
        btn_shez_true = (Button) findViewById(R.id.btn_shez_true);
    }

    private void onClickall() {
        tv_color_lan.setOnClickListener(this);
        tv_color_hong.setOnClickListener(this);
        tv_color_huang.setOnClickListener(this);
        tv_color_lv.setOnClickListener(this);
        btn_shez_ok.setOnClickListener(this);
        btn_shez_zant.setOnClickListener(this);
        btn_shez_stop.setOnClickListener(this);
        btn_shez_true.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_color_lan:
                    color = "#0393cc";
                    setcolor(color);
                    break;
                case R.id.tv_color_hong:
                    color = "#ff0026";
                    setcolor(color);
                    break;
                case R.id.tv_color_huang:
                    color = "#0dff00";
                    setcolor(color);
                    break;
                case R.id.tv_color_lv:
                    color = "#f7ff02";
                    setcolor(color);
                    break;

                case R.id.btn_shez_ok:
                    Intent ok = new Intent(this, MusicService.class);
                    ok.putExtra("action","ok");
                    startService(ok);

                    break;
                case R.id.btn_shez_zant:
                    Intent zant = new Intent(this, MusicService.class);
                    zant.putExtra("action","zant");
                    startService(zant);

                    break;
                case R.id.btn_shez_stop:
                    Intent stop = new Intent(this, MusicService.class);
                    stop.putExtra("action","stop");
                    startService(stop);

                    break;

                case R.id.btn_shez_true:
                    Intent intent = new Intent();
                    intent.putExtra("color",color);
                    setResult(135,intent);

                    spManager.setSzColor(color);

                    finish();
                    break;
            }
        }

    private void setcolor(String colors) {
        tv_shez_topcolor.setBackgroundColor(Color.parseColor(colors));
        tv_shez_color.setBackgroundColor(Color.parseColor(colors));
    }

}
