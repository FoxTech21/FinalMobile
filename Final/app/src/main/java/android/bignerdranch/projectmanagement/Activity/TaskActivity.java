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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TaskActivity extends AppCompatActivity {

    private EditText edittaskName, editProgressPercent, edittaskId;
    private Button btnCreatTask;
    private DatabaseHandler databaseHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task);

        edittaskId = findViewById(R.id.edittaskId);
        edittaskName = findViewById(R.id.edittaskName);
        editProgressPercent = findViewById(R.id.editProgressPercent);

        // Khởi tạo DatabaseHandler
        databaseHandler = new DatabaseHandler(this);

        // Xử lý khi bấm nút tạo task
        Button creatTask = findViewById(R.id.btnCreatTask);
        creatTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskId = edittaskId.getText().toString();
                String taskName = edittaskName.getText().toString();
                String progressPercent = editProgressPercent.getText().toString();

                // Kiểm tra nếu các trường không rỗng
                if (!taskId.isEmpty() && !taskName.isEmpty() && !progressPercent.isEmpty()) {
                    // Thêm dữ liệu vào database (estimateDay mặc định là 0)
                    long newRowId = databaseHandler.insertTask(Integer.parseInt(taskId), taskName, 0, Integer.parseInt(progressPercent));

                    // Kiểm tra nếu insert thành công
                    if (newRowId != -1) {
                        Toast.makeText(TaskActivity.this, "Task added with ID: " + newRowId, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TaskActivity.this, "Error inserting task", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TaskActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ImageView home = findViewById(R.id.imageHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TaskActivity", "Home button clicked");
                Intent intent = new Intent(TaskActivity.this, MainPage.class);
                startActivity(intent);
            }
        });

        ImageView setting = findViewById(R.id.imageSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this, SettingPage.class);
                startActivity(intent);
            }
        });

        ImageView creat = findViewById(R.id.imageAdd);
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this, Feature.class);
                startActivity(intent);
            }
        });

        ImageView task = findViewById(R.id.imageTasks);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this, TaskActivity.class);
                startActivity(intent);

            }
        });




    }
}