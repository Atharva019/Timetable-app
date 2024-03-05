package com.example.ttgmp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class home_acitivity extends AppCompatActivity {
    Button get_str;
    //private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acitivity);
        Intent in = new Intent(getApplicationContext(),FullscreenActivity.class);
        startActivity(in);
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        mDatabase.child("users").child("email").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Toast.makeText(home_acitivity.this, "Error getting data", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(home_acitivity.this, ""+String.valueOf(task.getResult().getValue()), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        get_str = findViewById(R.id.start_btn);
//        get_str.setOnClickListener(v -> {
//            Intent in = new Intent(getApplicationContext(),FullscreenActivity.class);
//            startActivity(in);
//        });
    }
}