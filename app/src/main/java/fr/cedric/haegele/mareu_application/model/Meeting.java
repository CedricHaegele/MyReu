package fr.cedric.haegele.mareu_application.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;


/**
 * Model object representing a Meeting
 */

public class Meeting implements Parcelable {



    /** Topic Meeting */
    private String topicMeeting;

    /** Room Name */
    private String roomName;

    /** Date Meeting */
    private String dateMeeting;

    private String hourMeeting;

    /** Mails*/
    private List<String> mails;

    /** drawable */
    private int drawable;
    private Date date;

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


    public String getDateMeeting() {
        return dateMeeting;
    }

    public String getHourMeeting() {
        return hourMeeting;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    /**
     * Constructor
     * @param topicMeeting
     * @param roomName
     * @param dateMeeting
     * @param hourMeeting      */
    public Meeting(String topicMeeting, String roomName, String dateMeeting, String hourMeeting, List <String> mails) {

        this.topicMeeting = topicMeeting;
        this.roomName = roomName;
        this.dateMeeting = dateMeeting;
        this.hourMeeting=hourMeeting;
        this.mails = mails;

    }

    public String getTopicMeeting() {
        return topicMeeting;
    }


    public List <String> getMails() {
        return mails;
    }

    public String getRoomName() {
        return roomName;
    }



    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }


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

    public Date getDate() {
        return date;
    }
}