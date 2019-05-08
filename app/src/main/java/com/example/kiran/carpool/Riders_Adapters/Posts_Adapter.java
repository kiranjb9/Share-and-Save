package com.example.kiran.carpool.Riders_Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiran.carpool.Nav;
import com.example.kiran.carpool.R;
import com.example.kiran.carpool.Util.Models.RiderPosts;

import java.io.InputStream;
import java.util.List;


public class Posts_Adapter extends ArrayAdapter<RiderPosts> {
    ImageView mImageView;
    int vg;
    SharedPreferences pref;
    ImageView image;


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

        TextView source=itemView.findViewById(R.id.Searchsource);
        TextView dest = itemView.findViewById(R.id.Searchdest);


        TextView dateTime = itemView.findViewById(R.id.datetime);

        try {

            source.setText(p.getSourceName());
            dest.setText(p.getDestinationName());
            dateTime.setText("Date & Time \n"+p.getDate() + "\n" + p.getTime());

             image= itemView.findViewById(R.id.info);
            image.setClickable(true);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    View mview = inflater.inflate(R.layout.dialogue_post_info,null);
                    AlertDialog.Builder mbuilder = new AlertDialog.Builder(getContext());
                    mbuilder.setTitle("Complete Information");
                    TextView sourceName = mview.findViewById(R.id.Sname);
                    TextView destName = mview.findViewById(R.id.Dname);

                    TextView sourceAdress = mview.findViewById(R.id.Sadress);

                    TextView destAdress = mview.findViewById(R.id.Dadress);

                    TextView Date = mview.findViewById(R.id.date);

                    TextView Time = mview.findViewById(R.id.time);

                    TextView amt = mview.findViewById(R.id.amount);

                    TextView duration  = mview.findViewById(R.id.duration);

                    TextView Distance = mview.findViewById(R.id.distance);

                    TextView seats= mview.findViewById(R.id.seats);


                    sourceName.setText(p.getSourceName());
                    destName.setText(p.getDestinationName());
                    sourceAdress.setText(p.getSource());
                    destAdress.setText(p.getDestination());
                    Date.setText(p.getDate());
                    Time.setText(p.getTime());
                    amt.setText(p.getAmount());
                    duration.setText(p.getDuration());
                    Distance.setText(p.getDistance());
                    seats.setText(p.getSeats());


                    mbuilder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    mbuilder.setView(mview);
                    AlertDialog dialog=mbuilder.create();
                    dialog.show();
                }
            });


        } catch (Exception e) {
            System.out.println("ERROR"+e);
            e.printStackTrace();
        }



        return itemView;

    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}


