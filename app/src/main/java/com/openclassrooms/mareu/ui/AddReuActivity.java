package com.openclassrooms.mareu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Reunion;
import com.openclassrooms.mareu.service.ReunionApiService;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReuActivity extends AppCompatActivity {

    @BindView(R.id.color)
    ImageView avatar;
    @BindView(R.id.nameLyt)
    TextInputLayout nameInput;
    @BindView(R.id.lieuLyt)
    TextInputLayout lieuInput;
    @BindView(R.id.datePicker1)
    TextInputLayout heureInput;
    @BindView(R.id.aboutItLyt)
    TextInputLayout aboutItInput;
    @BindView(R.id.emailLyt)
    TextInputLayout emailInput;
    @BindView(R.id.create)
    MaterialButton addButton;

    private ReunionApiService mApiService;
    private String mNeighbourImage;
    private DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reu);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mApiService = DI.getReunionApiService();
        picker=(DatePicker)findViewById(R.id.datePicker1);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mNeighbourImage = randomImage();
        Glide.with(this).load(mNeighbourImage).placeholder(R.drawable.ic_baseline_add_white_24dp)
                .apply(RequestOptions.circleCropTransform()).into(avatar);
        nameInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                addButton.setEnabled(s.length() > 0);
            }
        });

    }

    private void getDate(){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
    }

    @OnClick(R.id.create)
    void addReu() {
        Reunion reunion = new Reunion(
                System.currentTimeMillis(),
                nameInput.getEditText().getText().toString(),
                mNeighbourImage,
                new Date(),
                lieuInput.getEditText().getText().toString(),
                emailInput.getEditText().getText().toString(),
                aboutItInput.getEditText().getText().toString()
        );
        mApiService.createReunion(reunion);
        finish();
    }

    String randomImage() {
        return "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
    }

    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, AddReuActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
        //navigation component
    }

}
