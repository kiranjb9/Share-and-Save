package com.example.kiran.carpool;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.User;
import com.google.gson.Gson;

public class Register extends AppCompatActivity {
    Button button;
    EditText fname, lname, email, mobilenumber,pass;
    String result;
    String Vfname,Vlname,Vemail,Vmobile,Vpass;
    final User newUser = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname= (EditText) findViewById(R.id.FirstName);
       lname= (EditText) findViewById(R.id.LastName);
      email = (EditText) findViewById(R.id.Email);
        mobilenumber = (EditText) findViewById(R.id.MobileNumber);
        pass = (EditText) findViewById(R.id.Password);

        // instanstiating Getter Setter class


         //getting values from EditText into String variable


        //button click
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
                System.out.println("JUST CHECING"+Vlname);
                newUser.setLname(Vlname);
                newUser.setEmail(Vemail);
                newUser.setMobilenumber(Vmobile);
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
}

