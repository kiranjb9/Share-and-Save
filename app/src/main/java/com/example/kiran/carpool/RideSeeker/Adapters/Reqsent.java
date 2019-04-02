package com.example.kiran.carpool.RideSeeker.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiran.carpool.R;
import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.AllSentReq.AllSentReq;
import com.example.kiran.carpool.Util.Models.AllSentReq.Sent;
import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.google.gson.Gson;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Reqsent extends ArrayAdapter<AllSentReq> {
    RiderPosts ride;
    AllSentReq all;
    Sent s;

    String num_seats;
    int vg;
    String req_for_post;
    String result,postid;
    Button req,request,cancel;
    Spinner spinner;
    List<AllSentReq> userList;
    ArrayAdapter<CharSequence> adapter1;

    Context context;

    public Reqsent(Context context, int activity, List<AllSentReq> list){

        super(context,activity,list);

        this.context=context;

        this.vg=activity;

        this.userList=list;
        System.out.println("entered ListAdapter");

    }



    public View getView(int position, View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(vg, parent, false);
        AllSentReq p = userList.get(position);


        TextView txt=itemView.findViewById(R.id.Sent);

        try {
            Toast.makeText(getContext(),"entered try",Toast.LENGTH_SHORT).show();
            txt.setText("You requested a ride to" +s.getRequests_sent_for_the_user().getFname()+" "+s.getRequests_sent_for_the_user().getLname()+ "for the below post");

            Toast.makeText(getContext(),s.getRequests_sent_for_post().getSource(),Toast.LENGTH_SHORT).show();





        } catch (Exception e) {
            System.out.println("ERROR"+e);
            e.printStackTrace();
        }

        return itemView;

    }

    class DialogReq extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(getApplicationContext());
            Gson gson = new Gson();
            String userJson = gson.toJson(ride, RiderPosts.class);
            System.out.println("User Json - " + userJson);
            result = HttpManager.getData(context.getResources().getString(R.string.serviceUrl)+"/requesting/"+postid+"/"+req_for_post+"/"+num_seats);
            System.out.println("Result - " + result);

            return result;
        }

        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
            Gson gson = new Gson();

            if (TextUtils.isEmpty(result)) {

            }

            else {
                if (userList != null && userList.size() > 0) {
                    System.out.println("USER LIST CONTENT " + userList.get(0).toString());
                    Toast.makeText(getContext(),"REQUEST SENT",Toast.LENGTH_SHORT).show();



                }

            }

        }


    }

}
