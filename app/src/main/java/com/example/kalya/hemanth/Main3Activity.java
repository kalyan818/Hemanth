package com.example.kalya.hemanth;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {
TextView sirname,sirname1;
Button btn1,btn2;
String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        sirname = (TextView)findViewById(R.id.sirname);
        sirname1 = (TextView)findViewById(R.id.sirname1);
        FirebaseAuth mAuth;
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main3Activity.this,MapsActivity.class);
                startActivity(i);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hemanth-5993c.firebaseio.com/2/sir/abc/");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        value = dataSnapshot.getValue().toString();
                        sirname.setText(value);
                       /* Intent i = new Intent(Main3Activity.this,MapsActivity.class);
                        startActivity(i);*/
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hemanth-5993c.firebaseio.com/2/sir/xyz/");
                ref.addValueEventListener(new ValueEventListener() {
                    String lat="";
                    String lon = "";
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String mn = dataSnapshot.child("lat").getValue().toString();
                        String mn1 = dataSnapshot.child("lon").getValue().toString();
                        Intent i = new Intent(Main3Activity.this,MapsActivity.class);
                        i.putExtra("lat",mn);
                        i.putExtra("lon",mn1);
                        startActivity(i);
                        /*Toast.makeText(Main3Activity.this,mn,Toast.LENGTH_LONG).show();
                        if (mn.equals("none"))
                        {
                            Toast.makeText(Main3Activity.this,mn,Toast.LENGTH_LONG).show();
                        }else {
                            lat= mn.substring(0,11);
                            lon= mn.substring(12,23);
                            Toast.makeText(Main3Activity.this,lat,Toast.LENGTH_LONG).show();
                            Toast.makeText(Main3Activity.this,lon,Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Main3Activity.this,MapsActivity.class);
                            i.putExtra("lat",lat);
                            i.putExtra("lon",lon);
                            startActivity(i);
                            //Toast.makeText(MapActivity.this,lon,Toast.LENGTH_LONG).show();
                            //float lat1 = Float.parseFloat(lat);
                            //float lon1 = Float.parseFloat(lon);
                            //geoLocate(lat1,lon1);
                        }*/
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
