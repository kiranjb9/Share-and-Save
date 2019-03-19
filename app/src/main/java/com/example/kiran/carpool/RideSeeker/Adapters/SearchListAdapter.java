package com.example.kiran.carpool.RideSeeker.Adapters;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiran.carpool.R;
import com.example.kiran.carpool.Util.Models.RiderPosts;

import java.util.List;


public class SearchListAdapter extends ArrayAdapter<RiderPosts> {

    int vg;
    Button req,request,cancel;
    Spinner spinner;
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
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View itemView = inflater.inflate(vg, parent, false);
        RiderPosts p = userList.get(position);
        req = (Button) itemView.findViewById(R.id.req);

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

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(getContext());
                View mview = inflater.inflate(R.layout.dialogue_layout,null);
                mbuilder.setTitle("Nimber of seats you wanna request");
                spinner = (Spinner) mview.findViewById(R.id.req_sp1);
                mbuilder.setPositiveButton("Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!spinner.getSelectedItem().toString().equalsIgnoreCase(("SELECT"))) {
                            Toast.makeText(getContext(),spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
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

        return itemView;

    }

}


