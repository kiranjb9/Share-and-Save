package com.example.kiran.carpool.Riders_Adapters;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiran.carpool.R;
import com.example.kiran.carpool.Util.HttpManager;

import com.example.kiran.carpool.Util.Models.Model_All_req;
import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.google.gson.Gson;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class All_Requests_Adapters extends ArrayAdapter<Model_All_req>{
    Model_All_req ride;
    String num_seats;
    int vg;
    String req_for_post;
    String result,postid;
    String user_id;
    Button Delete,Accept;
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
        final View itemView = inflater.inflate(vg, parent, false);

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
            req_txt2.setText( "Post" );


        } catch (Exception e) {


            System.out.println("ERROR" + e);
            e.printStackTrace();
        }

        Delete.setTag(position);

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = (Integer) itemView.getTag();
                Model_All_req user = getItem(position);

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
            result = HttpManager.getData(context.getResources().getString(R.string.serviceUrl)+"/Accept/"+user_id+"/"+user_who_sent_id+"/"+obj_id);
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
                    Toast.makeText(getApplicationContext(),"REQUEST SENT",Toast.LENGTH_SHORT).show();



                }

            }

        }


    }

}
