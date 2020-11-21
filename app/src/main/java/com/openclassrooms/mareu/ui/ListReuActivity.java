package com.openclassrooms.mareu.ui;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import com.example.mareu.R;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.events.DeleteReunionEvent;
import com.openclassrooms.mareu.model.Reunion;

import java.util.List;

public class ListReuActivity extends AppCompatActivity {
    private List<Reunion> mReunion;

    /**
     * UI Components
     */
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
//    @BindView(R.id.container)

    MyReuRecyclerViewAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reu);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initRecyclerView();
    }

    @OnClick(R.id.add_reu)
    void addReu() {
        AddReuActivity.navigate(this);

    }
    private void initRecyclerView(){
        mReunion = DI.getReunionApiService().getReunion();
        this.mAdapter = new MyReuRecyclerViewAdapter(mReunion);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void dateFilter(){

    }


    private void roomFilter(){

        switch(onMenuItemSelected())


    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mRecyclerView.getAdapter()!= null)
        this.mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        DI.getReunionApiService().deleteReunion(event.reunion);
        initRecyclerView();
    }
}
