package com.example.listycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CityAdapter extends ArrayAdapter<String> {

    // Allows deleting a city to be handled in MainActivity.java
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    // Declaring references passed via constructor
    private final List<String> cities;
    private final OnDeleteClickListener listener;

    // Constructor
    public CityAdapter(@NonNull Context context, @NonNull List<String> cities,
                       @NonNull OnDeleteClickListener listener) {
        super(context, 0, cities);
        this.cities = cities;
        this.listener = listener;
    }

    // Listview calls this whenever it needs row for position
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_list, parent, false);
        }

        TextView cityName = row.findViewById(R.id.cityName);
        Button deleteBtn = row.findViewById(R.id.deleteButton);

        String city = getItem(position);
        cityName.setText(city);

        final int currentPos = position;
        deleteBtn.setOnClickListener(v -> listener.onDeleteClick(currentPos));

        return row;
    }
}

