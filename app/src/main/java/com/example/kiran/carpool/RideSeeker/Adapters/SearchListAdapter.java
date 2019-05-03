package com.example.kiran.carpool.RideSeeker.Adapters;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiran.carpool.R;
import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.Model_All_req;
import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.example.kiran.carpool.Util.Models.StaticClass;
import com.example.kiran.carpool.Util.Models.User;
import com.example.kiran.carpool.maps.MapActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;
import static com.facebook.FacebookSdk.getApplicationContext;


public class SearchListAdapter extends ArrayAdapter<RiderPosts> {
    RiderPosts ride;
    String num_seats;
    int vg;
    String req_for_post;
    String result,postid;
    Button req;
    List age;
    ImageButton i;

    Spinner spinner;
    List<RiderPosts> userList;
    ArrayAdapter<CharSequence> adapter1;
int Position;
    Context context;

    public SearchListAdapter(Context context, int activity, List<RiderPosts> list){

        super(context,activity,list);

        this.context=context;

        this.vg=activity;

        this.userList=list;
        System.out.println("entered ListAdapter");

    }



    public View getView(int position, View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(vg, parent, false);
        RiderPosts p = userList.get(position);
        req = (Button) itemView.findViewById(R.id.req);
        i = (ImageButton) itemView.findViewById(R.id.map1);



        TextView Fname=itemView.findViewById(R.id.Searchname);
        TextView phno = itemView.findViewById(R.id.Searchphno);
        TextView source = itemView.findViewById(R.id.Searchsource);
        TextView Datetime = itemView.findViewById(R.id.Searchdatetime);
        TextView dest = itemView.findViewById(R.id.Searchdest);
        TextView seats = itemView.findViewById(R.id.noofseats);
        TextView money = itemView.findViewById(R.id.money);
        try {
            String id =p.getRide_postedBy().get_id();
            System.out.println("ID///////////////////////////" + id);
            seats.setText(p.getSeats());
            Fname.setText(p.getRide_postedBy().getFname() + " " + p.getRide_postedBy().getLname());
            phno.setText(p.getRide_postedBy().getMobilenumber());
            source.setText(p.getSource());
            dest.setText(p.getDestination());
            Datetime.setText(p.getDate() + "\n" + p.getTime());
            money.setText(p.getAmount());





        } catch (Exception e) {
            System.out.println("ERROR"+e);
            e.printStackTrace();
        }
        req.setTag(position);
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
//                int position = (Integer) v.getTag();
//                RiderPosts user = getItem(position);

                View mview = inflater.inflate(R.layout.dialogue_layout,null);
//              int  s = Integer.parseInt(user.getSeats());
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(getContext());
//                age = new ArrayList<>();
//                for (int i = 1; i <= s; i++) {
//                    age.add(i);
//                }
//
////                ArrayAdapter<Integer> sa= new ArrayAdapter<Integer>(
////                        context, android.R.layout.simple_spinner_item, age);
////                sa.setDropDownViewResource(
////                        android.R.layout.simple_spinner_dropdown_item );
////                spinner.setAdapter(sa);
////                adapter1.
////                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////                spinner.setAdapter(age);
//                spinner = mview.findViewById(R.id.req_sp1);
//                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
//
//                        System.out.println("pos1" + parent.getSelectedItemId());
//                        Toast.makeText(context, parent.getItemAtPosition(pos) + " slected ", ((Toast.LENGTH_LONG)/2)).show();
//
//                    }
//
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
//


                mbuilder.setTitle("Number of seats you wanna request");
                spinner = (Spinner) mview.findViewById(R.id.req_sp1);

                mbuilder.setPositiveButton("Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = (Integer) v.getTag();
                        // Access the row position here to get the correct data item
                          ride  = getItem(position);
                        StaticClass sc =new StaticClass();
                        postid= ride.get_id();
                        req_for_post= sc.getUserID();

                        if (!spinner.getSelectedItem().toString().equalsIgnoreCase(("SELECT"))) {
                            num_seats=spinner.getSelectedItem().toString();
                            Toast.makeText(getContext(),spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                            DialogReq d = new DialogReq();
                            d.execute();
                            req.setText("SENT");
                            req.setEnabled(false);
                            dialog.dismiss();


                        }
                    }
                });
                mbuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();
            }
        });
        i.setTag(position);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = (Integer) v.getTag();
                // Access the row position here to get the correct data item
                ride  = getItem(position);

                Intent myIntent = new Intent(context, MapActivity.class);
                Bundle extras = new Bundle();

                extras.putDouble("s1", Double.parseDouble(ride.getSoure_lat()));

                extras.putDouble("s2", Double.parseDouble(ride.getSoure_long()));

                extras.putDouble("d1", Double.parseDouble(ride.getDest_lat()));

                extras.putDouble("d2", Double.parseDouble(ride.getDest_long()));

                myIntent.putExtras(extras);
                System.out.println();
                context.startActivity(myIntent);
            }
        });
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


