package com.martin.lab1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected static final String LoginActivity = "loginActivity";

    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = (EditText) findViewById(R.id.loginBox);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        Log.i(LoginActivity, "In onStart()");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(LoginActivity, "In onResume()");

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(LoginActivity, "In onStart()");

        SharedPreferences sharedPref = getSharedPreferences("DefaultEmail", Context.MODE_PRIVATE);
        email.setText(sharedPref.getString("DefaultEmail", "email@domain.com"));

    }
    public void onclick(View v){
        SharedPreferences pref = getSharedPreferences("DefaultEmail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editer = pref.edit();

        editer.putString("DefaultEmail", email.getText().toString());
        editer.commit();
        Intent intent = new Intent(LoginActivity.this, StartActivity.class);
        startActivityForResult(intent, 5);
    }

    protected void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == 5){
            Log.i(LoginActivity, "Return to startActivity.onActionResult");
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(LoginActivity, "In onPause()");
    }

    @Override protected void onStop(){
        super.onStop();
        Log.i(LoginActivity, "In onStop()");
    }

    @Override protected void onDestroy(){
        super.onDestroy();
        Log.i(LoginActivity, "In onDestroy()");
    }

}
