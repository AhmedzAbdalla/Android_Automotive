package com.example.twoactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static final String PHONE = "PHONE";
    public static final String MESSAGE = "MESSAGE";
    private EditText editMessage;
    private EditText editPhone;
    private Button btnNext;
    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        editMessage = findViewById(R.id.editMessage);
        editPhone = findViewById(R.id.editPhone);
        btnNext = findViewById(R.id.btnNext);
        btnClose = findViewById(R.id.btnclose);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outintent = new Intent(MainActivity.this ,MainActivity2.class );
                String phone = editPhone.getText().toString();
                String msg = editMessage.getText().toString();

                outintent.putExtra(MainActivity.PHONE,phone);
                outintent.putExtra(MainActivity.MESSAGE,msg);
                startActivity(outintent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClose(View view) {
        finish();
    }
}