package com.ebookfrenzy.numad23sp_team16;

import android.os.Parcel;
import android.os.Parcelable;
//for our project, we need to load png image, not svg.Because svg requires the use of other library.
public class Flag implements Parcelable {
    private String svg;
    private String png;

    public Flag(String svg, String png) {
        this.svg = svg;
        this.png = png;
    }

    protected Flag(Parcel in) {
        svg = in.readString();
        png = in.readString();
    }

    public static final Creator<Flag> CREATOR = new Creator<Flag>() {
        @Override
        public Flag createFromParcel(Parcel in) {
            return new Flag(in);
        }

        @Override
        public Flag[] newArray(int size) {
            return new Flag[size];
        }
    };

    public String getSvg() {
        return svg;
    }

    public String getPng() {
        return png;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public void setPng(String png) {
        this.png = png;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(svg);
        dest.writeString(png);
    }
}

