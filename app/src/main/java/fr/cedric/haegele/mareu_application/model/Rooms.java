package fr.cedric.haegele.mareu_application.model;

import android.os.Parcel;
import android.os.Parcelable;

import fr.cedric.haegele.mareu_application.R;

public class Rooms implements Parcelable {

    private final int Id;
    private String RoomName;
    private int drawable;

    public Rooms(int id, String roomName, int drawable) {
        Id = id;
        RoomName = roomName;
        this.drawable = drawable;
    }


    protected Rooms(Parcel in) {
        Id = in.readInt();
        RoomName = in.readString();
        drawable = in.readInt();
    }

    public static final Creator<Rooms> CREATOR = new Creator<Rooms>() {
        @Override
        public Rooms createFromParcel(Parcel in) {
            return new Rooms(in);
        }

        @Override
        public Rooms[] newArray(int size) {
            return new Rooms[size];
        }
    };

    public int getId() {
        return Id;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }


    public int getDrawable() {
        return drawable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(RoomName);
        dest.writeInt(drawable);
    }

}