package android.bignerdranch.projectmanagement.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.bignerdranch.projectmanagement.R;
import android.view.View;
import android.widget.ImageView;

public class SettingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting_page);


        ImageView home = findViewById(R.id.imageHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingPage.this, MainPage.class);
                startActivity(intent);
            }
        });

        ImageView search = findViewById(R.id.imageSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingPage.this, SearchPage.class);
                startActivity(intent);
            }
        });
    }
}