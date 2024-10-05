package android.bignerdranch.projectmanagement.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.bignerdranch.projectmanagement.R;
import android.view.View;
import android.widget.ImageView;

public class Feature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feature);




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
    }
}