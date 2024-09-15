package com.example.labfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DynamicFragment extends Fragment {

    //private String counter = "0";  // Keep track of the counter
    private String counter = "0";
    TextView CounterValue;


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

        // Restore the counter value if savedInstanceState is not null
        if (savedInstanceState != null) {
            counter = savedInstanceState.getString("counter", "0");
        }
        if (CounterValue != null) {
            CounterValue.setText(counter);
        }
    }
    void modifyCounter(String Str)
    {
        if (CounterValue != null) {
            CounterValue.setText(Str);
        }
    }


}

interface communicator
{
    public void changeData(String Str);
}