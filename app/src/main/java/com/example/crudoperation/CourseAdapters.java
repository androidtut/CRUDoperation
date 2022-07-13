package com.example.crudoperation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudoperation.Fragments.ButtonSheetFragment;
import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseAdapters extends RecyclerView.Adapter<CourseAdapters.ViewHolder> {
    ArrayList<courseModels>list;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public CourseAdapters(ArrayList<courseModels> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_layout,parent,false);
        return new ViewHolder(view);
    }

    public void setFilterList(ArrayList<courseModels> FilterList){
        this.list = FilterList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         final courseModels models = list.get(position);
//         holder.image.setImageResource(models.getImage());
         Picasso.get().load(models.getImageurl()).into(holder.image);
         holder.title.setText(models.getTitle());
         holder.desc.setText(models.getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView title,desc;
    Button edit,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.courseimage);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
