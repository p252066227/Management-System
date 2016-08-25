package edu.feicui.studentsonline.ui.findall;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.studentsonline.R;
import edu.feicui.studentsonline.adapter.RecyclerAdapter;
import edu.feicui.studentsonline.entity.Student;
import edu.feicui.studentsonline.ui.DBManager;
import edu.feicui.studentsonline.ui.SPManager;

/**
 * Created by Administrator on 2016/8/17.
 */
public class FindAllFragment extends Fragment {

    private View view;
    private RecyclerView find_all_recycler;
    private RecyclerAdapter recyclerAdapter;
    private List<Student> list;
    private DBManager dbManager;
    private SPManager spManager;

    private String name;
    private int stposition;
    private Button btn_find;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    recyclerAdapter.setRecycleTitleListener(new RecyclerAdapter.RecycleTitleListener() {
                        @Override
                        public void itemOnClick(View view, int position) {
                            name = list.get(position).getName();
                            Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void itemOnLongClick(View view, int position) {
                            name = list.get(position).getName();
                            stposition = spManager.getQuan();
                            if (stposition==2|stposition==4){
                                positivebutton(name,position);
                            }

                        }
                    });

//                    if (!dbManager.studentnull()){
                        list = dbManager.lookstudent();
//                    }else {
//                        list.add(new Student("张三","男","1"))；
//                    }
                    find_all_recycler.setAdapter(recyclerAdapter);
                    recyclerAdapter.setList(list);
                    recyclerAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.find_all_fragment, container, false);

        btn_find = (Button) view.findViewById(R.id.btn_find);

        find_all_recycler = (RecyclerView) view.findViewById(R.id.find_all_recycler);
        list = new ArrayList<Student>();
        recyclerAdapter = new RecyclerAdapter(getContext());
        dbManager = new DBManager(getContext());
        spManager = SPManager.getSpManager(getContext());

        list = dbManager.lookstudent();

        find_all_recycler.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        find_all_recycler.setItemAnimator(new DefaultItemAnimator());

        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = dbManager.lookstudent();
                handler.sendEmptyMessage(1);
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        handler.sendEmptyMessage(1);

    }


    private void positivebutton(final String stname, final int position) {
        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //    设置Title的图标
        builder.setIcon(R.drawable.ic_launcher);
        //    设置Title的内容
        builder.setTitle("删除");
        //    设置Content来显示一个信息
        builder.setMessage("确定删除" + stname + "吗？");
        //    设置一个PositiveButton
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recyclerAdapter.remove(position);

                dbManager.studelete(stname);
                if (!dbManager.studentuser(stname)) {
                    Toast.makeText(getContext(), "成功删除" + stname, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //这里是取消操作
            }
        });
        builder.show();

    }

}
