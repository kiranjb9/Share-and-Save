package com.example.kiran.carpool.Riders_Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiran.carpool.R;
import com.example.kiran.carpool.Util.Models.Agreed_Model;
import com.example.kiran.carpool.Util.Models.RiderPosts;

import java.util.List;


public class Agreed_Adapter extends ArrayAdapter<Agreed_Model> {

    int vg;

    List<Agreed_Model> userList;

    Context context;

    public Agreed_Adapter(Context context, int activity, List<Agreed_Model> list){

        super(context,activity,list);

        this.context=context;

        this.vg=activity;

        this.userList=list;


    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(vg, parent, false);
        Agreed_Model p = userList.get(position);

        TextView Fname=itemView.findViewById(R.id.txt1);
        TextView dateTime = itemView.findViewById(R.id.txt2);


        try {

            Fname.setText("You have a ride with " + p.getFname() + " " + p.getLname() );
            dateTime.setText("on "+p.getDate() + "  at" + p.getTime() );




        } catch (Exception e) {
            System.out.println("ERROR"+e);
            e.printStackTrace();
        }



        return itemView;

    }

}


