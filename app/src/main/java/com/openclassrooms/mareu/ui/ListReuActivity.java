package com.openclassrooms.mareu.ui;


import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.mareu.R;

public class ListReuActivity extends AppCompatActivity {

    /**
     * UI Components
     */
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
//    @BindView(R.id.container)
//    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reu);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.add_reu)
    void addReu() {
        AddReuActivity.navigate(this);

    }
}
