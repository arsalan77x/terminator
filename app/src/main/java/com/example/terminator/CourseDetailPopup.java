package com.example.terminator;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class CourseDetailPopup {
    Course course;

    public CourseDetailPopup(Course course) {
        this.course = course;
    }

    public void showPopupWindow(final View view) {


        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.course_detail_popup_layout, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


        TextView test2 = popupView.findViewById(R.id.course_number);
        test2.setText(this.course.getName());

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button addButton = popupView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (true) {
                    Toast.makeText(view.getContext(), "این درس به برنامه شما اضافه شد", Toast.LENGTH_SHORT).show();
                }
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button cancelButton = popupView.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
