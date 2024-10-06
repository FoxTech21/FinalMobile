package android.bignerdranch.projectmanagement.Activity;

import android.bignerdranch.projectmanagement.Adapter.OngoingAdapter;
import android.bignerdranch.projectmanagement.Adapter.OngoingAdapter_Search;
import android.bignerdranch.projectmanagement.Domain.OngioingDomain;
import android.bignerdranch.projectmanagement.Domain.OngoingDomain_Search;
import android.bignerdranch.projectmanagement.SQLiteOpenHelper.DatabaseHandler;
import android.bignerdranch.projectmanagement.R;
import android.bignerdranch.projectmanagement.databinding.ActivityMainPageBinding;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {
    private ActivityMainPageBinding binding;
    private OngoingAdapter adapterOngoing;
    private DatabaseHandler databaseHandler;
    private List<OngioingDomain> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainPageBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        initRecyclerView();
        databaseHandler = new DatabaseHandler(this);


        ImageView search = findViewById(R.id.imageSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, SearchPage.class);
                startActivity(intent);
            }
        });

        ImageView home = findViewById(R.id.imageHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, MainPage.class);
                startActivity(intent);
            }
        });

        ImageView setting = findViewById(R.id.imageSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, SettingPage.class);
                startActivity(intent);
            }
        });

        ImageView creat = findViewById(R.id.imageAdd);
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Feature.class);
                startActivity(intent);
            }
        });

        ImageView task = findViewById(R.id.imageTasks);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, TaskActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        // Lấy dữ liệu từ cơ sở dữ liệu
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        ArrayList<OngioingDomain> items = (ArrayList<OngioingDomain>) databaseHandler.getTaskDetails();

        // Kiểm tra nếu danh sách không rỗng trước khi gán cho adapter
        if (items != null && !items.isEmpty()) {
            binding.viewOngoing.setLayoutManager(new GridLayoutManager(this, 2));
            adapterOngoing = new OngoingAdapter(items);
            binding.viewOngoing.setAdapter(adapterOngoing);
        } else {
            // Nếu không có dữ liệu, bạn có thể hiển thị thông báo hoặc một giao diện khác
            Toast.makeText(this, "No tasks available", Toast.LENGTH_SHORT).show();
        }
    }
}