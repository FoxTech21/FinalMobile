package android.bignerdranch.projectmanagement.Activity;

import android.bignerdranch.projectmanagement.Adapter.OngoingAdapter;
import android.bignerdranch.projectmanagement.Domain.OngioingDomain;
import android.bignerdranch.projectmanagement.SQLiteOpenHelper.DatabaseHandler;
import android.bignerdranch.projectmanagement.R;
import android.bignerdranch.projectmanagement.databinding.ActivityMainPageBinding;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

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


        databaseHandler = new DatabaseHandler(this);
        taskList = databaseHandler.getTaskDetails();

        initRecyclerView(taskList);

        LinearLayout search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, SearchPage.class);
                startActivity(intent);
            }
        });

        LinearLayout home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, MainPage.class);
                startActivity(intent);
            }
        });

        LinearLayout setting = findViewById(R.id.setting);
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

        LinearLayout task = findViewById(R.id.task);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, TaskActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initRecyclerView(List<OngioingDomain> taskList) {
        binding.viewOngoing.setLayoutManager(new GridLayoutManager(this, 1));
        adapterOngoing = new OngoingAdapter((ArrayList<OngioingDomain>) taskList);
        binding.viewOngoing.setAdapter(adapterOngoing);
    }
}