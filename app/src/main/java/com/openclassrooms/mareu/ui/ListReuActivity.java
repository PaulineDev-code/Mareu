package com.openclassrooms.mareu.ui;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

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
import com.openclassrooms.mareu.service.ReunionApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class ListReuActivity extends AppCompatActivity {


    private static final String TAG = "cycle";
    /**
     * UI Components
     */
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.recycler_empty)
    TextView viewEmpty;

    MyReuRecyclerViewAdapter mAdapter;
    ReunionApiService mApiService = DI.getReunionApiService();
    private List<Reunion> mReunion = new ArrayList<>();
    private Date dateForFilter = new Date();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reu);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initRecyclerView();
        showEmptyView();
    }

    @OnClick(R.id.add_reu)
    void addReu() {
        AddReuActivity.navigate(this);

    }
    private void initRecyclerView(){
        mReunion = mApiService.getReunion();
        this.mAdapter = new MyReuRecyclerViewAdapter(mReunion, this);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showEmptyView(){
        if(mAdapter.getItemCount() == 0){
            viewEmpty.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
        else{
            viewEmpty.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void getDatePicker() {
        Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            mReunion = mApiService.dateFilter(myCalendar.getTime());
            mAdapter.setData(mReunion);
            showEmptyView();
        };

            DatePickerDialog myDatePicker = new DatePickerDialog(ListReuActivity.this, R.style.DialogTheme, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            myDatePicker.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())  {
            case R.id.date_filter:
                getDatePicker();
                break;
            case R.id.filter_room_1:
                mReunion = mApiService.filterRoom( "Mario");
                mAdapter.setData(mReunion);
                showEmptyView();
                break;
            case  R.id.filter_room_2:
                mReunion = mApiService.filterRoom("Luigi");
                mAdapter.setData(mReunion);
                showEmptyView();
                break;
            case  R.id.filter_room_3:
                mReunion = mApiService.filterRoom("Wario");
                mAdapter.setData(mReunion);
                showEmptyView();
                break;
            case  R.id.filter_room_4:
                mReunion = mApiService.filterRoom("Waluigi");
                mAdapter.setData(mReunion);
                showEmptyView();
                break;
            case  R.id.filter_room_5:
                mReunion = mApiService.filterRoom("Boo");
                mAdapter.setData(mReunion);
                showEmptyView();
                break;
            case  R.id.filter_room_6:
                mReunion = mApiService.filterRoom("DonkeyKong");
                mAdapter.setData(mReunion);
                showEmptyView();
                break;
            case R.id.filter_none:
                mReunion = mApiService.getReunion();
                mAdapter.setData(mReunion);
                showEmptyView();
                break;

            default:

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d(TAG, "start log");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mRecyclerView.getAdapter()!= null)
        this.mRecyclerView.getAdapter().notifyDataSetChanged();
        showEmptyView();
        Log.d(TAG, "resume log");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(TAG, "restart log");
    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Log.d(TAG, "stop log");
    }

    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        mApiService.deleteReunion(event.reunion);
        initRecyclerView();
    }
}
