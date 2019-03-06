package com.example.kiran.carpool;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.User;
import com.google.gson.Gson;

public class Login extends AppCompatActivity {
    Button button;
    EditText  email,pass;
    String result;
    String Vemail,Vpass;
    final User newUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.lemail);
        pass = (EditText) findViewById(R.id.lpassword);
        button = findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()){
                   RegisterUser registerUser = new RegisterUser();
                    registerUser.execute();
                }
            }
            private Boolean check(){
                Vemail = email.getText().toString();
                Vpass = pass.getText().toString();


                newUser.setEmail(Vemail);
                newUser.setPass(Vpass);
                return true;
            }
        });


    }

    class RegisterUser extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(getApplicationContext());
            Gson gson = new Gson();
            String userJson = gson.toJson(newUser, User.class);
            System.out.println("User Json - " + userJson);
            result = httpManager.postData("http://10.0.2.2:3000/login",userJson);
            System.out.println("Result - " + result);
            return result;
        }

        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
            if (result.equals("1")) {
                System.out.println("Result - " + result);
                Intent myIntent = new Intent(Login.this, Nav.class);
                startActivity(myIntent);
            }

        }


    }
}