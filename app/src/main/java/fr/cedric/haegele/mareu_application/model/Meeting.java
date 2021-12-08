package fr.cedric.haegele.mareu_application.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Model object representing a Meeting
 */

public class Meeting implements Parcelable {

    /** Topic Meeting */
    private final String topicMeeting;

    /** Room Name */
    private final String roomName;

    /** Date Meeting */
    private final String dateMeeting;

    /** Hour Meeting */
    private final String hourMeeting;

    /** Mails' List */
    private final List<String> mails;

    /** Drawable */
    private int drawable;

    /** Constructor ( topicMeeting,roomName, dateMeeting, hourMeeting) */
    public Meeting(String topicMeeting, String roomName, String dateMeeting, String hourMeeting, List<String> mails) {

        this.topicMeeting = topicMeeting;
        this.roomName = roomName;
        this.dateMeeting = dateMeeting;
        this.hourMeeting = hourMeeting;
        this.mails = mails;
    }

    /**
     * GETTERS
     */
    public String getTopicMeeting() {
        return topicMeeting;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getDateMeeting() {
        return dateMeeting;
    }

    public String getHourMeeting() {
        return hourMeeting;
    }

    public List<String> getMails() {
        return mails;
    }

    public int getDrawable() {
        return drawable;
    }

    /**
     * Parcelable Implementation
     */
    protected Meeting(Parcel in) {
        topicMeeting = in.readString();
        roomName = in.readString();
        dateMeeting = in.readString();
        hourMeeting = in.readString();
        mails = in.createStringArrayList();
        drawable = in.readInt();
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(topicMeeting);
        dest.writeString(roomName);
        dest.writeString(dateMeeting);
        dest.writeString(hourMeeting);
        dest.writeStringList(mails);
        dest.writeInt(drawable);
    }
}