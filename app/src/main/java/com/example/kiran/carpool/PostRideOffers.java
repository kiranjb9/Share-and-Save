package com.example.kiran.carpool;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.Calendar;

public class PostRideOffers extends AppCompatActivity {
    Spinner dropdown;
    Button selectDate,btn;
    EditText date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    EditText editText;
    //TIME PICKER
    EditText chooseTime;
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;

    RadioButton radio2,radio1;
    //Dynamic EDIT TEXT
    private LinearLayout lnrDynamicEditTextHolder;
    private EditText edtNoCreate;
    private Button btnCreate;

    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private EditText editTextSource, editTextDestination;
    private String TAG = "CreateRide";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ride_offers);

    //Auto complete


            // Handle editTextSource Click Handler
            editTextSource = (EditText)findViewById(R.id.editText_Source);

            editTextSource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(PostRideOffers.this);

                        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                    } catch (GooglePlayServicesRepairableException e) {
                        // TODO: Handle the error.
                    } catch (GooglePlayServicesNotAvailableException e) {

                    }
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    System.out.println("1111111111111111111111111111111111111111111111111"+place.getName());
                    editTextSource.setText(place.getName());

                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(this, data);
                    // TODO: Handle the error.
                    Log.i(TAG, status.getStatusMessage());

                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                }
            }

            editTextDestination = (EditText)findViewById(R.id.editText_Destination);
            editTextDestination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(PostRideOffers.this);
                        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                    } catch (GooglePlayServicesRepairableException e) {
                        // TODO: Handle the error.
                    } catch (GooglePlayServicesNotAvailableException e) {

                    }
                }
            });



        dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"0","1", "2", "3","4","5"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);



        //Date Picker
        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(PostRideOffers.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(PostRideOffers.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });




        //TIME PICKER

        chooseTime = findViewById(R.id.etChooseTime);
        btn = findViewById(R.id.btn_time);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(PostRideOffers.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        int hour = hourOfDay % 12;
                        if (hour == 0)
                            hour = 12;
                        chooseTime.setText(String.format("%02d:%02d %s", hour, minutes,
                                hourOfDay < 12 ? "am" : "pm"));

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });


        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(PostRideOffers.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        int hour = hourOfDay % 12;
                        if (hour == 0)
                            hour = 12;
                        chooseTime.setText(String.format("%02d:%02d %s", hour, minutes,
                                hourOfDay < 12 ? "am" : "pm"));

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        //Radio button
        radio2 = findViewById(R.id.radio2);
        radio1 = findViewById(R.id.radio1);


        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.layout1).setVisibility(View.GONE);
                findViewById(R.id.one).setVisibility(View.GONE);
                findViewById(R.id.two).setVisibility(View.GONE);
                findViewById(R.id.three).setVisibility(View.GONE);
                findViewById(R.id.four).setVisibility(View.GONE);
                findViewById(R.id.five).setVisibility(View.GONE);}


        });
        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.layout1).setVisibility(View.VISIBLE);
            }

            });

        int  length = Integer.parseInt(dropdown.getSelectedItem().toString());


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int  length = Integer.parseInt(parentView.getSelectedItem().toString());

                findViewById(R.id.one).setVisibility(View.GONE);
                findViewById(R.id.two).setVisibility(View.GONE);
                findViewById(R.id.three).setVisibility(View.GONE);
                findViewById(R.id.four).setVisibility(View.GONE);
                findViewById(R.id.five).setVisibility(View.GONE);
            if (length==0)
            {
                findViewById(R.id.one).setVisibility(View.GONE);
                findViewById(R.id.two).setVisibility(View.GONE);
                findViewById(R.id.three).setVisibility(View.GONE);
                findViewById(R.id.four).setVisibility(View.GONE);
                findViewById(R.id.five).setVisibility(View.GONE);
            }
            if(length==1) findViewById(R.id.one).setVisibility(View.VISIBLE);
                if(length==2) {findViewById(R.id.one).setVisibility(View.VISIBLE); findViewById(R.id.two).setVisibility(View.VISIBLE);}
                if(length==3) {findViewById(R.id.one).setVisibility(View.VISIBLE); findViewById(R.id.two).setVisibility(View.VISIBLE);findViewById(R.id.three).setVisibility(View.VISIBLE);}
                if(length==4) {findViewById(R.id.one).setVisibility(View.VISIBLE); findViewById(R.id.two).setVisibility(View.VISIBLE);findViewById(R.id.three).setVisibility(View.VISIBLE);findViewById(R.id.four).setVisibility(View.VISIBLE);}
                if(length==5) {findViewById(R.id.one).setVisibility(View.VISIBLE); findViewById(R.id.two).setVisibility(View.VISIBLE);findViewById(R.id.three).setVisibility(View.VISIBLE);findViewById(R.id.four).setVisibility(View.VISIBLE);findViewById(R.id.five).setVisibility(View.VISIBLE);}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }




        });
    }
}
