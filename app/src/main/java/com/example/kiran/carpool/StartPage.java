package com.example.kiran.carpool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kiran.carpool.RideSeeker.MainActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONObject;

public class StartPage extends AppCompatActivity {
Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Bundle bundle1 = intent.getExtras();
        b1 = findViewById(R.id.btn_rideoffer);
        b2 = findViewById(R.id.btn_needRide);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                bundle1.putString("_id", bundle.getString("_id"));
//                bundle1.putString("email", bundle.getString("email"));
//                bundle1.putString("phno", bundle.getString("phno"));
//                bundle1.putString("Fname", bundle.getString("Fname"));
//                bundle1.putString("Lname", bundle.getString("Lname"));
                Intent myIntent = new Intent(StartPage.this, Nav.class);
//                myIntent.putExtras(bundle1);
                startActivity(myIntent);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StartPage.this, MainActivity.class);
                startActivity(myIntent);

            }
        });


        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Insert your code here
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name");

        request.setParameters(parameters);
        request.executeAsync();
        System.out.println(parameters.getString("fields"+"              00000000000000000000000000"));


    }
}
