package android.bignerdranch.projectmanagement.Activity;

import android.annotation.SuppressLint;
import android.bignerdranch.projectmanagement.SQLiteOpenHelper.DatabaseHandler;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.bignerdranch.projectmanagement.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Feature extends AppCompatActivity {

    private EditText editdevID;
    private EditText editdevName;
    private EditText edittaskID;
    private EditText editstartDay;
    private EditText editstartMonth;
    private EditText editstartYear;
    private EditText editendDay;
    private EditText editendMonth;
    private EditText editendYear;
    private EditText startDate;
    private EditText endDate;
    private Button btnCreat;
    private DatabaseHandler databaseHandler;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feature);

        // Khởi tạo các view
        editdevID = findViewById(R.id.editDevId);
        editdevName = findViewById(R.id.editdevName);
        edittaskID = findViewById(R.id.edittaskID);
        editstartDay = findViewById(R.id.editstartDay);
        editstartMonth = findViewById(R.id.editstartMonth);
        editstartYear = findViewById(R.id.editstartYear);
        editendDay = findViewById(R.id.editendDay);
        editendMonth = findViewById(R.id.editendMonth);
        editendYear = findViewById(R.id.editendYear);
        btnCreat = findViewById(R.id.btnCreat);

        // Khởi tạo DatabaseHandler
        databaseHandler = new DatabaseHandler(this);

        // Sự kiện click nút Save
        btnCreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                String devId = editdevID.getText().toString();
                String devName = editdevName.getText().toString();
                String taskId = edittaskID.getText().toString();
                String startDay = editstartDay.getText().toString();
                String startMonth = editstartMonth.getText().toString();
                String startYear = editstartYear.getText().toString();
                String endDay = editendDay.getText().toString();
                String endMonth = editendMonth.getText().toString();
                String endYear = editendYear.getText().toString();
                String endDate = endDay + "/" + endMonth + "/" + endYear;
                String startDate = startDay + "/" + startMonth + "/" + startYear;

                // Kiểm tra nếu các trường không rỗng
                if (!devName.isEmpty() && !taskId.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                    // Chuyển đổi các giá trị ngày tháng năm sang số nguyên để so sánh
                    int startDayInt = Integer.parseInt(startDay);
                    int startMonthInt = Integer.parseInt(startMonth);
                    int startYearInt = Integer.parseInt(startYear);
                    int endDayInt = Integer.parseInt(endDay);
                    int endMonthInt = Integer.parseInt(endMonth);
                    int endYearInt = Integer.parseInt(endYear);

                    // Kiểm tra điều kiện startDate <= endDate
                    if (startYearInt < endYearInt ||
                            (startYearInt == endYearInt && startMonthInt < endMonthInt) ||
                            (startYearInt == endYearInt && startMonthInt == endMonthInt && startDayInt <= endDayInt)) {

                        // Thêm dữ liệu vào database
                        long newRowId = databaseHandler.insertDevTask(devName, Integer.parseInt(taskId), startDate, endDate);

                        // Kiểm tra nếu insert thành công
                        if (newRowId != -1) {
                            Toast.makeText(Feature.this, "Task added with ID: " + newRowId, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Feature.this, "Error inserting task", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Hiển thị thông báo nếu startDate > endDate
                        Toast.makeText(Feature.this, "Start date must be before or equal to end date", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Hiển thị thông báo nếu trường nào đó còn trống
                    Toast.makeText(Feature.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });



        ImageView search = findViewById(R.id.imageSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Feature.this, SearchPage.class);
                startActivity(intent);
            }
        });

        ImageView home = findViewById(R.id.imageHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Feature.this, MainPage.class);
                startActivity(intent);
            }
        });

        ImageView setting = findViewById(R.id.imageSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Feature.this, SettingPage.class);
                startActivity(intent);
            }
        });

        ImageView creat = findViewById(R.id.imageAdd);
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Feature.this, Feature.class);
                startActivity(intent);
            }
        });

        ImageView task = findViewById(R.id.imageTasks);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Feature.this, TaskActivity.class);
                startActivity(intent);
            }
        });
    }
}