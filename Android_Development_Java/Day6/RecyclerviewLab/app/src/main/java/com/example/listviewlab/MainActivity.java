package com.example.listviewlab;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView myRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);



        List<cake> input = Arrays.asList(
                new cake(R.drawable.aa, "CAke1", "cake1 desc"),
                new cake(R.drawable.cc, "CAke2", "cake2 desc"),
                new cake(R.drawable.dd, "CAke3", "cake3 desc"),
                new cake(R.drawable.ff, "CAke4", "cake4 desc"),
                new cake(R.drawable.ss, "CAke5", "cake5 desc"),
                new cake(R.drawable.vv, "CAke6", "cake6 desc"),
                new cake(R.drawable.xx, "CAke7", "cake7 desc"),
                new cake(R.drawable.dd, "CAke3", "cake3 desc"),
                new cake(R.drawable.ff, "CAke4", "cake4 desc"),
                new cake(R.drawable.ss, "CAke5", "cake5 desc"),
                new cake(R.drawable.vv, "CAke6", "cake6 desc"),
                new cake(R.drawable.xx, "CAke7", "cake7 desc")
        );
        //MyAdapter adp = new MyAdapter(this, input);
        //ConstraintLayout layoutManager = new ConstraintLayout(this);

        MyAdapter adp = new MyAdapter(this, input);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(adp);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}