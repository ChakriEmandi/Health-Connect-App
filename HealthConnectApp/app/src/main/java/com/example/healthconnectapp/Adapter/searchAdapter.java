package com.example.healthconnectapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthconnectapp.Model.doctor;
import com.example.healthconnectapp.R;
import com.example.healthconnectapp.SearchFragment;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder {
    public TextView name, gender, specialization, email;
    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.search_name);
        gender = (TextView) itemView.findViewById(R.id.search_gender);
        specialization = (TextView) itemView.findViewById(R.id.search_spec);
        email = (TextView) itemView.findViewById(R.id.search_email);
    }
}
public class searchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private SearchFragment context;
    private List<doctor> doctors;

    public searchAdapter(SearchFragment context, List<doctor> doctors) {
        this.context = context;
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cards_search,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.name.setText(doctors.get(position).getName());
        holder.gender.setText(doctors.get(position).getGender());
        holder.specialization.setText(doctors.get(position).getSpecialization());
        holder.email.setText(doctors.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }
}
