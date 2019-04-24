package com.example.kiran.carpool.Riders_Adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiran.carpool.Entrypage2;
import com.example.kiran.carpool.Nav;
import com.example.kiran.carpool.R;
import com.example.kiran.carpool.RideSeeker.Adapters.SearchListAdapter;
import com.example.kiran.carpool.Util.HttpManager;

import com.example.kiran.carpool.Util.Models.Model_All_req;
import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.example.kiran.carpool.Util.Models.StaticClass;
import com.example.kiran.carpool.entryPage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class All_Requests_Adapters extends ArrayAdapter<Model_All_req>{
    Model_All_req ride;



    String num_seats;
    LayoutInflater inflater;
    View itemView;
    int vg;
    String user_who_sent_id,seats,Ride_postedBy;
    String result,obj_id;
    String post_id;
    Button Delete,Accept;
    ListView listV;
    Spinner spinner;
    List<Model_All_req> userList;
    ArrayAdapter<CharSequence> adapter1;

    Context context;

    public All_Requests_Adapters(Context context, int activity, List<Model_All_req> list){

        super(context,activity,list);

        this.context=context;

        this.vg=activity;

        this.userList=list;
        System.out.println("entered ListAdapter");

    }



    public View getView(int position, View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      itemView = inflater.inflate(vg, parent, false);
        listV = itemView.findViewById(R.id.simpleListView3);

        Delete = itemView.findViewById(R.id.deleteId);
        Accept = itemView.findViewById(R.id.acceptId);
        Model_All_req p = userList.get(position);
        System.out.println("Position "+position);

        TextView req_txt1=itemView.findViewById(R.id.txtReq1);
        TextView req_txt2=itemView.findViewById(R.id.txtReq2);

        TextView txt2=itemView.findViewById(R.id.clickableName);
        TextView txt3=itemView.findViewById(R.id.clickablePost);
        TextView textSource=itemView.findViewById(R.id.source);
        TextView textDest=itemView.findViewById(R.id.dest);
        TextView textSeats=itemView.findViewById(R.id.req_seats);
        Button textDelete=itemView.findViewById(R.id.delete_sent_Req);


        try {
            req_txt1.setText("You recieved a Request from " + p.getFname() + " " + p.getLname() + "for your Ride");
            req_txt2.setText( "Post" +p.getReq_seats());
//            obj_id = p.getObj_id();
//            user_who_sent_id = p.getUser_who_sent_id();
//            post_id = p.getPost_id();
//            seats = p.getReq_seats();

        } catch (Exception e) {


            System.out.println("ERROR" + e);
            e.printStackTrace();
        }
        Accept.setTag(position);
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Model_All_req user = getItem(position);

                obj_id = user.getObj_id();
                user_who_sent_id = user.getUser_who_sent_id();
                post_id = user.getPost_id();
                seats = user.getReq_seats();
                Ride_postedBy = user.getRide_postedBy();

                DialogReq r1 = new DialogReq();
                r1.execute();


                userList.remove(position);
                All_Requests_Adapters.this.notifyDataSetChanged();

            }
        });




        Delete.setTag(position);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = (Integer) v.getTag();
                Model_All_req user = getItem(position);
                obj_id = user.getObj_id();
                user_who_sent_id = user.getUser_who_sent_id();
                post_id = user.getPost_id();
                DeleteReq r1 = new DeleteReq();
                r1.execute();


                userList.remove(position);
                All_Requests_Adapters.this.notifyDataSetChanged();

            }
        }  );

        return itemView;

    }

    class DialogReq extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(getApplicationContext());
            Gson gson = new Gson();
            String userJson = gson.toJson(ride, RiderPosts.class);
            System.out.println("User Json - " + userJson);
            result = HttpManager.getData(context.getResources().getString(R.string.serviceUrl) + "/Accept/" + post_id + "/" + obj_id + "/" + user_who_sent_id + "/" + Ride_postedBy+"/" + seats);
            System.out.println("Result - " + result);

            return result;
        }

        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
            Gson gson = new Gson();

            if (result == "1") {
                    System.out.println("USER LIST CONTENT " + userList.get(0).toString());
                    AlertDialog.Builder mbuilder = new AlertDialog.Builder(getContext());
                    mbuilder.setTitle("Request Accepted");
                    mbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                        }
                    });
                    AlertDialog dialog=mbuilder.create();
                    dialog.show();
                }

            else {
                Toast.makeText(context,result,Toast.LENGTH_SHORT).show();

            }


        }


    }
    class DeleteReq extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(getApplicationContext());
            Gson gson = new Gson();
            String userJson = gson.toJson(ride, RiderPosts.class);
            System.out.println("User Json - " + userJson);
            result = HttpManager.getData(context.getResources().getString(R.string.serviceUrl) + "/deleteRequests/" + post_id + "/" + obj_id + "/" + user_who_sent_id );
            System.out.println("Result - " + result);

            return result;
        }

        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
            Gson gson = new Gson();
            if (result == "0") {

            }

            else {
                if (result == "1") {
                    AlertDialog.Builder mbuilder = new AlertDialog.Builder(getContext());
                    mbuilder.setTitle("Request Deleted :(");
                    mbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog=mbuilder.create();
                    dialog.show();
                }

            }


        }


    }

//    class RegisterUser extends AsyncTask<Void, Void, String> {
//        @Override
//        protected String doInBackground(Void... params) {
//            HttpManager httpManager = new HttpManager(getApplicationContext());
//            String result = HttpManager.getData(context.getResources().getString(R.string.serviceUrl) + "/allrequests/"+ StaticClass.getUserID());
//            return result;
//        }
//
//
//        protected void onPostExecute(String result) {
//            System.out.println("Result - " + result);
//            Gson gson = new Gson();
//            final List<Model_All_req> userList = gson.fromJson(result, new TypeToken<List<Model_All_req>>() {
//            }.getType());
//
//            if (TextUtils.isEmpty(result)) {
//
//            } else {
//                if (userList != null && userList.size() > 0) {
//                    System.out.println("sddsd" + userList.get(0).toString());
//
//                    All_Requests_Adapters adapter = new All_Requests_Adapters(context, R.layout.fragment_entry_page_2, userList);
//                    listV.setAdapter(adapter);
//                }
//
//            }
//        }
//    }
}


