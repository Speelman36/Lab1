package com.martin.lab1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {

    protected static final String StartActivity = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.startButton);
        Button button =  (Button) findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 5);


            }
        });



        Log.i(StartActivity, "In onCreate()");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == Activity.RESULT_OK){
            String messagePassed = data.getStringExtra("Response");
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
