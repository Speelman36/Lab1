package com.martin.lab1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {

    protected static final String ListItemsActivity = "ListItemsActivity";
    ImageButton imagebtn;
    Switch switchbtn;
    CheckBox checkBox;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imagebtn = (ImageButton) findViewById(R.id.imageButton);
        switchbtn = (Switch) findViewById(R.id.switch1);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT; //= Toast.LENGTH_LONG if Offs
                Toast toast;
                try{
                    if(switchbtn.isChecked()){
                        text = "Switch is On";// "Switch is Off";
                    }
                    else {
                        text = "Switch is Off";
                    }
                    toast = Toast.makeText(ListItemsActivity.this , text, duration); //this is the ListActivity
                    toast.show(); //display your message box
                }catch (Exception e){
                    Log.i("Switch crashed", e.getMessage());
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 2. Chain together various setter methods to set the dialog characteristics
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("Do you wish to quit?") //Add a dialog message to strings.xml

                        .setTitle("Quit?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Response", "My information to share");
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        })
                        .show();


            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
  /*      fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
            Bundle bundle = data.getExtras();
            Bitmap imageBitMap = (Bitmap) bundle.get("data");
            imageButton.setImageBitmap(imageBitMap);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ListItemsActivity, "In onResume()");

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ListItemsActivity, "In onStart()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ListItemsActivity, "In onPause()");
    }

    @Override protected void onStop(){
        super.onStop();
        Log.i(ListItemsActivity, "In onStop()");
    }

    @Override protected void onDestroy(){
        super.onDestroy();
        Log.i(ListItemsActivity, "In onDestroy()");
    }

}
