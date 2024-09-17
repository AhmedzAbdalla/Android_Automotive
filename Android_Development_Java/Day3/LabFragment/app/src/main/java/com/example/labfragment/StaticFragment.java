package com.example.labfragment;

import static androidx.fragment.app.FragmentManager.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StaticFragment extends Fragment {

    public int counter = 0;
    public String mystrcounter ;
    Button btncount;
    communicator comm;

    private static final String TAG = "StaticFragment";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm =(communicator) getActivity();

        if (savedInstanceState != null) {
            // Restore the counter value after a rotation

            mystrcounter= savedInstanceState.getString("counter");
            counter = Integer.valueOf(mystrcounter);
            //strcounter = savedInstanceState.getString("counter");
            Log.d(TAG, "onCreat||counter"+ mystrcounter);
            Log.d(TAG, "onCreat%%counter"+ counter);
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
                Log.d(TAG, "onViewCreated counter"+ counter);
                comm.respond(String.valueOf(counter));
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
         outState.putString("counter", String.valueOf(counter));
        outState.putString("counter", String.valueOf(counter));

        String strcounter = outState.getString("counter");
        Log.d(TAG, "onSaveInstanceState counter>> "+ strcounter);
    }

}