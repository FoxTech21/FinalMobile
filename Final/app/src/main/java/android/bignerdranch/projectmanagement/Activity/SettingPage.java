package android.bignerdranch.projectmanagement.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.bignerdranch.projectmanagement.R;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SettingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting_page);


        CheckBox checkBox = findViewById(R.id.chkDisplay);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Lưu trạng thái checkbox vào SharedPreferences
            SharedPreferences sharedPref = getSharedPreferences("TaskPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("isEstimateDayHidden", isChecked); // Lưu trạng thái checkbox
            editor.apply();
        });

        ImageView home = findViewById(R.id.imageHome);
                home.setOnClickListener(v -> {
            Intent intent = new Intent(SettingPage.this, MainPage.class);
            startActivity(intent);
        });

        ImageView search = findViewById(R.id.imageSearch);
        search.setOnClickListener(v -> {
            Intent intent = new Intent(SettingPage.this, SearchPage.class);
            startActivity(intent);
        });

        ImageView task = findViewById(R.id.imageTasks);
        task.setOnClickListener(v -> {
            Intent intent = new Intent(SettingPage.this, TaskActivity.class);
            startActivity(intent);
        });

        ImageView add = findViewById(R.id.imageAdd);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(SettingPage.this, Feature.class);
            startActivity(intent);
        });
    }
}