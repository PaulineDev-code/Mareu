package com.openclassrooms.mareu.ui;


import android.os.Bundle;
import android.widget.Adapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.mareu.R;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Reunion;

import java.util.List;

public class ListReuActivity extends AppCompatActivity {
    private List<Reunion> mReunion;

    /**
     * UI Components
     */
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
//    @BindView(R.id.container)
    MyReuRecyclerViewAdapter mAdapter;
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reu);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    @OnClick(R.id.add_reu)
    void addReu() {
        AddReuActivity.navigate(this);

    }
    private void initRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerview);
        mReunion = DI.getReunionApiService().getReunion();
        this.mAdapter = new MyReuRecyclerViewAdapter(mReunion);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
