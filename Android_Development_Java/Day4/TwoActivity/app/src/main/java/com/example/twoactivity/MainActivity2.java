package com.example.twoactivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvPhone, tvMessage;
    private Button btnClose, btnSaveShared, btnReadShared, btnSaveInternal, btnReadInternal, btnSaveSQLite, btnReadSQLite;
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String PHONE_KEY = "phoneKey";
    private static final String MESSAGE_KEY = "messageKey";
    private static final String FILE_NAME = "internalStorage.txt";
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvPhone = findViewById(R.id.tvPhoneValue);
        tvMessage = findViewById(R.id.tvMessageValue);
        btnClose = findViewById(R.id.btnClose);
        btnSaveShared = findViewById(R.id.btnSaveToShared);
        btnReadShared = findViewById(R.id.btnReadFromShared);
        btnSaveInternal = findViewById(R.id.btnSaveToInternal);
        btnReadInternal = findViewById(R.id.btnReadFromInternal);
        btnSaveSQLite = findViewById(R.id.btnSaveToSQLite);
        btnReadSQLite = findViewById(R.id.btnReadFromSQLite);

        // Handle the intent from MainActivity
        Intent incomingIntent = getIntent();
        tvPhone.setText(incomingIntent.getStringExtra(MainActivity.PHONE));
        tvMessage.setText(incomingIntent.getStringExtra(MainActivity.MESSAGE));

        // Close Button
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize the SQLite helper
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        database = dbHelper.getWritableDatabase();

        // Shared Preferences - Save
        btnSaveShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PHONE_KEY, tvPhone.getText().toString());
                editor.putString(MESSAGE_KEY, tvMessage.getText().toString());
                editor.apply();
                tvPhone.setText("");
                tvMessage.setText("");
                Toast.makeText(MainActivity2.this, "Saved to Shared Preferences", Toast.LENGTH_SHORT).show();
            }
        });

        // Shared Preferences - Read
        btnReadShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                String phone = sharedPreferences.getString(PHONE_KEY, "");
                String message = sharedPreferences.getString(MESSAGE_KEY, "");
                tvPhone.setText(phone);
                tvMessage.setText(message);
                Toast.makeText(MainActivity2.this, "Read from Shared Preferences", Toast.LENGTH_SHORT).show();
            }
        });

        // Internal Storage - Save
        btnSaveInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = tvPhone.getText().toString() + "\n" + tvMessage.getText().toString();
                try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
                    fos.write(data.getBytes());
                    tvPhone.setText("");
                    tvMessage.setText("");
                    Toast.makeText(MainActivity2.this, "Saved to Internal Storage", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity2.this, "Error saving to Internal Storage", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Internal Storage - Read
        btnReadInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try (FileInputStream fis = openFileInput(FILE_NAME)) {
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    String[] data = new String(buffer).split("\n");
                    tvPhone.setText(data[0]);
                    tvMessage.setText(data[1]);
                    Toast.makeText(MainActivity2.this, "Read from Internal Storage", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity2.this, "Error reading from Internal Storage", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // SQLite - Save
        btnSaveSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(DataBaseHelper.COLUMN_PHONE, tvPhone.getText().toString());
                values.put(DataBaseHelper.COLUMN_MESSAGE, tvMessage.getText().toString());
                long result = database.insert(DataBaseHelper.TABLE_NAME, null, values);
                if (result == -1) {
                    Toast.makeText(MainActivity2.this, "Error saving to SQLite", Toast.LENGTH_SHORT).show();
                } else {
                    tvPhone.setText("");
                    tvMessage.setText("");
                    Toast.makeText(MainActivity2.this, "Saved to SQLite", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // SQLite - Read
        btnReadSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = database.query(DataBaseHelper.TABLE_NAME, null, null, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    String phone = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_PHONE));
                    String message = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_MESSAGE));
                    tvPhone.setText(phone);
                    tvMessage.setText(message);
                    cursor.close();
                    Toast.makeText(MainActivity2.this, "Read from SQLite", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "No data found in SQLite", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // SQLite Helper Class
    static class DataBaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "app_data.db";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_NAME = "user_data";
        private static final String COLUMN_PHONE = "phone";
        private static final String COLUMN_MESSAGE = "message";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_MESSAGE + " TEXT);";

        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}

