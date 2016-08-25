package edu.feicui.studentsonline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.feicui.studentsonline.R;
import edu.feicui.studentsonline.entity.Student;

/**
 * Created by Administrator on 2016/8/17.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private List<Student> list;
    Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,int position) {
        holder.tv_re_name.setText(list.get(position).getName());
        holder.tv_re_xingbie.setText(list.get(position).getXingbie());
        holder.tv_re_age.setText(list.get(position).getAge());


        if (recycleTitleListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=holder.getLayoutPosition();
                    recycleTitleListener.itemOnClick(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position=holder.getLayoutPosition();
                    recycleTitleListener.itemOnLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_re_name,tv_re_xingbie,tv_re_age;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_re_name= (TextView) itemView.findViewById(R.id.tv_re_name);
            tv_re_xingbie= (TextView) itemView.findViewById(R.id.tv_re_xingbie);
            tv_re_age= (TextView) itemView.findViewById(R.id.tv_re_age);
        }
    }

    public interface  RecycleTitleListener{
        void itemOnClick(View view,int position);
        void itemOnLongClick(View view,int position);
    }

    private RecycleTitleListener recycleTitleListener=null;

    public RecycleTitleListener getRecycleTitleListener() {
        return recycleTitleListener;
    }

    public void setRecycleTitleListener(RecycleTitleListener recycleTitleListener) {
        this.recycleTitleListener = recycleTitleListener;
    }

    public void add(int position, String name, String xingbie, String age) {
        list.add(new Student(name, xingbie, age));
        notifyItemInserted(position);
    }

    public void remove(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

}

