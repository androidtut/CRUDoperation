package com.example.crudoperation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.crudoperation.Fragments.ButtonSheetFragment;
import com.example.crudoperation.databinding.ActivityInsertBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {
    private ActivityInsertBinding binding;
    private String title,desc,url;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,id;
    private ArrayList<Blogdata> blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Blogdata");

//        add single text to the database
//        databaseReference.setValue("Hello world");

//        using push method we can generate a random id
//        databaseReference.push().setValue("Hello world");




        binding.adddata.setOnClickListener(v->{
            title = binding.title.getText().toString();
            desc = binding.desc.getText().toString();
            url = binding.imageurl.getText().toString();
//            databaseReference.push().child("name").setValue(title + desc + url);
            if(title.isEmpty()){
                Toast.makeText(this, "Please add title", Toast.LENGTH_SHORT).show();
            }else if(desc.isEmpty()){
                Toast.makeText(this, "Please add desc", Toast.LENGTH_SHORT).show();
            }else if(url.isEmpty()){
                Toast.makeText(this, "Please add url", Toast.LENGTH_SHORT).show();
            }else{
//                there are many way to insert data from database
//                first way
                id =  databaseReference.push();
//                id.child("title").setValue(title);
//                id.child("desc").setValue(desc);
//                id.child("url").setValue(url);

//                second way to create constructor and arraylist
                Blogdata blogdata = new Blogdata(title,desc,url);
                blog = new ArrayList<>();
                blog.add(blogdata);
                id.setValue(blogdata).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(InsertActivity.this, "data add successfully", Toast.LENGTH_SHORT).show();
                    }
                });

                binding.title.setText("");
                binding.desc.setText("");
                binding.imageurl.setText("");
            }
        });

    }

}