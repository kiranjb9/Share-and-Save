package com.example.kiran.carpool.Riders_Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.kiran.carpool.Entrypage2;
import com.example.kiran.carpool.R;

public class call_fragment extends FragmentActivity {

    public void call(){
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        Entrypage2 regcomplainfragment = new Entrypage2();
        fragmenttransaction.replace(R.id.content_frame, regcomplainfragment).addToBackStack("HomeFragment");
        fragmenttransaction.commit();
    }


}
