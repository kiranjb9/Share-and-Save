//package com.example.kiran.carpool.Util;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//import com.example.kiran.carpool.R;
//
//import java.util.List;
//public class Posts_Adapter {
//
//    ImageView mImageView;
//    int vg;
//
//    List<Posts> userList;
//
//    Context context;
//
//    public ListAdapter(Context context, int activity, List<Posts> list){
//
//        super(context,activity,list);
//
//        this.context=context;
//
//        this.vg=activity;
//
//        this.userList=list;
//        System.out.println("entered ListAdapter");
//
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        System.out.println("entered getView");
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View itemView = inflater.inflate(vg, parent, false);
//        Posts p = userList.get(position);
//
//        TextView Fname=itemView.findViewById(R.id.ListFname);
//        TextView RBG = itemView.findViewById(R.id.ListBloodG);
//        TextView place = itemView.findViewById(R.id.Listplace);
//
//        TextView dateTime = itemView.findViewById(R.id.datetime);
//
//        try {
//            mImageView = convertView.findViewById(R.id.girl);
//            if(p.getPostedBy().getGender()=="Female"){
//                mImageView.setImageResource(R.drawable.girl);
//                System.out.println(p.getPostedBy().getGender());
//            }
//            if(p.getPostedBy().getGender()=="male") {
//                mImageView.setImageResource(R.drawable.boy);
//                System.out.println(p.getPostedBy().getGender());
//            }
//            Fname.setText(p.getPostedBy().getFname() + " " + p.getPostedBy().getLname() + "\n" + p.getPostedBy().getMobilenumber());
//            RBG.setText(p.getReq_bloodG());
//            place.setText(p.getPlace());
//
//            dateTime.setText(p.getDate() + "  " + p.getTime());
//
//            //Email.setText(list.get(position).getString("email"));
//
//            //Branch.setText(list.get(position).getString("branch"));
//
//            //Sem.setText(list.get(position).getString("sem"));
//
//            // Course.setText(list.get(position).getString("course"));
//
//
//        } catch (Exception e) {
//            System.out.println("ERROR"+e);
//            e.printStackTrace();
//        }
//
//
//
//        return itemView;
//
//    }
//
//
//
//}
//
