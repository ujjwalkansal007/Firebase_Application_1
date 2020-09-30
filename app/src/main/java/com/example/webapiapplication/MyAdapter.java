package com.example.webapiapplication;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    String text[];
    Integer pics[];
    Activity activity;

    MyAdapter(String text[],Integer pics[],Activity activity){
        this.text=text;
        this.pics=pics;
        this.activity=activity;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mylayout = activity.getLayoutInflater().inflate(R.layout.customview,parent,false);

        MyViewHolder holder = new MyViewHolder(mylayout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(text[position]);
        holder.iv.setImageResource(pics[position]);
    }

    @Override
    public int getItemCount() {
        return text.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        ImageView iv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.textView);
            iv=itemView.findViewById(R.id.imageView);


        }
    }



}
