package fr.cedric.haegele.mareu_application.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.cedric.haegele.mareu_application.DI.DI;
import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.Service.MeetingApiService;
import fr.cedric.haegele.mareu_application.model.Meeting;


public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder>implements Filterable {

    List<Meeting> mMeetings;
    OnMeetingClickListener onMeetingClickListener;
    List<Meeting>fullListMeeting;




    public MeetingRecyclerViewAdapter(List<Meeting> meetings) {
        this.mMeetings = meetings;
        fullListMeeting = new ArrayList<>(mMeetings);
    }

    @NonNull
    @Override
    public MeetingRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meeting1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingRecyclerViewAdapter.ViewHolder holder, int position) {

        Meeting meeting = mMeetings.get(position);
        holder.displayMeeting(mMeetings.get(position));

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), MeetingDetailActivity.class);
        intent.putExtra("DATA_MEETINGS",meeting);
        v.getContext().startActivity(intent);

    }
});

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    void update(List<Meeting> meetings) {
        this.mMeetings = meetings;
        notifyDataSetChanged();
    }



    public interface OnMeetingClickListener {
        void onMeetingClicked(int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView photo;
        public TextView title, room, hour,date;
        public TextView mail;
        public ImageView delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            title = itemView.findViewById(R.id.title);
            room = itemView.findViewById(R.id.room);
            hour = itemView.findViewById(R.id.hour);
            date = itemView.findViewById(R.id.date);
            mail = itemView.findViewById(R.id.mail);
            delete = itemView.findViewById(R.id.delete);

        }



        public void displayMeeting(Meeting meeting) {
            photo.setColorFilter(meeting.getDrawable());
            title.setText(meeting.getTopicMeeting());
            hour.setText(meeting.getHourMeeting());
            mail.setText(meeting.getMails().toString()
                    .substring(1, meeting.getMails().toString().length() - 1));
            date.setText(meeting.getDateMeeting());
            room.setText(meeting.getRoomName());

        }


    }


    /**
     * Update mReunion
     **/
    void setData(List<Meeting> list) {
        this.mMeetings = list;
        notifyDataSetChanged();

    }
    @Override
    public Filter getFilter() {
        return filter;
    }

    private final Filter filter = new Filter() {


        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Meeting> filteredMeetingList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredMeetingList.addAll(fullListMeeting);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Meeting item : fullListMeeting) {
                    if (item.getRoomName().toLowerCase().contains(filterPattern)) {
                        filteredMeetingList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredMeetingList;
            return filterResults;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mMeetings.clear();
            mMeetings.addAll(((List)filterResults.values));
            notifyDataSetChanged();
        }
    };


}

