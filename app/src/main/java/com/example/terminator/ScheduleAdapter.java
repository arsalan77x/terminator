package com.example.terminator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Object> listRecyclerItem;

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

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ScheduleAdapter.ItemViewHolder itemViewHolder = (ScheduleAdapter.ItemViewHolder) holder;
        Course course = (Course) listRecyclerItem.get(position);
        itemViewHolder.button.setText(course.getName());
        itemViewHolder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CourseDetailPopup courseDetailPopup = new CourseDetailPopup(course);
                courseDetailPopup.showPopupWindow(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listRecyclerItem.size();
    }
}
