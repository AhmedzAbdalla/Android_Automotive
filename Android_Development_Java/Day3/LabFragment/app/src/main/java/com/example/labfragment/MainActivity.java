package com.example.labfragment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements communicator{


    int counter = 0;

    FragmentManager fmgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fmgr = getSupportFragmentManager();
        if (savedInstanceState == null) {
            // Restore the counter value after a rotation
            //counter = savedInstanceState.getInt("counter", 0);
            //==============
            DynamicFragment myfragment = new DynamicFragment();
            fmgr = getSupportFragmentManager();
            FragmentTransaction ftrans = fmgr.beginTransaction();
            ftrans.add(R.id.fragmentContainerView2,myfragment, "myfragment");
            ftrans.commit();
        }
        else
        {
            String savedCounterValue = savedInstanceState.getString("counter", "0");
            counter = new Integer(savedCounterValue);
            Fragment myfragment= fmgr.findFragmentById(R.id.fragmentContainerView2);
            if(myfragment instanceof DynamicFragment)
            {
                ((DynamicFragment) myfragment).changeData(savedCounterValue);
            }
        }



        //changeData("55");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the counter value into the Bundle
        Fragment myfragment = fmgr.findFragmentById(R.id.fragmentContainerView2);
        if (myfragment instanceof DynamicFragment) {
            // Assuming the counter value is stored in DynamicFragment as a String
            outState.putString("counter", String.valueOf(counter));
        }
    }

    public void respond(String Str)
    {
        fmgr = getSupportFragmentManager();
        Fragment myfragment= fmgr.findFragmentById(R.id.fragmentContainerView2);
        if(myfragment instanceof DynamicFragment)
        {
            ((DynamicFragment) myfragment).changeData(Str);
        }

    }

}