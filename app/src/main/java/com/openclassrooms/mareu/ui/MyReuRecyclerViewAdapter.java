package com.openclassrooms.mareu.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareu.R;
import com.openclassrooms.mareu.model.Reunion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyReuRecyclerViewAdapter extends RecyclerView.Adapter<MyReuRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunion ;

    public MyReuRecyclerViewAdapter(List<Reunion> items) {
        mReunion = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reu, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Reunion reunion = mReunion.get(position);
        holder.mReunionName.setText(reunion.getName());
        Glide.with(holder.mReunionAvatar.getContext())
                .load(reunion.getColor())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mReunionAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context ctx = holder.itemView.getContext();
                Intent intent = new Intent(ctx, DetailActivity.class);
                intent.putExtra("id" , reunion.getId());
                ctx.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return mReunion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mReunionAvatar;
        @BindView(R.id.item_list_name)
        public TextView mReunionName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
