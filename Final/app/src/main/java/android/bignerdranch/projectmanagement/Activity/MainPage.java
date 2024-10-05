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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {
    private ActivityMainPageBinding binding;
    private OngoingAdapter adapterOngoing;
    private DatabaseHandler dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainPageBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        initRecyclerView();
        dbHelper = new DatabaseHandler(this);

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
    }

    private void initRecyclerView() {
        ArrayList<OngioingDomain> items = new ArrayList<>();
        items.add(new OngioingDomain("Order List","28/04/2024",50,"ongoing1"));
        items.add(new OngioingDomain("Order Detail","2/05/2024",80,"ongoing2"));
        items.add(new OngioingDomain("Product List","01/05/2024",63,"ongoing3"));
        items.add(new OngioingDomain("Product Detail","06/05/2024",72,"ongoing4"));
        binding.viewOngoing.setLayoutManager(new GridLayoutManager(this,2));
        adapterOngoing = new OngoingAdapter(items);
        binding.viewOngoing.setAdapter(adapterOngoing);
    }
}