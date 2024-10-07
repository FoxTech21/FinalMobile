package android.bignerdranch.projectmanagement.Activity;

import android.bignerdranch.projectmanagement.Adapter.OngoingAdapter_Search;
import android.bignerdranch.projectmanagement.Domain.OngoingDomain_Search;
import android.bignerdranch.projectmanagement.SQLiteOpenHelper.DatabaseHandler;
import android.bignerdranch.projectmanagement.databinding.ActivitySearchPageBinding;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.bignerdranch.projectmanagement.R;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AppCompatActivity {

    private ActivitySearchPageBinding bindingsearch;
    private OngoingAdapter_Search adapterOngoing_search;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingsearch = ActivitySearchPageBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(bindingsearch.getRoot());

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        List<OngoingDomain_Search> taskListSearch = databaseHandler.getTaskSearch();

        initRecyclerView(taskListSearch);


        ImageView home = findViewById(R.id.imageHome);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(SearchPage.this, MainPage.class);
            startActivity(intent);
        });

        ImageView setting = findViewById(R.id.imageSetting);
        setting.setOnClickListener(v -> {
            Intent intent = new Intent(SearchPage.this, SettingPage.class);
            startActivity(intent);
        });

        Button update = findViewById(R.id.btnUpdate);
        update.setOnClickListener(v -> {
            Intent intent = new Intent(SearchPage.this, Update.class);
            startActivity(intent);
        });
    }

    private void initRecyclerView(List<OngoingDomain_Search> taskListSearch) {
        bindingsearch.viewongoingSearch.setLayoutManager(new GridLayoutManager(this, 1));
        adapterOngoing_search = new OngoingAdapter_Search(taskListSearch);
        bindingsearch.viewongoingSearch.setAdapter(adapterOngoing_search);
    }

//private void initRecyclerView() {
//
//    ArrayList<OngoingDomain_Search> items = new ArrayList<>();
//    items.add(new OngoingDomain_Search("Order List","28/04/2024",50,"30/04/2024","Kaushik"));
//    items.add(new OngoingDomain_Search("Order Detail","2/05/2024",80,"04/05/2024"," Khilan"));
//    items.add(new OngoingDomain_Search("Product List","01/05/2024",63,"03/05/2024","Ramesh"));
//    items.add(new OngoingDomain_Search("Product Detail","06/05/2024",72,"08/05/2024","Kaushik"));
//    bindingsearch.viewongoingSearch.setLayoutManager(new GridLayoutManager(this,1));
//    adapterOngoing_search = new OngoingAdapter_Search(items);
//    bindingsearch.viewongoingSearch.setAdapter(adapterOngoing_search);
//    }
}