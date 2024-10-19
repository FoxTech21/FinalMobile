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
import android.widget.Toast;

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
        long estimateDay = intent.getLongExtra("estimateDay", 0); // Lấy estimateDay

        if(estimateDay == 0)
        {
            // Tách startDate thành các thành phần riêng biệt (day, month, year)
            String[] startDateParts = startDate.split("/"); // Tách chuỗi theo dấu "/"
            int startDay = Integer.parseInt(startDateParts[0]);  // "25"
            String startMonth = startDateParts[1];  // "09"
            String startYear = startDateParts[2];  // "2024"

            // Tách endDate thành các thành phần riêng biệt (day, month, year)
            String[] endDateParts = endDate.split("/");  // Tách chuỗi theo dấu "/"
            int endDay = Integer.parseInt(endDateParts[0]);  // "30"
            String endMonth = endDateParts[1];  // "10"
            String endYear = endDateParts[2];

            estimateDay = (endDay - startDay) + 1; // Tính toán estimateDay
        }else {
            Toast.makeText(this, "estimateDay: " + estimateDay, Toast.LENGTH_SHORT).show();
        }

        // Thiết lập dữ liệu vào các TextView
        txtTaskId.setText(String.valueOf(taskID));
        devNameTxt.setText(devName);
        taskNameTxt.setText(taskName);
        startDateTxt.setText(startDate);
        endDateTxt.setText(endDate);
        progressBarPercent.setText(progressPercent + "%");
        estimateDayTxt.setText(String.valueOf(estimateDay)); // Hiển thị estimateDay

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

        ImageView create = findViewById(R.id.imageAdd);
        create.setOnClickListener(v -> {
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
