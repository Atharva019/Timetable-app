package com.example.ttgmp;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> data;
    static String teachInfos_arr;
    Context context;

    Adapter(Context context,List<String> data){
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @androidx.annotation.NonNull
    @Override
    public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.cards,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ViewHolder viewHolder, int i) {
        String teacher_name = data.get(i);
        viewHolder.teacher_name.setText(teacher_name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView teacher_name,subjects,sel_subs;
        String subs="";
        AutoCompleteTextView search;
        String[] sub = assignSubTech.SUBS.split(",");
        String[] arr = {"one","two","three","four","five"};
        Button add;
        public ViewHolder(@androidx.annotation.NonNull View itemView) {
            super(itemView);
            teacher_name = itemView.findViewById(R.id.teach_name_ast);
            subjects = itemView.findViewById(R.id.subject_name_ast);
            sel_subs = itemView.findViewById(R.id.sel_subs);
            sel_subs.setMovementMethod(new ScrollingMovementMethod());
            search = itemView.findViewById(R.id.search_subs);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, sub);
            search.setThreshold(1);
            search.setAdapter(adapter);
            add = itemView.findViewById(R.id.add_sub);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (search.getText().toString().isEmpty()){
                        search.setError("please fill out this field");
                        search.requestFocus();
                    }else {
                        subs += search.getText().toString() + "";
                        sel_subs.setText(subs);
                        search.setText("");
                        teachInfos_arr += teacher_name.getText().toString() + ":" + sel_subs.getText().toString();
                        if (sel_subs.getText().toString().isEmpty()){
                            Toast.makeText(context, "Assign subject to teacher", Toast.LENGTH_SHORT).show();
                        }else {
                            add.setEnabled(false);
                        }

                    }
                }
            });
        }
    }
}
