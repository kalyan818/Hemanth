package com.example.kalya.hemanth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity1 extends AppCompatActivity implements ImageAdapter1.OnItemClickListner{
    private RecyclerView mRecyclerView;
    private ImageAdapter1 mAdapter,mAdapter1;

    private ProgressBar mProgressCircle;
    private ImageView img;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        img = (ImageView)findViewById(R.id.chatbox);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  =  new Intent(ImagesActivity1.this,Main3Activity.class);
                startActivity(i);
            }
        });
        String data =  getIntent().getStringExtra("name");
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
        mProgressCircle = (ProgressBar)findViewById(R.id.progress_circle);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();
            mDatabaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hemanth-5993c.firebaseio.com/");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }
                mAdapter = new ImageAdapter1(ImagesActivity1.this,mUploads);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListner(ImagesActivity1.this);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImagesActivity1.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position, String s) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(s));
        startActivity(intent);
    }

    @Override
    public void onWhatEverClicked(int position) {
        Toast.makeText(this,"on what ever clicked" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Toast.makeText(this,"on delete click"+position, Toast.LENGTH_SHORT).show();
    }
}
