package com.example.posapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder>{

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<UserEntity> mUsers;

    public UserListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        if (mUsers != null) {
            UserEntity user = mUsers.get(position);
            holder.setData(user.getUserId(), user.getFirst_name(), user.getLast_name(), position);
        } else {
            // Covers the case of data not being ready
            holder.userItemView.setText(R.string.no_users);
        }
    }

    @Override
    public int getItemCount() {
        if (mUsers != null)
            return mUsers.size();
        else return 0;
    }

    public void setUsers(List<UserEntity> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView userItemView;
        private TextView userFirstNameView;
        private TextView userLastNameView;
        private int mPosition;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.tvxuserID);
            userFirstNameView = itemView.findViewById(R.id.tvxuserFirstName);
            userLastNameView = itemView.findViewById(R.id.tvxuserLastName);;
        }

        public void setData(String userId, String firstName, String lastName, int position) {
            userItemView.setText(userId);
            userFirstNameView.setText(firstName);
            userLastNameView.setText(lastName);
            mPosition = position;
        }
    }
}
