package com.example.kalya.hemanth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ChatBoc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_boc);
        String valu = getIntent().getStringExtra("key").toString();
        Toast.makeText(this,valu,Toast.LENGTH_LONG).show();
    }
}
