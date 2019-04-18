package com.example.kiran.carpool;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.example.kiran.carpool.Util.Models.StaticClass;
import com.example.kiran.carpool.Riders_Adapters.Posts_Adapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Belal on 18/09/16.
 */


public class entryPage extends Fragment {
    Button b1;Context context;
    ListView listV;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();

        final View view = inflater.inflate(R.layout.list1, container, false);
        b1 = (Button) view.findViewById(R.id.btn_PostRideOffer);





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getContext(), PostRideOffers.class);
                startActivity(myintent);
            }
        });

        listV = view.findViewById(R.id.simpleListView);
        RegisterUser registerUser=new RegisterUser();
        registerUser.execute();



        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 1");
    }


    class RegisterUser extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(getActivity());
            String result = HttpManager.getData(getResources().getString(R.string.serviceUrl) + "/get_posts_by_id/"+ StaticClass.getUserID());
            return result;
        }


        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
            Gson gson = new Gson();
            final List<RiderPosts> userList = gson.fromJson(result, new TypeToken<List<RiderPosts>>() {
            }.getType());

            if (TextUtils.isEmpty(result)) {

            } else {
                if (userList != null && userList.size() > 0) {
                    System.out.println("USER LIST CONTENT " + userList.get(0).toString());

                    Posts_Adapter adapter = new Posts_Adapter(context, R.layout.fragment_entry_page, userList);
                    listV.setAdapter(adapter);
                }

            }
        }
    }


}