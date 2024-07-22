package com.jesica.aplikasilistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private List<String> countries;
    private Context context;

    // Constructor untuk adapter, menerima list nama negara dan context
    public CountryAdapter(List<String> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    // ViewHolder untuk menampung elemen yang akan ditampilkan di RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_date); // Sesuaikan dengan ID TextView di list_item.xml
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout untuk setiap item
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data dari list ke ViewHolder sesuai posisi
        String country = countries.get(position);
        holder.textView.setText(country);
    }

    @Override
    public int getItemCount() {
        // Jumlah item yang akan ditampilkan
        return countries.size();
    }
}

