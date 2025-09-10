package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // declare variable so we can reference later
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo",
                "Beijing", "Osaka", "New Delhi"};


        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        // initializing CityAdapter
        cityAdapter = new CityAdapter(this, dataList, position -> {
                    if (position >= 0 && position < dataList.size()) {
                        dataList.remove(position);
                        cityAdapter.notifyDataSetChanged();
                        cityList.clearChoices();
                    }
                }
        );

        // MainActivity.java (inside onCreate after you build dataList)
        cityList.setAdapter(cityAdapter);

        Button addBtn = findViewById(R.id.addCity);
        addBtn.setOnClickListener(v -> {
            // Build an input dialog
            final EditText input = new EditText(this);
            input.setHint("City name");

            new AlertDialog.Builder(this)
                    .setTitle("Add City")
                    .setView(input)
                    .setPositiveButton("Add", (dialog, which) -> {
                        String name = input.getText().toString().trim();
                        if (name.isEmpty()) {
                            Toast.makeText(this, "City name can't be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // (Optional) prevent duplicates
                        if (dataList.contains(name)) {
                            Toast.makeText(this, "City already in list", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        dataList.add(name);
                        cityAdapter.notifyDataSetChanged();
                        cityList.smoothScrollToPosition(dataList.size() - 1);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });


    }
}

