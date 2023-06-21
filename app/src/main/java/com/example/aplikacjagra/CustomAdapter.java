package com.example.aplikacjagra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList rekord_id, rekord_name, rekord_time;

    CustomAdapter(Context context, ArrayList rekord_id, ArrayList rekord_name, ArrayList rekord_time) {
        this.context = context;
        this.rekord_id = rekord_id;
        this.rekord_name = rekord_name;
        this.rekord_time = rekord_time;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rekord_id_txt.setText(String.valueOf(rekord_id.get(position)));
        holder.rekord_name_txt.setText(String.valueOf(rekord_name.get(position)));
        holder.rekord_time_txt.setText(String.valueOf(rekord_time.get(position)));
    }

    @Override
    public int getItemCount() {
        return rekord_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rekord_id_txt, rekord_name_txt, rekord_time_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rekord_id_txt = itemView.findViewById(R.id.rekord_id_txt);
            rekord_name_txt = itemView.findViewById(R.id.rekord_name_txt);
            rekord_time_txt = itemView.findViewById(R.id.rekord_time_txt);
        }
    }
}
