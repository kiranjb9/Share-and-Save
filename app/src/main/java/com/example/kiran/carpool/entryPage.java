package com.example.kiran.carpool;


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
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.kiran.carpool.Util.HttpManager;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Belal on 18/09/16.
 */


public class entryPage extends Fragment {
    Button b1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_entry_page, container, false);

        //AUTOCOMPLETE API CODDE
//        final TextView txtVw = view.findViewById(R.id.placeName);
//
//        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
//                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//
//        view.findViewById(R.id.place_autocomplete_search_button).setVisibility(View.GONE);
////        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
////                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//
//        AutocompleteFilter filter= new AutocompleteFilter.Builder()
//                .setCountry("IN")
//                .build();
//
//        autocompleteFragment.setFilter(filter);
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                txtVw.setText(place.getAddress() + "\n" + place.getLatLng().toString());
//
//            }
//            @Override
//            public void onError(Status  status) {
//                txtVw.setText(status.toString());
//                System.out.println("ERROR IN PLACE" + status.getStatusMessage() + " CODE IS :  " + status.getStatusCode());
//            }
//        }); //AUTOCOMPLETE API CODE ENDS HERE
//
//
//
//        //OFFER RIDE BUTTON FUNCTIONALITY
//        final Button btn = view.findViewById(R.id.give_ride);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myintent = new Intent(view.getContext(), Ride_Details.class);
//                startActivity(myintent);
//            }
//        });
        b1 = view.findViewById(R.id.btn_PostRideOffer);
        b1.setText("Post Ride");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getContext(), PostRideOffers.class);
                startActivity(myintent);
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 1");
    }


    class RegisterUser extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            HttpManager httpManager = new HttpManager(getActivity());
            String result = HttpManager.getData(getResources().getString(R.string.serviceUrl) + "/getalldata");
            return result;
        }


        protected void onPostExecute(String result) {
            System.out.println("Result - " + result);
//            Gson gson = new Gson();
//            final List<Posts> userList = gson.fromJson(result, new TypeToken<List<Posts>>() {
//            }.getType());
//
//            if (TextUtils.isEmpty(result)) {
//
//            } else {
//                if (userList != null && userList.size() > 0) {
//                    System.out.println("USER LIST CONTENT " + userList.get(0).toString());
//
//                    ListAdapter adapter = new ListAdapter(context, R.layout.my_list1, userList);
//                    listV.setAdapter(adapter);
//                }
//
//            }
        }
    }


}