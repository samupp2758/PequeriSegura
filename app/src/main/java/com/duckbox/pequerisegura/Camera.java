package com.duckbox.pequerisegura;

import android.os.Parcel;
import android.os.Parcelable;

public class Camera implements Parcelable {
    public String camera;
    public String link_;

    public Camera(){

    }

    public Camera(String camera,String link_){
        this.camera = camera;
        this.link_ = link_;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getLink_() {
        return link_;
    }

    public void setLink_(String link_) {
        this.link_ = link_;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Camera(Parcel parcel){
        setLink_(parcel.readString());
        setCamera(parcel.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( getCamera() );
        dest.writeString( getLink_() );
    }

    public static final Parcelable.Creator<Camera> CREATOR = new Parcelable.Creator<Camera>(){
        @Override
        public Camera createFromParcel(Parcel source) {
            return new Camera(source);
        }
        @Override
        public Camera[] newArray(int size) {
            return new Camera[size];
        }
    };
}
