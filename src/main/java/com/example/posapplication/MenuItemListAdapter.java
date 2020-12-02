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

public class MenuItemListAdapter extends RecyclerView.Adapter<MenuItemListAdapter.MenuItemViewHolder>{

    public interface OnDeleteClickListener {
        void OnDeleteClickListener(MenuItemEntity menuItemEntity);
    }

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<MenuItemEntity> mMenuItemEntities;
    private OnDeleteClickListener onDeleteClickListener;

    public MenuItemListAdapter(Context context, OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_menu_item, parent, false);
        MenuItemViewHolder viewHolder = new MenuItemViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {

        if (mMenuItemEntities != null) {
            MenuItemEntity menuItemEntity = mMenuItemEntities.get(position);
            holder.setData(menuItemEntity.getName(), menuItemEntity.getPrice(), position);
            holder.setListeners();
        } else {
            // Covers the case of data not being ready
            holder.MenuItemItemView.setText(R.string.no_users);
        }
    }

    @Override
    public int getItemCount() {
        if (mMenuItemEntities != null)
            return mMenuItemEntities.size();
        else return 0;
    }

    public void setMenuItems(List<MenuItemEntity> menuItemEntity) {
        mMenuItemEntities = menuItemEntity;
        notifyDataSetChanged();
    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder {

        private TextView MenuItemItemView, menuItemPrice;
        private ImageView imgDelete;
        private int mPosition;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            MenuItemItemView = itemView.findViewById(R.id.tvxItemName);
            menuItemPrice = itemView.findViewById(R.id.tvxItemPrice);
            imgDelete = itemView.findViewById(R.id.ivRowDelete);
        }

        public void setData(String itemName, double price, int position) {
            MenuItemItemView.setText(itemName);
            menuItemPrice.setText(String.valueOf(price));
            mPosition = position;
        }

        public void setListeners() {
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(mMenuItemEntities.get(mPosition));
                    }
                }
            });
        }
    }
}
