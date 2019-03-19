package com.example.kiran.carpool.Util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kiran.carpool.R;

public class MyCustomDialog extends DialogFragment {
    private static final String TAG = "MyCustomDialog";
    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    //widgets
    private Spinner spinner;
    private Button req, cancel;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogue_layout, container, false);

        spinner = view.findViewById(R.id.req_sp1);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDialog().dismiss();
            }
        });

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input.");
                String input = spinner.getSelectedItem().toString();
                if(!input.equals("")){

                    System.out.println(input+"             INPUT////////////////");
                }


                getDialog().dismiss();
            }
        });

        return view;
    }


}
