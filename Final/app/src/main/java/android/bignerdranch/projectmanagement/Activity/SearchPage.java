package android.bignerdranch.projectmanagement.Activity;

import android.bignerdranch.projectmanagement.Adapter.OngoingAdapter_Search;
import android.bignerdranch.projectmanagement.Domain.OngoingDomain_Search;
import android.bignerdranch.projectmanagement.databinding.ActivitySearchPageBinding;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.bignerdranch.projectmanagement.R;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

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

        initRecyclerView();


        ImageView home = findViewById(R.id.imageHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, MainPage.class);
                startActivity(intent);
            }
        });

        ImageView setting = findViewById(R.id.imageSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, SettingPage.class);
                startActivity(intent);
            }
        });

        Button update = findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, Update.class);
                startActivity(intent);
            }
        });
    }


private void initRecyclerView() {

    ArrayList<OngoingDomain_Search> items = new ArrayList<>();
    items.add(new OngoingDomain_Search("Order List","28/04/2024",50,"ongoing1","30/04/2024","Kaushik"));
    items.add(new OngoingDomain_Search("Order Detail","2/05/2024",80,"ongoing2","04/05/2024"," Khilan"));
    items.add(new OngoingDomain_Search("Product List","01/05/2024",63,"ongoing3","03/05/2024","Ramesh"));
    items.add(new OngoingDomain_Search("Product Detail","06/05/2024",72,"ongoing4","08/05/2024","Kaushik"));
    bindingsearch.viewongoingSearch.setLayoutManager(new GridLayoutManager(this,1));
    adapterOngoing_search = new OngoingAdapter_Search(items);
    bindingsearch.viewongoingSearch.setAdapter(adapterOngoing_search);
    }
}