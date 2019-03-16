package com.example.kiran.carpool.Util;

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


public class Posts_Adapter extends ArrayAdapter<RiderPosts> {
    ImageView mImageView;
    int vg;

    List<RiderPosts> userList;

    Context context;

    public Posts_Adapter(Context context, int activity, List<RiderPosts> list){

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

        TextView Fname=itemView.findViewById(R.id.ListFname);
        TextView RBG = itemView.findViewById(R.id.ListBloodG);
        TextView place = itemView.findViewById(R.id.Listplace);

        TextView dateTime = itemView.findViewById(R.id.datetime);

        try {
            mImageView = convertView.findViewById(R.id.girl);
//            if(p.getPostedBy().getGender()=="Female"){
//                mImageView.setImageResource(R.drawable.girl);
//                System.out.println(p.getPostedBy().getGender());
//            }
//            if(p.getPostedBy().getGender()=="male") {
//                mImageView.setImageResource(R.drawable.boy);
//                System.out.println(p.getPostedBy().getGender());
//            }
            Fname.setText(p.getRide_postedBy().getFname() + " " + p.getRide_postedBy().getLname() + "\n" + p.getRide_postedBy().getMobilenumber());
            RBG.setText(p.getSource());
            place.setText(p.getDestination());

            dateTime.setText(p.getDate() + "  " + p.getTime());




        } catch (Exception e) {
            System.out.println("ERROR"+e);
            e.printStackTrace();
        }



        return itemView;

    }

}


