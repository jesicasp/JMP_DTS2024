package com.jesica.aplikasilistview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private List<String> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.rv_negara);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        countryList = new ArrayList<>();
        countryList.add("Indonesia");
        countryList.add("Malaysia");
        countryList.add("Singapore");
        countryList.add("Italia");
        countryList.add("Inggris");
        countryList.add("Belanda");
        countryList.add("Argentina");
        countryList.add("Mesir");
        countryList.add("Urganda");

        adapter = new CountryAdapter(countryList, this);
        recyclerView.setAdapter(adapter);

    }
}