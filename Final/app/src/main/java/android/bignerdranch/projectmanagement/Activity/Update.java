package android.bignerdranch.projectmanagement.Activity;

import android.bignerdranch.projectmanagement.SQLiteOpenHelper.DatabaseHandler;
import android.bignerdranch.projectmanagement.databinding.ActivityUpdateBinding;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bignerdranch.projectmanagement.R;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    private ActivityUpdateBinding bindingUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);

        bindingUpdate = ActivityUpdateBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(bindingUpdate.getRoot());


        ImageView search = findViewById(R.id.imageSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this, SearchPage.class);
                startActivity(intent);
            }
        });

        ImageView home = findViewById(R.id.imageHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this, MainPage.class);
                startActivity(intent);
            }
        });

        ImageView setting = findViewById(R.id.imageSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this, SettingPage.class);
                startActivity(intent);
            }
        });

        ImageView creat = findViewById(R.id.imageAdd);
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this, Feature.class);
                startActivity(intent);
            }
        });

        // Nhận dữ liệu từ Intent
        String taskID = getIntent().getStringExtra("taskID");
        String taskName = getIntent().getStringExtra("taskName");
        String startDate = getIntent().getStringExtra("startDate");
        String endDate = getIntent().getStringExtra("endDate");
        String devName = getIntent().getStringExtra("devName");
        int progressPercent = getIntent().getIntExtra("progressPercent", 0);
        int estimate = getIntent().getIntExtra("estimateDay", 0);

        // Tách startDate thành các thành phần riêng biệt (day, month, year)
        String[] startDateParts = startDate.split("/"); // Tách chuỗi theo dấu "/"
        int startDay = Integer.parseInt(startDateParts[0]);  // "25"
        String startMonth = startDateParts[1];  // "09"
        String startYear = startDateParts[2];  // "2024"

        // Tách endDate thành các thành phần riêng biệt (day, month, year)
        String[] endDateParts = endDate.split("/");  // Tách chuỗi theo dấu "/"
        int endDay = Integer.parseInt(endDateParts[0]);  // "30"
        String endMonth = endDateParts[1];  // "10"
        String endYear = endDateParts[2];  // "2024"

        if(estimate == 0)
        {
            estimate = (endDay - startDay) + 1; // Tính toán estimateDay
        }else {
            Toast.makeText(this, "estimateDay: " + estimate, Toast.LENGTH_SHORT).show();
        }


        // Hiển thị thông tin cũ
        bindingUpdate.edittaskID.setText(taskID);
        bindingUpdate.editdevName.setText(devName);
        bindingUpdate.edittaskName.setText(taskName);

        bindingUpdate.editstartDay.setText(String.valueOf(startDay));
        bindingUpdate.editstartMonth.setText(startMonth);
        bindingUpdate.editstartYear.setText(startYear);

        bindingUpdate.editendDay.setText(String.valueOf(endDay));
        bindingUpdate.editendMonth.setText(endMonth);
        bindingUpdate.editendYear.setText(endYear);

        bindingUpdate.editProgressPercent.setText(String.valueOf(progressPercent));
        bindingUpdate.estimatetxt.setText(String.valueOf(estimate));

        // Khi người dùng nhấn nút cập nhật
        Button btnUpdate = bindingUpdate.btnUpdate;
        btnUpdate.setOnClickListener(v -> {
            int newTaskId = Integer.parseInt(bindingUpdate.edittaskID.getText().toString());
            String newTaskName = bindingUpdate.edittaskName.getText().toString();
            String newDevName = bindingUpdate.editdevName.getText().toString();

            String newStartDay = bindingUpdate.editstartDay.getText().toString();
            String newStartMonth = bindingUpdate.editstartMonth.getText().toString();
            String newStartYear = bindingUpdate.editstartYear.getText().toString();

            String newEndDay = bindingUpdate.editendDay.getText().toString();
            String newEndMonth = bindingUpdate.editendMonth.getText().toString();
            String newEndYear = bindingUpdate.editendYear.getText().toString();

            int newProgressPercent = Integer.parseInt(bindingUpdate.editProgressPercent.getText().toString());
            int newEstimate = Integer.parseInt(bindingUpdate.estimatetxt.getText().toString());

            String newStartDate = newStartDay + "/" + newStartMonth + "/" + newStartYear;
            String newEndDate = newEndDay + "/" + newEndMonth + "/" + newEndYear;

            // Cập nhật thông tin vào database
            DatabaseHandler db = new DatabaseHandler(this);
            db.updateTask(newTaskId, newDevName, newStartDate, newEndDate, newTaskName, newProgressPercent, newEstimate);

            // Đóng Activity sau khi cập nhật
            finish();
        });

    }
}