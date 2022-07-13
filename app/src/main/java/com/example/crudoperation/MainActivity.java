package com.example.crudoperation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.crudoperation.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
FloatingActionButton floatingActionButton;
private ArrayList<courseModels>list;
    ActivityMainBinding binding;
    CourseAdapters adapters;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        floatingActionButton= findViewById(R.id.floatingActionButton);

        setSupportActionBar(binding.toolbar);

        floatingActionButton.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,InsertActivity.class);
            startActivity(intent);
        });

        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                FilterList(s.toString());
                return false;
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Blogdata");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                courseModels models = snapshot1.getValue(courseModels.class);
                if(models != null){
                    list.add(models);
                }else{
                    Toast.makeText(MainActivity.this, "No items added", Toast.LENGTH_SHORT).show();
                }
                }
                adapters = new CourseAdapters(list,getApplicationContext());
                binding.courserecycler.setAdapter(adapters);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                binding.courserecycler.setLayoutManager(linearLayoutManager);

                binding.courserecycler.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Blogdata");


                        return false;
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//
//        list.add(new courseModels(R.drawable.ic_launcher_background,"how to learn css","how to learn css"));
//        list.add(new courseModels(R.drawable.ic_launcher_background,"how to learn css","how to learn css"));
//        list.add(new courseModels(R.drawable.ic_launcher_background,"how to learn css","how to learn css"));
    }

    private void FilterList(String s) {
        ArrayList<courseModels> filterList = new ArrayList<>();
        for(courseModels item:list){
            if(item.getTitle().toLowerCase().contains(s.toLowerCase())){
                filterList.add(item);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapters.setFilterList(filterList);
        }
    }
}