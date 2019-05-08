package com.example.kiran.carpool;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.example.kiran.carpool.Util.Models.StaticClass;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostRideOffers extends AppCompatActivity {
    Spinner sp1,sp2;
    Button selectDate,btn,btn_post;
    ArrayAdapter<CharSequence> adapter1, adapter2;
    EditText date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    String durationText;
    String distanceText;
    Calendar calendar;
    boolean EdittextCheck;
    //holds value from UI to send it asynctask
    String source,sourceName, dest,destName, sourceLat,sourcelng, destLat,destLong,DATE,TIME,SEATS,amount;
    TextView txt1;

    EditText chooseTime,amt;
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;
    LatLng latLng1, latLng2;
    private static final String API_KEY = "AIzaSyCFz3q92Zyd5Ia1aAR1TU2L3rzXq8UAuI4";

    RiderPosts ride = new RiderPosts();
    StaticClass staticClass =new StaticClass();
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private EditText editTextSource, editTextDestination;
    private String TAG = "CreateRide";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ride_offers);

        //spinner1
        sp1=(Spinner) findViewById(R.id.spinner1);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.seats, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                SEATS=  sp1.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(pos) + " slected ", ((Toast.LENGTH_LONG)/2)).show();

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        editTextDestination = (EditText)findViewById(R.id.editText_Destination);
        editTextDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EdittextCheck=true;
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(PostRideOffers.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {

                }
            }
        });
            editTextSource = (EditText)findViewById(R.id.editText_Source);
            editTextSource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EdittextCheck=false;
                    try {
                        Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(PostRideOffers.this);

                        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);

                    } catch (GooglePlayServicesRepairableException e) {
                        // TODO: Handle the error.
                    } catch (GooglePlayServicesNotAvailableException e) {

                    }
                }
            });
            amt = findViewById(R.id.amount);

            amt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt1 = findViewById(R.id.hide);
                    txt1.setVisibility(View.GONE);

                }
            });

        txt1 = findViewById(R.id.hide);
        txt1.setVisibility(View.VISIBLE);
        //Date Picker
        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);
        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dist();
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(PostRideOffers.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                                SettingDateVariables(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }


        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dist();

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(PostRideOffers.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                                SettingDateVariables(day + "/" + (month + 1) + "/" + year);
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
                        String time= String.format("%02d:%02d %s", hour, minutes,
                                hourOfDay < 12 ? "am" : "pm");
                        chooseTime.setText(time);
                        SetTimeVariable(time);

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
                        String time= String.format("%02d:%02d %s", hour, minutes,
                                hourOfDay < 12 ? "am" : "pm");
                        chooseTime.setText(time);
                        SetTimeVariable(time);

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });




            btn_post = (Button) findViewById(R.id.posts);
            btn_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //calling Async
                    if(check()){
                      RegisterUser registerUser = new RegisterUser();
                      registerUser.execute();
                }
                }
                private Boolean check(){
                    amount =amt.getText().toString();
                    ride.setSoure_lat(sourceLat);
                    ride.setSoure_long(sourcelng);
                    ride.setSourceName(sourceName);
                    ride.setDestinationName(destName);

                    ride.setDest_lat(destLat);
                    ride.setDest_long(destLong);
                    ride.setSource(source);
                    ride.setAmount(amount);
                    ride.setDestination(dest);
                    ride.setSeats(SEATS);

             ride.setDate(DATE);
             ride.setTime(TIME);
             System.out.print("id is " +staticClass.getUserID());
             ride.setUserid(staticClass.getUserID());
             ride.setDistance(distanceText);
             ride.setDuration(durationText);
                    return true;
                }
            });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);

                if(EdittextCheck==false) {
                    editTextSource.setText(place.getName().toString());
                    if (place != null) {
                        latLng1 = place.getLatLng();
                        sourceLat = String.valueOf(latLng1.latitude);
                        sourcelng = String.valueOf(latLng1.longitude);
                        source = place.getAddress().toString();
                        sourceName =place.getName().toString();
                    }
                }

                if(EdittextCheck==true){
                    editTextDestination.setText(place.getName().toString());
                    if (place != null) {
                         latLng2 = place.getLatLng();
                        destLat = String.valueOf(latLng2.latitude);
                        destLong = String.valueOf(latLng2.longitude);
                        dest = place.getAddress().toString();
                        destName =place.getName().toString();
                    }
                }



            }

            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }


    }

    private void dist() {
        System.out.println("FUNCTION1");
        String url = getDirectionsUrl();
        new GetDisDur().execute(url);


    }

    private String getDirectionsUrl() {
        System.out.println("FUNCTION2");

        String str_origin = "origin=" + sourceLat + "," + sourcelng;

        String str_dest = "destination=" + destLat + "," + destLong;

//        String sensor = "sensor=false";
//
//        String mode = "mode=driving";

        String parameters = str_origin + "&" + str_dest + "&key=" +getString(R.string.serverkey);

//                + "&"  + sensor + "&" + mode;

        String output = "json";

        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
    }



    private void SetTimeVariable(String time) {
        TIME = time;
    }

    private void SettingDateVariables(String s) {
        DATE=s;
    }

    class RegisterUser extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(getApplicationContext());
            Gson gson = new Gson();
            String userJson = gson.toJson(ride, RiderPosts.class);
            System.out.println("User Json - " + userJson);
            String result = HttpManager.postData(getResources().getString(R.string.serviceUrl)+"/insertRideOffers",userJson);
            System.out.println("Result - " + result);

            return result;
        }

        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);

            if (result != null && !result.isEmpty()) {
                System.out.println("Result - " + result);
                Intent myIntent = new Intent(PostRideOffers.this, Nav.class);
                startActivity(myIntent);
            }

        }


    }



    //Direction Api class

    private class GetDisDur extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";
            System.out.println("Asynk");
            try {
                 data = HttpManager.getData(url[0]);

            } catch (Exception e) {
                Log.d("Background Task", e.toString());

            }

            System.out.println("DATA " + data);

            return data;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("RESULT " + result);
            if (result != null && !result.isEmpty()) {
                try {
                    JSONObject jsonObject = new JSONObject(result);

                    JSONArray routes = jsonObject.getJSONArray("routes");

                    JSONObject routes1 = routes.getJSONObject(0);

                    JSONArray legs = routes1.getJSONArray("legs");

                    JSONObject legs1 = legs.getJSONObject(0);

                    JSONObject distance = legs1.getJSONObject("distance");

                    JSONObject duration = legs1.getJSONObject("duration");

                    distanceText = distance.getString("text");

                    durationText = duration.getString("text");

                        String pattern = "([^\\s]+)";

                        // Create a Pattern object
                        Pattern r = Pattern.compile(pattern);

                        Matcher m = r.matcher(distanceText);

                        if (m.find()) {
                            distanceText = m.group(0);
                            distanceText=   distanceText.replaceAll("[,;\\s]", "");
                            System.out.println("Found value: " + m.group(0));

                        } else {
                            System.out.println("NO MATCH");
                        }
                        System.out.println(" Finally distance is " + distanceText + "   " + durationText);


                      try{
                          int s = (int) (4.11 * (Float.parseFloat(distanceText)));
                          System.out.println("amount " + Double.toString(s));
                          amt = findViewById(R.id.amount);
                          amt.setHint(Double.toString(s));

                      }

                      catch (Exception e){

                          System.out.println("Exception " + e);
                      }





                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            else {
                Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT);
            }
        }
    }
}
