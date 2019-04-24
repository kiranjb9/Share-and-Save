package com.example.kiran.carpool.RideSeeker;

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
import android.widget.Toast;

import com.example.kiran.carpool.R;
import com.example.kiran.carpool.RideSeeker.Adapters.Reqsent;
import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.AllSentReq.AllSentReq;
import com.example.kiran.carpool.Util.Models.StaticClass;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;



public class Fragment2 extends Fragment {

    ListView listV;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view ;
        view = inflater.inflate(R.layout.list3, container, false);
        DialogReq d = new DialogReq();
        d.execute();
        listV = view.findViewById(R.id.simpleListView3);
        context = getActivity();
        return view;
    }

    class DialogReq extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(context);
            Gson gson = new Gson();

            StaticClass staticClass = new StaticClass();
            String result = HttpManager.getData(getResources().getString(R.string.serviceUrl)+"/all_sent_requests/"+staticClass.getUserID());
            System.out.println("Result - " + result);

            return result;
        }

        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
            Gson gson = new Gson();
           List<AllSentReq> userList = gson.fromJson(result, new TypeToken<List<AllSentReq>>() {
            }.getType());


               if (TextUtils.isEmpty(result)) {

                   Toast.makeText(context,"EMPTY RESULT",Toast.LENGTH_SHORT).show();

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
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("SENT REQUESTS");
    }
}
