package android.bignerdranch.projectmanagement.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.bignerdranch.projectmanagement.R;

public class TaskDetail extends AppCompatActivity {
    private TextView txtTaskId,devNameTxt, taskNameTxt, startDateTxt, endDateTxt, estimateDayTxt, progressBarPercent;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_detail);

        // Khởi tạo các TextView
        txtTaskId = findViewById(R.id.txtTaskID);
        devNameTxt = findViewById(R.id.txtDevName);
        taskNameTxt = findViewById(R.id.txtTaskName);
        startDateTxt = findViewById(R.id.txtStartDate);
        endDateTxt = findViewById(R.id.txtEndDate);
        progressBarPercent = findViewById(R.id.percenttxt);
        progressBar = findViewById(R.id.progressBar);
        estimateDayTxt = findViewById(R.id.txtEstimateDay);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        int taskID = intent.getIntExtra("taskID", -1); // Giá trị mặc định là -1
        String devName = intent.getStringExtra("devName");
        String taskName = intent.getStringExtra("taskName");
        String startDate = intent.getStringExtra("startDate");
        String endDate = intent.getStringExtra("endDate");
        int progressPercent = intent.getIntExtra("progressPercent", 0); // Giá trị mặc định là 0
        int estimateDay = intent.getIntExtra("estimateDay", 0);

        // Thiết lập dữ liệu vào các TextView
        txtTaskId.setText(String.valueOf(taskID));
        devNameTxt.setText(devName);
        taskNameTxt.setText(taskName);
        startDateTxt.setText(startDate);
        endDateTxt.setText(endDate);
        progressBarPercent.setText(progressPercent + "%");
        estimateDayTxt.setText(estimateDay + " days");

        // Đặt giá trị phần trăm cho ProgressBar
        progressBar.setProgress(progressPercent); // Hiển thị phần trăm tiến độ

        // Kiểm tra trạng thái checkbox từ SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("TaskPreferences", MODE_PRIVATE);
        boolean isEstimateDayHidden = sharedPref.getBoolean("isEstimateDayHidden", false);

        // Ẩn estimateDay nếu checkbox được chọn
        if (isEstimateDayHidden) {
            estimateDayTxt.setVisibility(View.GONE); // Ẩn estimateDay
        } else {
            estimateDayTxt.setVisibility(View.VISIBLE); // Hiển thị estimateDay nếu không bị ẩn
        }

        // Khởi tạo các phần tử UI khác
        LinearLayout search = findViewById(R.id.search);
        search.setOnClickListener(v -> {
            Intent searchIntent = new Intent(TaskDetail.this, SearchPage.class);
            startActivity(searchIntent);
        });

        LinearLayout home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent homeIntent = new Intent(TaskDetail.this, MainPage.class);
            startActivity(homeIntent);
        });

        LinearLayout setting = findViewById(R.id.setting);
        setting.setOnClickListener(v -> {
            Intent settingIntent = new Intent(TaskDetail.this, SettingPage.class);
            startActivity(settingIntent);
        });

        ImageView creat = findViewById(R.id.imageAdd);
        creat.setOnClickListener(v -> {
            Intent featureIntent = new Intent(TaskDetail.this, Feature.class);
            startActivity(featureIntent);
        });

        LinearLayout task = findViewById(R.id.task);
        task.setOnClickListener(v -> {
            Intent taskIntent = new Intent(TaskDetail.this, TaskActivity.class);
            startActivity(taskIntent);
        });
    }
}
