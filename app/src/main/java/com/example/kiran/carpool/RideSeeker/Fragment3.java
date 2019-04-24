package com.example.kiran.carpool.RideSeeker;

import android.content.Context;
import android.net.Uri;
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

import com.example.kiran.carpool.Entrypage3;
import com.example.kiran.carpool.R;
import com.example.kiran.carpool.Riders_Adapters.Agreed_Adapter;
import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.Agreed_Model;
import com.example.kiran.carpool.Util.Models.StaticClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class Fragment3 extends Fragment {
    ListView listV;
    Context context ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.list3, container, false);
        listV = view.findViewById(R.id.simpleListView3);
        RegisterUser1 r = new RegisterUser1();
        r.execute();

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("AGREED RIDES");
    }

    public  class RegisterUser1 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(context);
            String result = HttpManager.getData(getResources().getString(R.string.serviceUrl) + "/Agreed_requests/"+ StaticClass.getUserID());
            return result;
        }


        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
            Gson gson = new Gson();
            final List<Agreed_Model> userList = gson.fromJson(result, new TypeToken<List<Agreed_Model>>() {
            }.getType());

            if (TextUtils.isEmpty(result)) {
                Toast.makeText(context,"errorr",Toast.LENGTH_SHORT).show();

            } else {
                if (userList != null && userList.size() > 0) {
                    System.out.println("USER LIST CONTENT " + userList.get(0).toString());

                    Agreed_Adapter adapter = new Agreed_Adapter(context, R.layout.fragment_fragment3, userList);
                    listV.setAdapter(adapter);
                }

            }
        }
    }
}
