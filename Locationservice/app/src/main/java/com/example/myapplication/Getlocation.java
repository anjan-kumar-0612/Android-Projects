package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Getlocation extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5;
    Button btn1;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getlocation);
        tv1=findViewById(R.id.textView1);
        tv2 =findViewById(R.id.textView2);
        tv3=findViewById(R.id.textView3);
        tv4=findViewById(R.id.textView4);
        tv5=findViewById(R.id.textView5);
        btn1=findViewById(R.id.btn1);

        //initialise fusedLocation providerclient
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check permission
                if(ActivityCompat.checkSelfPermission(Getlocation.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    //when permission granted
                    findLocation();

                }
                else{
                    //when permission denied
                    ActivityCompat.requestPermissions(Getlocation.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }
            }
        });
    }


    private void findLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location=task.getResult();
                if(location!=null){

                    try {
                        Geocoder geocoder=new Geocoder(Getlocation.this, Locale.getDefault());
                        //initialise address list
                        List<Address> addresses=geocoder.getFromLocation(
                                location.getLatitude(),location.getLongitude(),1
                        );

                        //set latitude on text
                        tv1.setText(Html.fromHtml(
                                "<font color ='#6200EE'><b>Latitude:</b><br></font>" +addresses.get(0).getLatitude()
                        ));
                        //set longitude on text
                        tv2.setText(Html.fromHtml(
                                "<font color ='#6200EE'><b>Longitude:</b><br></font>" +addresses.get(0).getLongitude()
                        ));
                        //set country
                        tv3.setText(Html.fromHtml(
                                "<font color ='#6200EE'><b>Country:</b><br></font>" +addresses.get(0).getCountryName()
                        ));
                        //set Locality
                        tv4.setText(Html.fromHtml(
                                "<font color ='#6200EE'><b>Locality:</b><br></font>" +addresses.get(0).getLocality()
                        ));
                        //set addressline
                        tv5.setText(Html.fromHtml(
                                "<font color ='#6200EE'><b>Address:</b><br></font>" +addresses.get(0).getAddressLine(0)
                        ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}