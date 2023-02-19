package com.ebookfrenzy.numad23sp_team16;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private final List<Country> countries;

    public CountryAdapter(List<Country> countries) {
        this.countries = countries;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView capitalTextView;
        public TextView populationTextView;
        public ImageView flagImageView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.nameTextView);
            capitalTextView = view.findViewById(R.id.capitalTextView);
            populationTextView = view.findViewById(R.id.populationTextView);
            flagImageView = view.findViewById(R.id.flagImageView);
        }
    }
    /**
    The ViewHolder class remains the same. The CountryAdapter class constructor accepts a List of Country objects and sets it to the countries member variable.

    The onCreateViewHolder() method inflates the country_item layout and returns a new ViewHolder.

    The onBindViewHolder() method sets the data to the views in the ViewHolder.

    The getItemCount() method returns the number of items in the countries list.
     **/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.nameTextView.setText(country.getName());
        holder.capitalTextView.setText(country.getCapital());
        holder.populationTextView.setText(String.valueOf(country.getPopulation()));
        //Glide is a popular image loading and caching library for Android.
        /**
         * The with() method of Glide is used to start building a request to load an image.
         *
         * In the Glide.with(holder.itemView.getContext()) line of code, holder.itemView.getContext() is used to get the Context of the View that the ViewHolder represents. The Context is used to help Glide with loading and caching images.
         *
         * load(country.getFlag()) is used to specify the URL of the image to load, which is obtained from the Country object using the getFlag() method.
         *
         * into(holder.flagImageView) is used to specify the ImageView to display the loaded image in, which is the flagImageView member variable of the ViewHolder.
         */
        Glide.with(holder.itemView.getContext())
                .load(country.getFlag().getPng())
                .override(100, 100)
                .into(holder.flagImageView);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}

