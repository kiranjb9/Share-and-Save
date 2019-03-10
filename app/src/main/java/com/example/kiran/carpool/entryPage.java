package com.example.kiran.carpool;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

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
        b1=  view.findViewById(R.id.btn_PostRideOffer);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getContext(), PostRideOffers.class);
                startActivity(myintent);
            }
        });
        return  view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 1");
    }
}

