package com.example.myprepare.class_test;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableTestData implements Parcelable {

  private String name;
  private int age;

  protected ParcelableTestData(Parcel in) {
    name = in.readString();
    age = in.readInt();
  }

  public static final Creator<ParcelableTestData> CREATOR = new Creator<ParcelableTestData>() {
    @Override
    public ParcelableTestData createFromParcel(Parcel in) {
      return new ParcelableTestData(in);
    }

    @Override
    public ParcelableTestData[] newArray(int size) {
      return new ParcelableTestData[size];
    }
  };

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeInt(age);
  }
}
