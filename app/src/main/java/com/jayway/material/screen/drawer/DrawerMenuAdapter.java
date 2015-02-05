package com.jayway.material.screen.drawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayway.material.help.AbstractListAdapter;
import com.jayway.material.R;

import java.util.ArrayList;
import java.util.List;

public class DrawerMenuAdapter extends AbstractListAdapter<DrawerMenuAdapter.DrawerMenuItem, DrawerMenuAdapter.DrawerMenuViewHolder> {

    private LayoutInflater mInflater;
    private DrawerMenuListener mDrawerMenuListener;

    public DrawerMenuAdapter(Context context) {
        List<DrawerMenuItem> items = new ArrayList<>();
        items.add(new DrawerMenuItem(android.R.drawable.ic_menu_agenda, "Agenda"));
        items.add(new DrawerMenuItem(android.R.drawable.ic_menu_call, "Call"));
        items.add(new DrawerMenuItem(android.R.drawable.ic_menu_camera, "Camera"));

        setData(items);

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public DrawerMenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new DrawerMenuViewHolder(
                mInflater.inflate(R.layout.drawer_menu_item, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(DrawerMenuViewHolder drawerMenuViewHolder, int position) {
        DrawerMenuItem item = mData.get(position);
        drawerMenuViewHolder.bind(position, item);
    }

    public void setDrawerMenuListener( DrawerMenuListener listener) {
        mDrawerMenuListener = listener;
    }

    public static interface DrawerMenuListener {
        public abstract void onMenuItemClicked(int position, DrawerMenuItem item);
    }

    public static class DrawerMenuItem {
        private final int mIconResourceId;
        private final String mLabel;

        public DrawerMenuItem(int iconResourceId, String label) {
            mIconResourceId = iconResourceId;
            mLabel = label;
        }

        public int getIconResourceId() {
            return mIconResourceId;
        }

        public String getLabel() {
            return mLabel;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DrawerMenuItem that = (DrawerMenuItem) o;

            if (mIconResourceId != that.mIconResourceId) return false;
            if (mLabel != null ? !mLabel.equals(that.mLabel) : that.mLabel != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = mIconResourceId;
            result = 31 * result + (mLabel != null ? mLabel.hashCode() : 0);
            return result;
        }
    }

    public class DrawerMenuViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mIcon;
        private final TextView  mLabel;

        private int mPosition;
        private DrawerMenuItem mMenuItem;

        public DrawerMenuViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.icon);
            mLabel = (TextView) itemView.findViewById(R.id.label);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDrawerMenuListener != null) {
                        mDrawerMenuListener.onMenuItemClicked(mPosition, mMenuItem);
                    }
                }
            });
        }

        public void bind(int position, DrawerMenuItem item) {
            mPosition = position;
            mMenuItem = item;
            mIcon.setImageResource(item.getIconResourceId());
            mLabel.setText(item.getLabel());
        }
    }
}
