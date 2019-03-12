package com.example.chatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chatapp.MessageActivity;
import com.example.chatapp.R;


import com.example.chatapp.Model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private static final String TAG = UserAdapter.class.getSimpleName();
    private Context mContext;
    private List<User> mUsers;
    private boolean ischat;

    public UserAdapter(Context mContext, List<User> mUsers, boolean ischat){
        this.mUsers=mUsers;
        this.mContext=mContext;
        this.ischat = ischat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false );

        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final User user = mUsers.get(position);
        holder.username.setText(user.getUsername());
        // this is equal, but looks correctly?????????????????????????????????????????????????????????????????????????????????????????????????
        if(user.getImageUrl().equals("default")){
            Log.d(TAG, "onBindViewHolder: "+user.getImageUrl());
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(mContext).load(user.getImageUrl()).into(holder.profile_image);
        }

        if(ischat){
            if(user.getStatus().equals("online")){
                holder.img_on.setVisibility(View.VISIBLE);
                holder.img_off.setVisibility(View.GONE);
            }else{
                holder.img_off.setVisibility(View.VISIBLE);
                holder.img_on.setVisibility(View.GONE);
            }
        }else{
            holder.img_off.setVisibility(View.GONE);
            holder.img_on.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {



        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public ImageView profile_image;
        private ImageView img_on;
        private ImageView img_off;

        public ViewHolder(View itemView){
            super(itemView);

            username=itemView.findViewById(R.id.username);
            profile_image=itemView.findViewById(R.id.profile_image);
            img_on=itemView.findViewById(R.id.img_on);
            img_off=itemView.findViewById(R.id.img_off);
        }

    }


}
