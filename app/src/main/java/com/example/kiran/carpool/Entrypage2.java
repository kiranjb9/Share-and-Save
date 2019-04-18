package com.example.kiran.carpool;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kiran.carpool.Riders_Adapters.All_Requests_Adapters;
import com.example.kiran.carpool.Riders_Adapters.Posts_Adapter;
import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.Model_All_req;
import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.example.kiran.carpool.Util.Models.StaticClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class Entrypage2 extends Fragment {
    ListView listV;
    Context context ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.list3, container, false);
        listV = view.findViewById(R.id.simpleListView3);
        RegisterUser r = new RegisterUser();
        r.execute();

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Requests Recieved");
    }



    class RegisterUser extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(getActivity());
            String result = HttpManager.getData(getResources().getString(R.string.serviceUrl) + "/allrequests/"+ StaticClass.getUserID());
            return result;
        }


        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
            Gson gson = new Gson();
            final List<Model_All_req> userList = gson.fromJson(result, new TypeToken<List<Model_All_req>>() {
            }.getType());

            if (TextUtils.isEmpty(result)) {

            } else {
                if (userList != null && userList.size() > 0) {
                    System.out.println("USER LIST CONTENT " + userList.get(0).toString());

                    All_Requests_Adapters adapter = new All_Requests_Adapters(context, R.layout.fragment_entry_page_2, userList);
                    listV.setAdapter(adapter);
                }

            }
        }
    }

}
