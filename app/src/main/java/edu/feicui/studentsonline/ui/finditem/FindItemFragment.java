package edu.feicui.studentsonline.ui.finditem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.studentsonline.R;
import edu.feicui.studentsonline.adapter.RecyclerAdapter;
import edu.feicui.studentsonline.entity.Student;
import edu.feicui.studentsonline.ui.DBManager;

/**
 * Created by Administrator on 2016/8/17.
 */
public class FindItemFragment extends Fragment{

    private View view;
    private List<Student> list;
    private EditText find_item_name;
    private Button btn_find_ok;
    private RecyclerView find_item_recycler;
    private RecyclerAdapter recyclerAdapter;
    private DBManager dbManager;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    recyclerAdapter.setList(list);
                    find_item_recycler.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();

                    recyclerAdapter.setRecycleTitleListener(new RecyclerAdapter.RecycleTitleListener() {
                        @Override
                        public void itemOnClick(View view, int position) {
                            find_item_name.setText(list.get(position).getName());
                        }

                        @Override
                        public void itemOnLongClick(View view, int position) {
                            Toast.makeText(getContext(), "Zz", Toast.LENGTH_SHORT).show();
                        }
                    });


                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.find_item_fragment,container,false);
        //所有控件的初始化
        initview(view);
        //对EditText设置改变监听
        find_item_name.addTextChangedListener(textListener);
        //RecyclerView 的布局设置
        recyclerviwe();

        btn_find_ok.setOnClickListener(listener);

        return view;
    }




    //    ********************************************************************
    private void initview(View view) {
        list = new ArrayList<>();
        find_item_name = (EditText) view.findViewById(R.id.find_item_name);
        find_item_recycler = (RecyclerView) view.findViewById(R.id.find_item_recycler);
        btn_find_ok = (Button) view.findViewById(R.id.btn_find_ok);
        recyclerAdapter = new RecyclerAdapter(getContext());
        dbManager = new DBManager(getContext());
    }

    private void recyclerviwe() {
        find_item_recycler.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        find_item_recycler.setItemAnimator(new DefaultItemAnimator());

    }


//    获取 EditText 改变的三个方法
    private TextWatcher textListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(charSequence.length()>0){//判断长度大于0，则转换
                String str=charSequence.toString();//转换输入框为String 类型
                list=dbManager.getName(str);//获取查询结果
//                if (list.size()>0){
//                    title = list.get(i).getTitle();
//                    url = list.get(i).getUrl();
//                    time = list.get(i).getCtime();
//                }
                handler.sendEmptyMessage(0);//发送
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = find_item_name.getText().toString().trim();
            if (dbManager.studentuser(name)) {
                String age = dbManager.getage(name);
                String xingbie = dbManager.getxingbie(name);
                positivebutton(name,age,xingbie);

            }else {
                Toast.makeText(getContext(), "没有该学生", Toast.LENGTH_SHORT).show();
            }

        }
    };



    //弹出框

    private void positivebutton(final String stname, final String stage, final String stxingbie) {
        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //    设置Title的图标
        builder.setIcon(R.drawable.ic_launcher);
        //    设置Title的内容
        builder.setTitle(stname);
        //    设置Content来显示一个信息
        builder.setMessage("年龄：" + stage + "  " + "性别：" + stxingbie);
        //    设置一个PositiveButton
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //    设置一个NegativeButton
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //这里是取消操作
//            }
//        });
        builder.show();

    }

}
