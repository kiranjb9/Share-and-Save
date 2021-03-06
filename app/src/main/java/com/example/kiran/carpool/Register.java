package com.example.kiran.carpool;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.User;
import com.google.gson.Gson;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_MEDIA = 1;
    Button button;
    Uri selectedImageUri=null;
    Calendar calendar;
    String gender;
    File file;
    EditText fname, lname, email, mobilenumber,pass;
    String result;
    String Vfname,Vlname,Vemail,Vmobile,Vpass,Vadress,DATE,TIME;
    Context context;
    CircleImageView c;
    DatePickerDialog datePickerDialog;
    int year;
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;
    int month;
    int dayOfMonth;
    final User newUser = new User();
    private static final int PICK_PHOTO_FOR_AVATAR = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = getApplicationContext();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_MEDIA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }



        EditText e= findViewById(R.id.Adress);
        fname= (EditText) findViewById(R.id.FirstName);
        lname= (EditText) findViewById(R.id.LastName);
        email = (EditText) findViewById(R.id.Email);
        mobilenumber = (EditText) findViewById(R.id.MobileNumber);
        pass = (EditText) findViewById(R.id.Password);
        c = findViewById(R.id.profile_image);
        Button clickButton = (Button) findViewById(R.id.button);
        ImageView selectDate = findViewById(R.id.calender);
        TextView selectedDate = findViewById(R.id.calenderDate);




        //profile pic
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);




            }
        });
        //Radio Button COde
        RadioGroup r =  findViewById(R.id.RadioGP);

        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                 gender = radioButton.getText().toString();
            }
        });


        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //calling Async
                if(check()){
                    if(selectedImageUri!=null){
                        TextInputLayout T = (TextInputLayout) findViewById(R.id.tilImage);

                        T.setError(null);
                        String imagepath = getRealPathFromURI(selectedImageUri);
                        File imageFile = new File(imagepath);
                        uploadFile(imageFile.getName(),imageFile);

                    }
                    else{
                        TextInputLayout T = (TextInputLayout) findViewById(R.id.tilImage);

                        T.setError("Please Select an Image");
                    }

                }

            }
            private Boolean check(){
                Vadress= e.getText().toString();
                Vfname = fname.getText().toString();
                Vlname = lname.getText().toString();
                Vemail = email.getText().toString();
                Vpass = pass.getText().toString();
                Vmobile = mobilenumber.getText().toString();

                return true;
            }
        });


        //Date Picker

        selectDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                selectedDate.setText(day + "/" + (month + 1) + "/" + year);
                                SettingDateVariables(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }


        });

        selectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog = new DatePickerDialog(Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                selectedDate.setText(day + "/" + (month + 1) + "/" + year);
                                SettingDateVariables(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


    }



    public Boolean uploadFile(String imageName , File image) {
        try {

            System.out.println("///////////////////////entered uploadFile");
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("image", imageName, RequestBody.create(MediaType.parse("image/png"), image))
                    .build();
            String u= getResources().getString(R.string.serviceUrl)+"/insertUserData/"+Vfname+"/"+Vlname+"/"+Vmobile+"/"+Vemail+"/"+Vpass+"/"+DATE+"/"+gender+"/"+Vadress;
            System.out.println("URL is  : "+ u);
                        Request request = new Request.Builder()
                    .url(u)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(final Call call, final IOException e) {

                    Register.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Handle UI here
                            Intent myIntent = new Intent(Register.this, StartPage.class);
                            startActivity(myIntent);                        }
                    });
                }

                @Override
                public void onResponse(final Call call, final Response response) throws IOException {

                    Register.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(response.code()==200){
                                Intent myIntent = new Intent(Register.this, Login.class);
                                startActivity(myIntent);
                            }





                                                    }
                    });


                }

            });

            return true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK  && null != data) {
            if (data == null) {
                TextInputLayout T = (TextInputLayout) findViewById(R.id.tilImage);

                    T.setError("Please Select an Image");

            }
            try {
                TextInputLayout T = (TextInputLayout) findViewById(R.id.tilImage);

                T.setError(null);
                 selectedImageUri = data.getData();
                ImageView imageView = (ImageView) findViewById(R.id.profile_image);
                Bitmap bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
                imageView.setImageBitmap(bitmap);


            } catch (Exception e) {
                e.printStackTrace();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }

        else{
            TextInputLayout T = (TextInputLayout) findViewById(R.id.tilImage);

            T.setError("Please Select an Image");
        }
    }
    private String getRealPathFromURI(Uri contentURI) {

        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();
        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null
                , MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_MEDIA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    private void SettingDateVariables(String s) {
        DATE=s;
    }


}

