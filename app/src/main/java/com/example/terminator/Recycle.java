package com.example.terminator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recycle extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    public final List<Object> listRecyclerItem;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public Recycle(Context context, List<Object> listRecyclerItem) {
        this.listRecyclerItem = listRecyclerItem;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final Button button;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.list_item_button);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.course_item, parent, false);


        return new ItemViewHolder((layoutView));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Course course = (Course) listRecyclerItem.get(position);
        itemViewHolder.button.setText(course.getName() + " / " + course.getInstructor());
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
        return listRecyclerItem.size();
    }
}
