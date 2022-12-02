package com.example.terminator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseDetailPopup {
    Course course;
    boolean isDelete;
    public static ArrayList<Course> selectedCourses = new ArrayList<>();
    public static boolean isStarted = false;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public CourseDetailPopup(Course course, Boolean isDelete) {
        this.course = course;
        this.isDelete = isDelete;

    }

    @SuppressLint("SetTextI18n")
    public void showPopupWindow(final View view) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.course_detail_popup_layout, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


        TextView courseNamePopUp = popupView.findViewById(R.id.course_name);
        courseNamePopUp.setText(this.course.getName());
        TextView instructorPopUp = popupView.findViewById(R.id.instructor);
        instructorPopUp.setText(this.course.getInstructor());
        TextView courseNumberPopUp = popupView.findViewById(R.id.course_number);
        courseNumberPopUp.setText(this.course.getCourse_number());
        TextView classTimePopUp = popupView.findViewById(R.id.class_time);
        classTimePopUp.setText(this.course.getClass_times());
        TextView examTimePopUp = popupView.findViewById(R.id.exam_time);
        examTimePopUp.setText(this.course.getExam_time());
        TextView infoPopUp = popupView.findViewById(R.id.info);
        infoPopUp.setText("اطلاعات: " + this.course.getInfo());
        infoPopUp.setMovementMethod(new ScrollingMovementMethod());
        TextView unitPopUp = popupView.findViewById(R.id.course_unit);
        unitPopUp.setText(Integer.toString(this.course.getUnits()));
        TextView capacityPopUp = popupView.findViewById(R.id.course_capacity);
        capacityPopUp.setText(Integer.toString(this.course.getCapacity()));

        Button addButton = popupView.findViewById(R.id.add_button);
        if (isDelete) {
            addButton.setText("حذف کردن");
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isDelete) {
                    selectedCourses.remove(course);
                    SharedPreferences.Editor editor = MainActivity.sp.edit();
                    editor.clear();
                    editor.apply();
                    Log.d(LOG_TAG, ""+MainActivity.sp.getAll().size());
                    for (int i = 0; i < CourseDetailPopup.selectedCourses.size(); i++) {
                        editor.putInt("id" + i, CourseDetailPopup.selectedCourses.get(i).getId());
                    }
                    editor.apply();
                    Intent intent = new Intent(v.getContext(), ScheduleActivity.class);
                    v.getContext().startActivity(intent);
                } else{
                    if (selectedCourses.size() == 0 || !isDatesOverlapped()) {
                        selectedCourses.add(course);
                        SharedPreferences.Editor editor = MainActivity.sp.edit();
                        for (int i = 0; i < CourseDetailPopup.selectedCourses.size(); i++) {
                            editor.putInt("id" + i, CourseDetailPopup.selectedCourses.get(i).getId());
                        }
                        editor.apply();

                        Toast.makeText(view.getContext(), "این درس به برنامه شما اضافه شد", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(view.getContext(), "تداخل با برنامه درسی", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button cancelButton = popupView.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public boolean isDatesOverlapped() {
        for (int i = 0; i < selectedCourses.size(); i++) {
            int dayLength = selectedCourses.get(i).getDays().size();
            for (int j = 0; j < dayLength; j++) {
                for (int k = 0; k < course.getDays().size(); k++) {
                    if (Objects.equals(selectedCourses.get(i).getDays().get(j), course.getDays().get(k))) {
                        if (selectedCourses.get(i).getStart_times().get(j) <= course.getStart_times().get(k) &&
                                course.getStart_times().get(k) <= selectedCourses.get(i).getEnd_times().get(j)) {
                            return true;
                        }
                        if (selectedCourses.get(i).getEnd_times().get(j) >= course.getEnd_times().get(k) &&
                                course.getEnd_times().get(k) >= selectedCourses.get(i).getStart_times().get(j)) {
                            return true;
                        }

                    }
                }
            }
        }

        return false;
    }
}
