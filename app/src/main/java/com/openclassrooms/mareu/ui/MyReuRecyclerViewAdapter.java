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

import com.example.mareu.R;
import com.openclassrooms.mareu.events.DeleteReunionEvent;
import com.openclassrooms.mareu.model.Reunion;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyReuRecyclerViewAdapter extends RecyclerView.Adapter<MyReuRecyclerViewAdapter.ViewHolder> {

    private List<Reunion> mReunion ;
    Context context ;

    public MyReuRecyclerViewAdapter(List<Reunion> items, Context context) {
        mReunion = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH'h'mm", Locale.FRANCE);
        Reunion reunion = mReunion.get(position);
        holder.mReunionName.setText(reunion.getName());
        holder.mReunionAvatar.setColorFilter(context.getResources().getColor(reunion.getColor()));
        holder.mReunionDate.setText(" - " + sdf.format(reunion.getDate()) + " - ");
        holder.mReunionRoom.setText(reunion.getRoom());
        holder.mReunionEmails.setText(reunion.getEmail());
        holder.mDeleteButton.setOnClickListener(v -> EventBus.getDefault().post(new DeleteReunionEvent(reunion)));
        holder.itemView.setOnClickListener(view -> {
            Context ctx = holder.itemView.getContext();
            Intent intent = new Intent(ctx, DetailActivity.class);
            intent.putExtra("id" , reunion.getId());
            ctx.startActivity(intent);

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
        @BindView(R.id.item_list_date)
        public TextView mReunionDate;
        @BindView(R.id.item_list_room)
        public TextView mReunionRoom;
        @BindView(R.id.item_list_emails)
        public TextView mReunionEmails;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
    /** Update mReunion **/
    void setData(List<Reunion> list){
        this.mReunion = list;
        notifyDataSetChanged();
    }
}
