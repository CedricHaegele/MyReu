package fr.cedric.haegele.mareu_application.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.util.Date;


/**
 * Model object representing a Meeting
 */

public class Meeting implements Parcelable {

    public static final Creator<Meeting>CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };


    /** Topic Meeting */
    private String topicMeeting;

    /** Room Name */
    private String roomName;

    /** Date Meeting */
    private Date dateMeeting;

    private Time hourMeeting;

    /** Mails*/
    private String mails;

    /** drawable */
    private int drawable;
    private Date date;

    public Date getDateMeeting() {
        return dateMeeting;
    }

    public void setDateMeeting(Date dateMeeting) {
        this.dateMeeting = dateMeeting;
    }

    public Time getHourMeeting() {
        return hourMeeting;
    }

    public void setHourMeeting(Time hourMeeting) {
        this.hourMeeting = hourMeeting;
    }

    public void setMails(String mails) {
        this.mails = mails;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    protected Meeting(Parcel in) {
        topicMeeting = in.readString();
        roomName = in.readString();
        dateMeeting = new Date(in.readLong());
        hourMeeting = new Time(in.readLong());
        mails = in.readString();
        drawable = in.readInt();
    }
    /**
     * Constructor
     * @param topicMeeting
     * @param roomName
     * @param dateMeeting    */
    public Meeting(String topicMeeting, String roomName, Date dateMeeting,Time hourMeeting, String mails) {

        this.topicMeeting = topicMeeting;
        this.roomName = roomName;
        this.dateMeeting = dateMeeting;
        this.hourMeeting=hourMeeting;
        this.mails = mails;

    }

    public String getTopicMeeting() {
        return topicMeeting;
    }


    public String getMails() {
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
        dest.writeString(mails);
        dest.writeLong(dateMeeting.getTime());
        dest.writeLong(hourMeeting.getTime());
        dest.writeInt(drawable);

    }

}