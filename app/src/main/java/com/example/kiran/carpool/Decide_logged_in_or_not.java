package com.example.kiran.carpool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kiran.carpool.Util.Models.StaticClass;

public class Decide_logged_in_or_not extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSharedPreferences("MyPref",0).getBoolean("isLoginKey",false)){
            StaticClass.setUserID(getSharedPreferences("MyPref",0).getString("id",""));
            Intent i = new Intent(Decide_logged_in_or_not.this, StartPage.class);
            startActivity(i);
            finish();
        }else{
            System.out.println("No Login");
            Intent i = new Intent(Decide_logged_in_or_not.this, Login.class);
            startActivity(i);
            finish();
        }
    }
}
