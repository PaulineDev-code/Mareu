package com.openclassrooms.mareu.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mareu.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Reunion;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.detail_aboutIt)
    TextView aboutIt;
    @BindView(R.id.detail_date)
    TextView date;
    @BindView(R.id.detail_room)
    TextView room;
    @BindView(R.id.detail_email)
    TextView email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        long id = getIntent().getLongExtra("id", -1);
        Reunion reunion = DI.getReunionApiService().getReunionbyid(id);
        imageView.setColorFilter(getResources().getColor(reunion.getColor()));



//        Glide.with(this).asBitmap().load(neighbour.getAvatarUrl()).into(new CustomTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                backGround.setBackground(new BitmapDrawable(getResources() , resource));
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//            }
//        } );
//
//        Glide.with(this).load(reunion.getColor()).into(image_view);


        name.setText(reunion.getName());
        name.setTextSize(25);
        name.setTextColor(0xFF000000);




        date.setText(reunion.getDate().toString());

        room.setText(reunion.getRoom());

        email.setText(reunion.getEmail());

        aboutIt.setText(reunion.getAboutIt());


    }

    @Override
    public boolean onSupportNavigateUp() {
        boolean b = super.onSupportNavigateUp();
        finish();
        return b;
    }
}
