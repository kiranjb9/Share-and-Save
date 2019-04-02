package com.example.kiran.carpool.RideSeeker;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kiran.carpool.R;
import com.example.kiran.carpool.RideSeeker.Adapters.Reqsent;
import com.example.kiran.carpool.RideSeeker.Adapters.SearchListAdapter;
import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.AllSentReq.AllSentReq;
import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.example.kiran.carpool.Util.Models.StaticClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Fragment2 extends Fragment {
    List<AllSentReq>  userList;
    ListView listV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view ;
        view = inflater.inflate(R.layout.list3, container, false);
       DialogReq d = new DialogReq();
        d.execute();

        return view;
    }

    class DialogReq extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(getApplicationContext());
            Gson gson = new Gson();

            StaticClass staticClass = new StaticClass();
            String result = HttpManager.getData(getResources().getString(R.string.serviceUrl)+"/all_sent_requests/"+staticClass.getUserID());
            System.out.println("Result - " + result);

            return result;
        }

        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
            Gson gson = new Gson();
//            List<AllSentReq>  userList = gson.fromJson(result, new TypeToken<List<AllSentReq>>() {
//            }.getType());

            if (TextUtils.isEmpty(result)) {

            }

            else {
                if (userList != null && userList.size() > 0) {
                    System.out.println("USER LIST CONTENT " + userList.get(0).toString());


                    Reqsent adapter = new Reqsent(getContext(), R.layout.fragment_fragment2, userList);
                    listV.setAdapter(adapter);



                }

            }

        }


    }
}
