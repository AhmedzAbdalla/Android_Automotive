package com.example.labfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DynamicFragment extends Fragment {

    //private String counter = "0";  // Keep track of the counter
    private String counter = "0";
    private String temp = "0";
    TextView CounterValue;
    private static final String TAG = "DynamicFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CounterValue = view.findViewById(R.id.editCounter);

        //CounterValue.setText(savedInstanceState.getString("counter"));
        // Restore the counter value if savedInstanceState is not null
        if (savedInstanceState != null) {

            temp = savedInstanceState.getString("counterval");
        }
        if (CounterValue != null) {
            CounterValue.setText(temp);
        }
    }
    void changeData(String Str)
    {
        if (CounterValue != null) {
            CounterValue.setText(Str);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String temp = CounterValue.getText().toString();
        outState.putString("counterval", temp);

    }

}

interface communicator
{
    public void respond(String Str);
}



