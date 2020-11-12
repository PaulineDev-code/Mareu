package com.openclassrooms.mareu.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Reunion;
import com.openclassrooms.mareu.service.ReunionApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReuActivity extends AppCompatActivity {

    @BindView(R.id.color)
    ImageView avatar;
    @BindView(R.id.nameLyt)
    TextInputLayout nameInput;
    @BindView(R.id.aboutItLyt)
    TextInputLayout aboutItInput;
    @BindView(R.id.emailLyt)
    TextInputLayout emailInput;
    @BindView(R.id.create)
    MaterialButton addButton;

    private ReunionApiService mApiService;
    private String mNeighbourImage;
    private DatePicker picker;
    private Calendar myCalendar;
    private TextView dateview;
    private Button addEmailButton;
    private Spinner spinner;
    private String[] roomsList= {"Mario", "Luigi", "Warrio", "Waluigi", "Boo", "DonkeyKong"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reu);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mApiService = DI.getReunionApiService();
        init();
        getDate();
        setAddEmailButton();
        initSpinner();
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

    private void initSpinner(){
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, roomsList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(new NothingSelectedSpinnerAdapter(
                arrayAdapter, R.layout.contact_spinner_row_nothing_selected, this
        ));
        spinner.setPrompt("Sélectionnez une salle");
    }

    private void getDate(){
        myCalendar = Calendar.getInstance();

        dateview = (TextView) findViewById(R.id.datePickerLyt);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        dateview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddReuActivity.this, R.style.DialogTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(){
        String myFormat = "dd/MM/YY";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        dateview.setText(sdf.format(myCalendar.getTime()));
    }
    private boolean emailValid(CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void setAddEmailButton(){
        addEmailButton = findViewById(R.id.add_email_button);
        addEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextInputEditText email = findViewById(R.id.email);
                final ChipGroup chipGroup = findViewById(R.id.chip_group);

                final Chip chip = new Chip(AddReuActivity.this);
                ChipDrawable drawable = ChipDrawable.createFromAttributes(AddReuActivity.this, null, 0, R.style.Widget_MaterialComponents_Chip_Entry);
                chip.setChipDrawable(drawable);
                chip.setCheckable(false);
                chip.setClickable(false);
                chip.setChipIconResource(R.drawable.ic_baseline_fingerprint_24);
                chip.setIconStartPadding(3f);
                chip.setPadding(60, 10, 60, 10);
                chip.setText(email.getText().toString());

                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chipGroup.removeView(chip);
                    }
                });
                if (!emailValid(email.getText().toString())){
                    Toast.makeText(AddReuActivity.this, "Rentrez un email valide", Toast.LENGTH_SHORT).show();
                    email.setText("");
                }
                else {
                    chipGroup.addView(chip);
                    email.setText("");
                }
            }
        });
    }

    @OnClick(R.id.create)
    void addReu() {
        Reunion reunion = new Reunion(
                System.currentTimeMillis(),
                nameInput.getEditText().getText().toString(),
                mNeighbourImage,
                new Date(),
                spinner.getSelectedItem().toString(),
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
