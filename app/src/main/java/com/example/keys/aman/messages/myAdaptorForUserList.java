package com.example.keys.aman.messages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keys.R;

import java.util.ArrayList;

public class myAdaptorForUserList extends RecyclerView.Adapter<myAdaptorForUserList.myViewHolder> {

    ArrayList<UserListModelClass> dataHolder;
    Context context;
    Activity activity;
    public myAdaptorForUserList() {
    }
    public myAdaptorForUserList(ArrayList<UserListModelClass> dataHolder, Context context, Activity activity) {
        this.dataHolder = dataHolder;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_layout,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.tv_public_uid.setText(dataHolder.get(position).getPublicUname());

        holder.tv_public_uid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ChatActivity.class);
                intent.putExtra("receiver_public_uid", dataHolder.get(position).getPublicUid());
                intent.putExtra("receiver_public_uname", dataHolder.get(position).getPublicUname());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }



    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView tv_public_uid;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_public_uid = itemView.findViewById(R.id.tv_public_uid);
        }
    }
}
