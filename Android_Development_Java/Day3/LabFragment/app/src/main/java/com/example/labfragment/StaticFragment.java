package com.example.labfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StaticFragment extends Fragment {

    public int counter = 0;
    Button btncount;
    communicator comm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm =(communicator) getActivity();

        if (savedInstanceState != null) {
            // Restore the counter value after a rotation
            counter = savedInstanceState.getInt("counter", 0);
        }

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_static, container, false);
    }


    public void onViewCreated(@NonNull View view,
                                   @Nullable Bundle savedInstanceState )
    {
        btncount = view.findViewById(R.id.btnCount);
        btncount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;

                comm.changeData(String.valueOf(counter));
            }
        });
    }

}