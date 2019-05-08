package com.example.kiran.carpool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.User;
import com.google.gson.Gson;

import java.io.InputStream;

public class Review extends AppCompatActivity {
SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_activity);

        TextView name = findViewById(R.id.name);
        TextView Mobile = findViewById(R.id.Mobile);
        TextView email = findViewById(R.id.email);
        TextView homeAddress = findViewById(R.id.homeAddress);
        TextView DOB = findViewById(R.id.DOB);
        TextView name1 = findViewById(R.id.headerTxt1);
        TextView name2 = findViewById(R.id.headerTxt2);
        TextView backBtn = findViewById(R.id.backbutton);
        ImageView edit = findViewById(R.id.editIcon);


        pref = getSharedPreferences("MyPref", MODE_PRIVATE);

        name.setText(pref.getString("fname","") + " " + pref.getString("lname",""));
        Mobile.setText(pref.getString("phno",""));
        email.setText(pref.getString("email",""));
        homeAddress.setText((pref.getString("adress","")));
        DOB.setText((pref.getString("DOB","")));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Review.this, UpdateUser.class);
                startActivity(myIntent);
            }
        });
        name1.setText(pref.getString("fname","") + " " + pref.getString("lname",""));
        name2.setText(pref.getString("email",""));


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Review.this, Nav.class);
                startActivity(myIntent);
            }
        });

        pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String u = getResources().getString(R.string.serviceUrl)+"/images/"+ pref.getString("id","")+".jpg";
        System.out.println(u);
        new DownloadImageTask( findViewById(R.id.profile_image4))
                .execute(u);



    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



}
