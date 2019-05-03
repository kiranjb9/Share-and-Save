package com.example.kiran.carpool;

import android.Manifest;
import android.app.Activity;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    File file;
    EditText fname, lname, email, mobilenumber,pass;
    String result;
    String Vfname,Vlname,Vemail,Vmobile,Vpass;
    Context context;
    CircleImageView c;
    final User newUser = new User();
    private static final int PICK_PHOTO_FOR_AVATAR = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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














        fname= (EditText) findViewById(R.id.FirstName);
       lname= (EditText) findViewById(R.id.LastName);
      email = (EditText) findViewById(R.id.Email);
        mobilenumber = (EditText) findViewById(R.id.MobileNumber);
        pass = (EditText) findViewById(R.id.Password);
        c = findViewById(R.id.profile_image);


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

        Button clickButton = (Button) findViewById(R.id.button);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //calling Async
                if(check()){
                    RegisterUser registerUser = new RegisterUser();
                    registerUser.execute();
                }

            }
            private Boolean check(){
                Vfname = fname.getText().toString();
                Vlname = lname.getText().toString();
                Vemail = email.getText().toString();
                Vpass = pass.getText().toString();
                Vmobile = mobilenumber.getText().toString();

                newUser.setFname(Vfname);
                newUser.setLname(Vlname);
                newUser.setEmail(Vemail);
                newUser.setMobilenumber(Vmobile);
                newUser.setPass(Vpass);
                return true;
            }
        });


    }



    public Boolean uploadFile(String imageName , File image) {
        try {

            System.out.println("///////////////////////entered uploadFile");
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("image", imageName, RequestBody.create(MediaType.parse("image/*"), image))
                    .build();

            Request request = new Request.Builder()
                    .url(getResources().getString(R.string.serviceUrl)+"/upload")
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(final Call call, final IOException e) {

                    Register.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Handle UI here
                            Toast.makeText(Register.this, "nah", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(final Call call, final Response response) throws IOException {

                    Register.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Handle UI here
                            Toast.makeText(Register.this, "Succesfully uploaded", Toast.LENGTH_SHORT).show();
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
                return;
            }
            try {
                Uri selectedImageUri = data.getData();
                ImageView imageView = (ImageView) findViewById(R.id.profile_image);
                Bitmap bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
                imageView.setImageBitmap(bitmap);


                String imagepath = getRealPathFromURI(selectedImageUri);
                File imageFile = new File(imagepath);
                uploadFile(imageFile.getName(),imageFile);


            } catch (Exception e) {
                e.printStackTrace();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
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
        class RegisterUser extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                HttpManager httpManager = new HttpManager(getApplicationContext());
                Gson gson = new Gson();
                String userJson = gson.toJson(newUser, User.class);
                System.out.println("User Json - " + userJson);
                result = httpManager.postData(getResources().getString(R.string.serviceUrl)+"/insertUserData",userJson);
                System.out.println("Result - " + result);

                return result;
            }

            protected void onPostExecute(String result) {
                System.out.println("Result - " + result);
                if (result.equals("1")) {
                    System.out.println("Result - " + result);
                    Intent myIntent = new Intent(Register.this, Login.class);
                    startActivity(myIntent);
                }

            }


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



}

