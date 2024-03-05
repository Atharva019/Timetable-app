package com.example.ttgmp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class assignSubTech extends AppCompatActivity implements View.OnClickListener {
    Button next, prev;
    Intent intent_ast;
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;
    public static String SUBS = "",staff="";
    int key_count = 0, i = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards);
        setContentView(R.layout.activity_assign_sub_tech);
        Toast.makeText(this, "Assign subjects to the teachers", Toast.LENGTH_LONG).show();

        //ls = getListView();
//        sub_adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,subjects);
//        search_sub = findViewById(R.id.search_subs);
//        search_sub.setThreshold(3);
//        search_sub.setAdapter(sub_adapter);

        items = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("staff")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                items.add(i + ". " + (String) document.get("name"));
                                staff += (String) document.get("name")+",";
                                i++;
                            }
                            //Toast.makeText(assignSubTech.this, ""+items, Toast.LENGTH_SHORT).show();
                            recyclerView = findViewById(R.id.recycle);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                            adapter = new Adapter(getApplicationContext(), items);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(assignSubTech.this, "failed to fetch data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //code for displaying selected department subjects to card
        FirebaseDatabase reference = FirebaseDatabase.getInstance();
            reference.getReference(""+subjects.department).child(""+subjects.year).child(""+subjects.sel_sem)
                    //reference.getReference("CSE").child("third year").child("2nd sem")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().exists()) {
                                        DataSnapshot snapshot = task.getResult();
                                        key_count = (int) snapshot.getChildrenCount();
                                        for (int i = 1; i <= key_count; i++) {
                                            SUBS += snapshot.child("" + i).getValue() + ",";
                                        }
                                    //t2.setText(String.format("%s\n\n%s\n\n%s", subs_CSE, subs_CIVIL, subs_MECH));
                                }
                            }
                        }
                    });

        next = findViewById(R.id.next_ast);
        prev = findViewById(R.id.prev_ast);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == next) {
            intent_ast = new Intent(getBaseContext(), working_days.class);
            startActivity(intent_ast);
            //sliding effect right to lefr
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
        }
        //previous button
        if (v == prev) {
            intent_ast = new Intent(getBaseContext(), teachers.class);
            startActivity(intent_ast);
            //sliding effect left to right
            overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
    }
}