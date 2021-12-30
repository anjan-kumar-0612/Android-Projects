package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText source,destination;
Button btnfind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        source=findViewById(R.id.source);
        destination=findViewById(R.id.destination);
        btnfind=findViewById(R.id.btnfind);
        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get value form source and destination
                String ssource=source.getText().toString().trim();
                String sdestination=destination.getText().toString().trim();
                //check condition
                if(ssource.equals("") && sdestination.equals("")){
                    //when both value blank
                    Toast.makeText(getApplicationContext(),"enter both destination",Toast.LENGTH_SHORT).show();
                }
                else{
                    //when both value filled
                    //display track
                    DisplayTrack(ssource,sdestination);
                }
            }
        });
    }

    private void DisplayTrack(String ssource, String sdestination) {
        //if the device does not have map install map then redirect to play store
        try{
            //when google map is installed
            //initialize uri
            Uri uri=Uri.parse("https://www.google.co.in/maps/dir/" + ssource + "/" + sdestination);
            //initialize intent with action view
            Intent intent =new  Intent(Intent.ACTION_VIEW,uri);
            //set package
            intent.setPackage("com.google.android.apps.maps");
            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //start Activity
            startActivity(intent);
        }
        catch (ActivityNotFoundException e){
            //when google map is not installed
            //initalize uri
            Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.google.apps.maps");
            //initialize intent with action view
            Intent intent=new Intent(Intent.ACTION_VIEW);
            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //start activity
            startActivity(intent);

        }

    }
}