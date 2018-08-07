package com.azens.countrylist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azens.countrylist.R;
import com.azens.countrylist.model.Country;

import java.util.List;

/**
 * Created by Azens Eklak on 8/7/18.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private Context context;
    private List<Country> countries;


    public CountryAdapter(Context context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_rows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(countries.get(position).getName());
        holder.capital.setText(countries.get(position).getCapital());
        holder.natName.setText(countries.get(position).getNativeName());
        holder.population.setText(countries.get(position).getPopulation());
        holder.area.setText(countries.get(position).getArea());
        holder.region.setText(countries.get(position).getRegion());

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView capital;
        private TextView region;
        private TextView area;
        private TextView population;
        private TextView natName;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            capital = (TextView) itemView.findViewById(R.id.capital);
            region = (TextView) itemView.findViewById(R.id.region);
            area = (TextView) itemView.findViewById(R.id.area);
            population = (TextView) itemView.findViewById(R.id.population);
            natName = (TextView) itemView.findViewById(R.id.nativeName);

        }
    }
}
