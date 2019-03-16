package com.example.kiran.carpool.RideSeeker.Adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiran.carpool.R;
import com.example.kiran.carpool.Util.Models.RiderPosts;

import java.util.List;


public class SearchListAdapter extends ArrayAdapter<RiderPosts> {

    int vg;

    List<RiderPosts> userList;


    Context context;

    public SearchListAdapter(Context context, int activity, List<RiderPosts> list){

        super(context,activity,list);

        this.context=context;

        this.vg=activity;

        this.userList=list;
        System.out.println("entered ListAdapter");

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("entered getView");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(vg, parent, false);
        RiderPosts p = userList.get(position);

        TextView Fname=itemView.findViewById(R.id.Searchname);
        TextView phno = itemView.findViewById(R.id.Searchphno);
        TextView source = itemView.findViewById(R.id.Searchsource);
        TextView Datetime = itemView.findViewById(R.id.Searchdatetime);
        TextView dest = itemView.findViewById(R.id.Searchdest);

        try {

            Fname.setText(p.getRide_postedBy().getFname() + " " + p.getRide_postedBy().getLname() );
            phno.setText(p.getRide_postedBy().getMobilenumber());
            source.setText(p.getSource());
            dest.setText(p.getDestination());
            Datetime.setText(p.getDate() + "\n" + p.getTime());


        } catch (Exception e) {
            System.out.println("ERROR"+e);
            e.printStackTrace();
        }



        return itemView;

    }

}


