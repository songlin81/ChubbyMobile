package com.chubbymobile.wwh.chubbymobile;

import java.util.HashMap;
import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    public HashMap<String,String> map = new HashMap<String,String> ();

    public String name ;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeMap(map);
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
    //重写Creator

        @Override
        public Person createFromParcel(Parcel source) {
            Person p = new Person();
            p.map=source.readHashMap(HashMap.class.getClassLoader());
            p.name=source.readString();
            return p;
        }

        @Override
        public Person[] newArray(int size) {
            return null;
        }
    };
}