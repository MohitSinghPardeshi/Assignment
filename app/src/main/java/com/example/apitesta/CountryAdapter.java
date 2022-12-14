package com.example.apitesta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import retrofit2.Callback;

public class CountryAdapter extends ArrayAdapter<Country> {
    Context context;
    public List<Country> list;
    public CountryAdapter(Context context, List<Country> list) {
        super(context, R.layout.country_item,list);
        this.context= context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View countryItem =layoutInflater.inflate(R.layout.country_item, parent, false);
        ProgramViewHolder holder= new ProgramViewHolder(countryItem);
        countryItem.setTag(holder);

        holder.countryId.setText(list.get(position).getCountryId());
        holder.probability.setText(""+list.get(position).getProbability());
        return countryItem;
    }


    public class ProgramViewHolder {
        TextView countryId;
        TextView probability;
        ProgramViewHolder(View v){
            countryId=v.findViewById(R.id.countryIdTextTV);
            probability= v.findViewById(R.id.probabilityTextTV);
        }
    }

}