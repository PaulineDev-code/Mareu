package com.openclassrooms.mareu.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.mareu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Reunion;
import com.openclassrooms.mareu.service.ReunionApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReuActivity extends AppCompatActivity {

    @BindView(R.id.color)
    ImageView avatar;
    @BindView(R.id.nameLyt)
    TextInputLayout nameInput;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.datePickerLyt)
    TextView dateView;
    @BindView(R.id.timePickerLyt)
    TextView timeView;
    @BindView(R.id.aboutItLyt)
    TextInputLayout aboutItInput;
    @BindView(R.id.emailLyt)
    TextInputLayout emailInput;
    @BindView(R.id.add_email_button)
    Button addEmailButton;
    @BindView(R.id.chip_group)
    ChipGroup chipGroup;
    @BindView(R.id.email)
    TextInputEditText email;
    @BindView(R.id.create)
    MaterialButton addButton;

    private ReunionApiService mApiService;
    private Calendar myCalendar = Calendar.getInstance();
    private int color;
    private String[] roomsList = {"Mario", "Luigi", "Wario", "Waluigi", "Boo", "DonkeyKong"};
    private ArrayList<String> emails = new ArrayList<>();



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
        getTime();
        setAddEmailButton();
        initSpinner();
    }



    /** Button return home **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        nameInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                addButton.setEnabled(s.length() > 0);
            }
        });

    }


    private void initSpinner() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(spinner.getSelectedItemPosition()){

                    case 1:
                        color = R.color.colorRed;
                        break;
                    case 2:
                        color = R.color.colorGreen;
                        break;
                    case 3:
                        color = R.color.colorYellow;
                        break;
                    case 4:
                        color = R.color.colorPurple;
                        break;
                    case 5 :
                        color = R.color.colorGreyWhite;
                        break;
                    case 6:
                        color = R.color.colorBrown;
                        break;

                    default:
                        color = R.color.colorTransparent;
                        break;

                }
                avatar.setColorFilter(getResources().getColor(color));

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

    private boolean isSpinnerEmpty(Spinner spinner) {
        return spinner.getSelectedItemPosition() == 0;
    }

    private void getDate() {

        DatePickerDialog.OnDateSetListener date = (datePicker, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelDate();
        };

        dateView.setOnClickListener(view -> {
            DatePickerDialog myDatePicker = new DatePickerDialog(AddReuActivity.this, R.style.DialogTheme, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            myDatePicker.show();

        });
    }

    private void updateLabelDate() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        dateView.setText(sdf.format(myCalendar.getTime()));
    }

    private void getTime() {

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hour);
                myCalendar.set(Calendar.MINUTE, minute);
                updateLabelTime();

            }
        };

        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(AddReuActivity.this, R.style.DialogTheme, time,
                        myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),
                        false).show();
            }
        });

    }

    private void updateLabelTime() {
        String myFormat = "HH 'h' mm";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        timeView.setText(sdf.format(myCalendar.getTime()));

    }



    private boolean emailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void isEmailValid(Chip chip){
        if (!emailValid(email.getText().toString())) {
            Toast.makeText(AddReuActivity.this, "Rentrez un email valide", Toast.LENGTH_SHORT).show();
        } else {
            chipGroup.addView(chip);
            emails.add(email.getText().toString());
        }
        email.setText("");
    }

    private void setAddEmailButton() {

        addEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Chip chip = new Chip(AddReuActivity.this);
                ChipDrawable drawable = ChipDrawable.createFromAttributes(AddReuActivity.this, null,
                        0, R.style.Widget_MaterialComponents_Chip_Entry);
                chip.setChipDrawable(drawable);
                chip.setCheckable(false);
                chip.setClickable(false);
                chip.setChipIconResource(R.drawable.ic_baseline_email_24);
                chip.setIconStartPadding(3f);
                chip.setPadding(60, 10, 60, 10);
                chip.setText(email.getText().toString());
                chip.setOnCloseIconClickListener(view1 -> chipGroup.removeView(chip));

                isEmailValid(chip);
            }
        });
    }

    @OnClick(R.id.create)
    void addReu() {
        if (!(isSpinnerEmpty(spinner))) {
            Reunion reunion = new Reunion(
                    System.currentTimeMillis(),
                    color,
                    nameInput.getEditText().getText().toString(),
                    myCalendar.getTime(),
                    spinner.getSelectedItem().toString(),
                    emails.toString(),
                    aboutItInput.getEditText().getText().toString()


            );
            int isRoomEmpty = spinner.getSelectedItem().toString().length();
            int isAboutItEmpty = aboutItInput.getEditText().getText().toString().length();
            int isDateEmpty = dateView.getText().toString().length();
            int isTimeEmpty = timeView.getText().toString().length();
            final ChipGroup chipGroup = findViewById(R.id.chip_group);

            if (!(isSpinnerEmpty(spinner)) && isAboutItEmpty != 0 && isDateEmpty != 0 && isTimeEmpty != 0 &&
                    !(isEmailEmpty(chipGroup))) {
                mApiService.createReunion(reunion);
                finish();
            } else {
                Toast.makeText(AddReuActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(AddReuActivity.this, "Sélectionnez une salle!", Toast.LENGTH_LONG).show();
        }

    }

    private boolean isEmailEmpty(ChipGroup chipGroup) {
        int hasChild = chipGroup.getChildCount();
        if (hasChild != 0) {
            return false;
        } else {
            return true;
        }
    }

    // Allow horizontal screen for this activity
    @Override
    protected void onResume() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        super.onResume();
    }


    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, AddReuActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
        //navigation component
    }

}
