package com.ebookfrenzy.numad23sp_team16;

import android.os.Parcel;
import android.os.Parcelable;
//core is implement this Parcelable interface
//this is the model class for the api we fetched
//used Gson to convert Json into this java object
public class Country implements Parcelable {
    private String name;
    private long population;
    private String capital;
    private Flag flags;



    public Country(String name, long population, String capital, Flag flags) {
        this.name = name;
        this.population = population;
        this.capital = capital;
        this.flags = flags;
    }

    protected Country(Parcel in) {
        name = in.readString();
        population = in.readLong();
        capital = in.readString();
        flags = in.readParcelable(Flag.class.getClassLoader());
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public String getCapital() {
        return capital;
    }

    public Flag getFlag() {
        return flags;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flag) {
        dest.writeString(name);
        dest.writeLong(population);
        dest.writeString(capital);
        dest.writeParcelable(flags, flag);
    }
}
