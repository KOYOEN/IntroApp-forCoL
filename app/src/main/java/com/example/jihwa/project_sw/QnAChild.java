package com.example.jihwa.project_sw;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by koyo on 20/11/2018.
 */

public class QnAChild implements Parcelable {
    public final String name;

    public QnAChild(String name) {
        this.name =  name;
    }

    protected QnAChild(Parcel in) {
        name = in.readString();
    }

    public static final Creator<QnAChild> CREATOR = new Creator<QnAChild>() {
        @Override
        public QnAChild createFromParcel(Parcel in) {
            return new QnAChild(in);
        }

        @Override
        public QnAChild[] newArray(int size) {
            return new QnAChild[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name);
    }
}
