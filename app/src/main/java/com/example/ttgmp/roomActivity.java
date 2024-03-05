package com.example.ttgmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class roomActivity extends AppCompatActivity implements View.OnClickListener {
    Button next,prev,add_room,add_grp;
    ListView rooms,groups;
    public static String str_r="";
    private static String str_g="";
     Intent intent_room;
    EditText room_tx,grp_tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        next = findViewById(R.id.next_room);
        prev = findViewById(R.id.prev_room);
        add_grp = findViewById(R.id.add_stud_grp);
        add_room = findViewById(R.id.add_room);
        room_tx = findViewById(R.id.room_tx);
        grp_tx = findViewById(R.id.stud_grp_tx);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        add_room.setOnClickListener(this);
        add_grp.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        //add rooms button action
        if (v == add_room){
            if (!room_tx.getText().toString().equals("")) {
                str_r += room_tx.getText().toString() + "#";
                String[] room_arr = str_r.split("#");
                ArrayAdapter<String> room_adt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, room_arr);
                rooms = findViewById(R.id.room_list);
                rooms.setAdapter(room_adt);
                room_tx.setText("");
            }else {
                room_tx.setError("please fill this field");
                room_tx.requestFocus();
            }
        }
        //add student's groups button action
        if (v == add_grp){
            if (!grp_tx.getText().toString().equals("")) {
                str_g += grp_tx.getText().toString() + "#";
                String[] grp_arr = str_g.split("#");
                ArrayAdapter<String> gpr_adt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, grp_arr);
                groups = findViewById(R.id.studu_grp_list);
                groups.setAdapter(gpr_adt);
                grp_tx.setText("");
            }else {
                grp_tx.setError("please fill this field");
                grp_tx.requestFocus();
            }
        }
        //next button
        if (v == next){
           // if (!str_r.isEmpty() && str_g.isEmpty()) {
                intent_room = new Intent(getBaseContext(), activity_result.class);
                startActivity(intent_room);
                //sliding effect right to lefr
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
                if (str_r.isEmpty()) {
                    Toast.makeText(this, "add rooms to continue", Toast.LENGTH_SHORT).show();
                }
                if (str_g.isEmpty()) {
                    Toast.makeText(this, "add student group to continue", Toast.LENGTH_SHORT).show();
                }
            //}else {
             //   Toast.makeText(this, "please add class rooms & student groups", Toast.LENGTH_SHORT).show();
           // }
        }
        //previous button
        if (v == prev){
            intent_room = new Intent(getBaseContext(),working_days.class);
            startActivity(intent_room);
            //sliding effect left to right
            overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
    }
}