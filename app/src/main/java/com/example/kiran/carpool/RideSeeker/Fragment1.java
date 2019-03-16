package com.example.kiran.carpool.RideSeeker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kiran.carpool.Nav;
import com.example.kiran.carpool.PostRideOffers;
import com.example.kiran.carpool.R;
import com.example.kiran.carpool.RideSeeker.Adapters.SearchListAdapter;
import com.example.kiran.carpool.Util.HttpManager;
import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.example.kiran.carpool.Util.Posts_Adapter;
import com.example.kiran.carpool.entryPage;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class Fragment1 extends Fragment {
    Button search;
    ListView listV;
    RiderPosts ride = new RiderPosts();
    String source, dest, sourceLATlng, destLatlong, DATE, TIME, SEATS, PREF;
    boolean EdittextCheck;
    Context context = getActivity();
    EditText S, D;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    public Fragment1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list2, container, false);
        listV = view.findViewById(R.id.simpleListView2);
        context =getActivity();
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
                ride.setSoure_latlong(sourceLATlng);
                ride.setDest_latlong(destLatlong);
                return true;
            }

        });


        //returning view
        return view;
    }

    //setting edit text of source & dest
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);

                if (EdittextCheck == false) {
                    S.setText(place.getName().toString());
                    source = place.getAddress().toString();
                    sourceLATlng = place.getLatLng().toString();
                }

                if (EdittextCheck == true) {
                    D.setText(place.getName().toString());
                    dest = place.getAddress().toString();
                    destLatlong = place.getLatLng().toString();
                    System.out.println("Destination 5455555555555555555555555555555555" + dest + "       " + destLatlong);

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


        //Async TAsk
    class RegisterUser extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(context);
            Gson gson = new Gson();
            String userJson = gson.toJson(ride, RiderPosts.class);
            System.out.println("User Json - " + userJson);
            String result = HttpManager.postData(getResources().getString(R.string.serviceUrl)+"/search",userJson);
            System.out.println("Result - " + result);
            return result;
        }

        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
            Gson gson = new Gson();
            final List<RiderPosts> userList = gson.fromJson(result, new TypeToken<List<RiderPosts>>() {
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
}
