package com.example.kiran.carpool.RideSeeker;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kiran.carpool.Login;
import com.example.kiran.carpool.R;
import com.example.kiran.carpool.RideSeeker.Adapters.SearchListAdapter;
import com.example.kiran.carpool.StartPage;
import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.example.kiran.carpool.maps.MapActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class Fragment1 extends Fragment  {
    Button search;
    Spinner spinner;
    Button i;
    Button req;
    String req_for_post;
    String result,postid;
    List<RiderPosts> userList;
    private Button launch_button;
    ListView listV;
    RiderPosts ride = new RiderPosts();
    String source, dest, sourceLat,sourcelng, destLat,destLong, DATE, TIME, SEATS, PREF;
    boolean EdittextCheck;
    Context context = getActivity();
    EditText S, D;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    final private static int DIALOG_LOGIN = 1;
    private String num_seats;
    private LayoutInflater inflater;


    public Fragment1() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list2, container, false);
        // Inflate the layout for this fragment
        req = view.findViewById(R.id.req);
        listV = view.findViewById(R.id.simpleListView2);
        context = getActivity();

        //draw path



        //destination auto complete
        D = (EditText) view.findViewById(R.id.editText_Destination);
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EdittextCheck = true;
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {

                }
            }
        });


        //Source Autocomplete
        S = (EditText) view.findViewById(R.id.editText_Source);
        S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EdittextCheck = false;
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(getActivity());

                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);

                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {

                }
            }
        });


        //Search Button
        search = (Button) view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling Async
                if (check()) {
                    RegisterUser registerUser = new RegisterUser();
                    registerUser.execute();


                }
            }

            private Boolean check() {
                ride.setSoure_lat(sourceLat);
                ride.setSoure_long(sourcelng);
                ride.setDest_lat(destLat);
                ride.setDest_long(destLong);
                ride.setSource(source);

                ride.setDestination(dest);
                return true;
            }

        });



        //returning view



        return view;


    }


    public void getMap(View v) {
        Intent myIntent = new Intent(v.getContext(), MapActivity.class);
        startActivity(myIntent);
    }


    //setting edit text of source & dest
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);

                if (EdittextCheck == false) {
                    S.setText(place.getName().toString());
                    if (place != null) {
                        LatLng latLng = place.getLatLng();
                        sourceLat = String.valueOf(latLng.latitude);
                        sourcelng = String.valueOf(latLng.longitude);
                        source = place.getAddress().toString();
                    }


                }

                if (EdittextCheck == true) {
                    D.setText(place.getName().toString());
                    if (place != null) {
                        LatLng latLng = place.getLatLng();
                        destLat = String.valueOf(latLng.latitude);
                        destLong = String.valueOf(latLng.longitude);
                        dest = place.getAddress().toString();
                    }
                }

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                System.out.println(status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
//
//    public void request(final String n) {
//
//        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                int num=listV.getAdapter().getCount();
//                System.out.println("NUMBER OF ITEMS :"+num);
//                for(int i=0;i<=num;i++){
//                    if (position == i) {
//                       postid= userList.get(i).get_id();
//                       req_for_post= userList.get(i).getRide_postedBy().get_id();
//                       num_seats=n;
//                        if (check()) {
//                            RegisterUser registerUser = new RegisterUser();
//                            registerUser.execute();
//
//
//                        }
//
//
//
//                    }
//                }
//
//            }
//
//            private boolean check() {
//
//
//                return  true;
//            }
//
//
//        });
//    }



    //Async TAsk
    class RegisterUser extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(context);
            Gson gson = new Gson();
            String userJson = gson.toJson(ride, RiderPosts.class);
            System.out.println("User Json - " + userJson);
            result = HttpManager.postData(getResources().getString(R.string.serviceUrl)+"/search",userJson);
            System.out.println("Result - " + result);
            return result;
        }

        protected void onPostExecute(String result) {

                System.out.println("Result - " + result);
                Gson gson = new Gson();
                userList = gson.fromJson(result, new TypeToken<List<RiderPosts>>() {
                }.getType());

                if (TextUtils.isEmpty(result)) {

                }

                else {
                    if (userList != null && userList.size() > 0) {
                        System.out.println("USER LIST CONTENT " + userList.get(0).toString());


                        SearchListAdapter adapter = new SearchListAdapter(context, R.layout.fragment_fragment1, userList);
                        listV.setAdapter(adapter);

                    }

                }


        }


    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("SEARCH RIDES");
    }

//    class DialogReq extends AsyncTask<Void, Void, String> {
//
//        @Override
//        protected String doInBackground(Void... params) {
//            HttpManager httpManager = new HttpManager(getApplicationContext());
//            Gson gson = new Gson();
//            String userJson = gson.toJson(ride, RiderPosts.class);
//            System.out.println("User Json - " + userJson);
//            result = HttpManager.getData(getResources().getString(R.string.serviceUrl)+"/requesting/"+postid+"/"+req_for_post+"/"+num_seats);
//            System.out.println("Result - " + result);
//
//            return result;
//        }
//
//        protected void onPostExecute(String result) {
//            System.out.println("Result - " + result);
//            Gson gson = new Gson();
//            userList = gson.fromJson(result, new TypeToken<List<RiderPosts>>() {
//            }.getType());
//
//            if (TextUtils.isEmpty(result)) {
//                Toast.makeText(getContext(),"EMPTY RESULT",Toast.LENGTH_SHORT).show();
//            }
//
//            else {
//                if (userList != null && userList.size() > 0) {
//                    System.out.println("USER LIST CONTENT " + userList.get(0).toString());
//
//
//                    SearchListAdapter adapter = new SearchListAdapter(context, R.layout.fragment_fragment1, userList);
//                    listV.setAdapter(adapter);
//
//
//
//                }
//
//            }
//
//        }
//
//
//    }


}



