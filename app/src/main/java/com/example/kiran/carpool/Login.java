package com.example.kiran.carpool;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.StaticClass;
import com.example.kiran.carpool.Util.Models.User;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Login extends AppCompatActivity {
    Button button,button2;
    private LoginButton loginButton;
    EditText  email,pass;
    String result;
    AccessToken fbTOken;
    String Vemail,Vpass;
    final User newUser = new User();
    StaticClass s = new StaticClass();
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });




//        callbackManager = CallbackManager.Factory.create();
//
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
//
//
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                     System.out.println("77777777777777777777777   Onsucess");
//                        GraphRequest.newMeRequest(
//                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                                    @Override
//                                    public void onCompleted(JSONObject me, GraphResponse response) {
//                                        if (response.getError() != null) {
//                                            // handle error
//                                        } else {
//                                            String email = me.optString("email");
//                                            System.out.println("77777777777777777777777   Onsucess"+email);
//
//                                            Intent myIntent = new Intent(Login.this, StartPage.class);
//                                            startActivity(myIntent);
//                                        }
//                                    }
//                                }).executeAsync();
//
//
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                    }
//
//
//                });
//
//
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//


        email = (EditText) findViewById(R.id.lemail);
        pass = (EditText) findViewById(R.id.lpassword);
        button = findViewById(R.id.btn);
        button2 =findViewById(R.id.btn_register);
        button2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent myIntent = new Intent(Login.this, Register.class);
                                           startActivity(myIntent);
                                       }

                                   });
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
    private void goMainScreen() {
        Intent intent = new Intent(this, StartPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
            JSONObject jresponse = null;
            try {
                jresponse = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(jresponse.optString("Fname"));

            s.setUserID(jresponse.optString("_id"));



            if (result != null && !result.isEmpty()) {

                Intent myIntent = new Intent(Login.this, StartPage.class);
                startActivity(myIntent);
            }
            if (TextUtils.isEmpty(result)) {
             Toast.makeText(getApplicationContext(),"Password or Email Id is wrong",Toast.LENGTH_SHORT).show();

            }



        }


    }
}
