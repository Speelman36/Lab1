package com.martin.lab1;//package com.martin.lab1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    protected static final String StartActivity = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.startButton);
        Button button =  (Button) findViewById(R.id.startButton);
        Button chatbtn = (Button) findViewById(R.id.Start_Chat);
        Button weatherbtn = (Button) findViewById(R.id.weatherButton);
        Button toolbar = (Button) findViewById(R.id.toolbar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 5);
            }
        });

        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(StartActivity, "User clicked Start Chat");

                Intent intent = new Intent(StartActivity.this, MessageListActivity.class);
                startActivity(intent);

            }
        });

        weatherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(StartActivity, "User clicked Check Forecast");

                Intent intent = new Intent(StartActivity.this, WeatherForecast.class);
                startActivity(intent);
            }
        });

        toolbar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(StartActivity.this, TestToolbar.class);
                startActivity(intent);
            }
        });
        Log.i(StartActivity, "In onCreate()");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if ((requestCode == 5) && resultCode == Activity.RESULT_OK){
            Log.i(StartActivity, "returned to startActivity");
            int duration;
            Toast toast;
            try{
                String messagePassed = data.getStringExtra("Response");
                duration = Toast.LENGTH_LONG;
                toast = Toast.makeText(StartActivity.this, messagePassed, duration);
            }catch (Exception e){
                Log.d("Crash", e.getMessage());
            }
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(StartActivity, "In onResume()");

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(StartActivity, "In onStart()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(StartActivity, "In onPause()");
    }

    @Override protected void onStop(){
        super.onStop();
        Log.i(StartActivity, "In onStop()");
    }

    @Override protected void onDestroy(){
        super.onDestroy();
        Log.i(StartActivity, "In onDestroy()");
    }
}