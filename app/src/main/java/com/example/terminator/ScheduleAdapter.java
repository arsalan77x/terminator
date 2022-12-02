package com.example.terminator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Object> listRecyclerItem;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public ScheduleAdapter(Context context, List<Object> listRecyclerItem) {
        this.listRecyclerItem = listRecyclerItem;
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final Button button;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            button = (Button) itemView.findViewById(R.id.schedule_item_button);

        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.schedule_item, parent, false);

        return new ScheduleAdapter.ItemViewHolder((layoutView));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ScheduleAdapter.ItemViewHolder itemViewHolder = (ScheduleAdapter.ItemViewHolder) holder;
        Course course = (Course) listRecyclerItem.get(position);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        if (position == 0){
            params.setMargins(0,Math.round(88*(course.getStart_times().get(0) - 7.0f)),
                    0, Math.round(course.getMarginBottom()));
            Log.d(LOG_TAG,Math.round(course.getMarginBottom())+"a");
        }
        else{
            Log.d(LOG_TAG,Math.round(course.getMarginBottom())+"b");
            params.setMargins(0, 0, 0, Math.round(course.getMarginBottom()));
        }
        itemViewHolder.button.setLayoutParams(params);
        itemViewHolder.button.setHeight(Math.round(course.getPadding()));

        itemViewHolder.button.setText(course.getName() + " / " + course.getInstructor());
        itemViewHolder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CourseDetailPopup courseDetailPopup = new CourseDetailPopup(course, true);
                courseDetailPopup.showPopupWindow(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listRecyclerItem.size();
    }
}
