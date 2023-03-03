package com.samraddhbestwin.microfinance.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.samraddhbestwin.microfinance.R;
import com.samraddhbestwin.microfinance.Response.NotificationListResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    Context mContext;
    ArrayList<NotificationListResponse.NotificationData> notificationList;

    NotificationListener notificationListener;

    public interface NotificationListener {
        void onNotificationDetail(String loan_id, String type, String notificationID);
    }


    public NotificationAdapter(Context context, ArrayList<NotificationListResponse.NotificationData> list, NotificationListener listener) {
        this.mContext = context;
        this.notificationList = list;
        this.notificationListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_notification_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationListResponse.NotificationData notificationData = notificationList.get(position);
        holder.notificationTitleTextView.setText(notificationData.getTitle());
        holder.notificationDescriptionTextView.setText(notificationData.getDescription());
        holder.notificationTimeTextView.setText(getTimeDifference(notificationData.getNotificationTime()));

        if (notificationData.getIsRead().equalsIgnoreCase("1")){
            holder.notificationDataConstraintLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }else {
            holder.notificationDataConstraintLayout.setBackgroundColor(mContext.getResources().getColor(R.color.gary_light));
        }


        holder.notificationDataConstraintLayout.setOnClickListener(view -> {
            notificationListener.onNotificationDetail(notificationData.getLoanId(), notificationData.getLoanType(),String.valueOf(notificationData.getId()));
        });
    }

    private String getTimeDifference(String notificationDate) {
        try {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date1 = dates.parse(currentDate);
            Date date2 = dates.parse(notificationDate);
            long difference_In_Time = date1.getTime() - date2.getTime();
            long elapsedSeconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long elapsedMinutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long elapsedHours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long elapsedDays
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            if (elapsedDays > 0) {
                return elapsedDays + " days ago";
            } else if (elapsedHours > 0 && elapsedHours < 24) {
                return elapsedHours + " Hours ago";
            } else if (elapsedMinutes > 0 && elapsedMinutes < 60) {
                return elapsedMinutes + " Min ago";
            } else if (elapsedSeconds > 0 && elapsedSeconds < 60) {
                return elapsedSeconds + " Sec ago";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notificationTitleTextView, notificationTimeTextView, notificationDescriptionTextView;
        ConstraintLayout notificationDataConstraintLayout;

        ViewHolder(View itemView) {
            super(itemView);
            notificationTitleTextView = itemView.findViewById(R.id.txt_notification_title);
            notificationTimeTextView = itemView.findViewById(R.id.txt_notification_time);
            notificationDescriptionTextView = itemView.findViewById(R.id.txt_notification_description);
            notificationDataConstraintLayout = itemView.findViewById(R.id.cl_data);
        }
    }
}
