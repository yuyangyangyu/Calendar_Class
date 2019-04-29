package com.example.calendar_class.B_G;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calendar_class.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<Class_detail> mlist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView;
        TextView textView1;
        TextView textView2;
        public ViewHolder(View view){
            super(view);
            cardView =(CardView)view;
            textView=view.findViewById(R.id.name);
            textView1=view.findViewById(R.id.place_tearcher);
            textView2=view.findViewById(R.id.Time);
        }
    }
    public MyAdapter(List<Class_detail> list){
        this.mlist=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context=viewGroup.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.tem,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Class_detail detail =mlist.get(i);
        viewHolder.textView.setText(detail.getName());
        viewHolder.textView1.setText(detail.getPlace_Teacher());
        viewHolder.textView2.setText(detail.getTime());
    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
