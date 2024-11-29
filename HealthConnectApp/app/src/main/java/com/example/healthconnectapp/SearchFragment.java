package com.example.healthconnectapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthconnectapp.Adapter.searchAdapter;
import com.example.healthconnectapp.Database.doctorDatabase;
import com.google.android.material.search.SearchBar;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    searchAdapter adapter;
    MaterialSearchBar searchBar;
    doctorDatabase database;
    List<String> suggestList = new ArrayList<>();
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        searchBar = (MaterialSearchBar) view.findViewById(R.id.searchBar);

        database = new doctorDatabase(this);
        searchBar.setHint("Search");
        searchBar.setElevation(10);
        loadSuggestList();
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for(String search:suggestList) {
                    if(search.toLowerCase().contains(searchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                searchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled) {
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
        adapter = new searchAdapter(this,database.getDoctors());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void startSearch(String text) {
        adapter = new searchAdapter(this, database.getDoctorByName(text));
        recyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = database.getNames();
        searchBar.setLastSuggestions(suggestList);
    }
}