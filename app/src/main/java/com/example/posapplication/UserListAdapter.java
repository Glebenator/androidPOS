package com.example.posapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder>{

    public interface OnDeleteClickListener {
        void OnDeleteClickListener(UserEntity myUser);
    }

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<UserEntity> mUsers;
    private OnDeleteClickListener onDeleteClickListener;

    public UserListAdapter(Context context, OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.onDeleteClickListener = listener;
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
            holder.setData(user.getUserId(), user.getFirst_name(), user.getLast_name(), user.getRole(), position);
            holder.setListeners();
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

        private TextView userItemView, userFirstNameView, userLastNameView, userRoleView;
        private ImageView imgDelete;
        private int mPosition;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.tvxuserID);
            userFirstNameView = itemView.findViewById(R.id.tvxuserFirstName);
            userLastNameView = itemView.findViewById(R.id.tvxuserLastName);
            userRoleView = itemView.findViewById(R.id.tvxuserRole);
            imgDelete = itemView.findViewById(R.id.ivRowDelete);
        }

        public void setData(String userId, String firstName, String lastName, String userRole, int position) {
            userItemView.setText(userId);
            userFirstNameView.setText(firstName);
            userLastNameView.setText(lastName);
            userRoleView.setText(userRole);
            mPosition = position;
        }

        public void setListeners() {
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(mUsers.get(mPosition));
                    }
                }
            });
        }
    }
}
