package android.bignerdranch.projectmanagement.Activity;

import android.annotation.SuppressLint;
import android.bignerdranch.projectmanagement.Adapter.OngoingAdapter_Search;
import android.bignerdranch.projectmanagement.Domain.OngoingDomain_Search;
import android.bignerdranch.projectmanagement.SQLiteOpenHelper.DatabaseHandler;
import android.bignerdranch.projectmanagement.databinding.ActivitySearchPageBinding;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.bignerdranch.projectmanagement.R;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

        bindingsearch.imageHome.setOnClickListener(v -> {
            Intent intent = new Intent(SearchPage.this, MainPage.class);
            startActivity(intent);
        });

        bindingsearch.imageSetting.setOnClickListener(v -> {
            Intent intent = new Intent(SearchPage.this, SettingPage.class);
            startActivity(intent);
        });

        bindingsearch.imageTasks.setOnClickListener(v -> {
            Intent intent = new Intent(SearchPage.this, TaskActivity.class);
            startActivity(intent);
        });


        bindingsearch.imageAdd.setOnClickListener(v -> {
            Intent intent = new Intent(SearchPage.this, Feature.class);
            startActivity(intent);
        });

    }

    private void initRecyclerView(List<OngoingDomain_Search> taskListSearch) {
        bindingsearch.viewongoingSearch.setLayoutManager(new GridLayoutManager(this, 1));

        // Khởi tạo adapter với listener cho sự kiện nhấn giữ
        adapterOngoing_search = new OngoingAdapter_Search(taskListSearch, (item, position) -> {
            showOptionsDialog(item, position);
        });

        bindingsearch.viewongoingSearch.setAdapter(adapterOngoing_search);
    }

    // Hiển thị dialog để chọn hành động
    private void showOptionsDialog(OngoingDomain_Search item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options");
        String[] options = {"Update", "Remove"};
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                // Thực hiện cập nhật item
                showUpdateDialog(item, position);
            } else if (which == 1) {
                // Thực hiện xóa item
                deleteTask(item, position);
            }
        });
        builder.show();
    }

    // Phương thức cập nhật item (hiển thị dialog hoặc điều hướng đến trang cập nhật)
    private void showUpdateDialog(OngoingDomain_Search item, int position) {
        // Xử lý cập nhật item (chuyển đến activity khác hoặc hiển thị dialog để cập nhật)
        Intent intent = new Intent(SearchPage.this, Update.class);
        intent.putExtra("taskId", item.getTaskId());
        intent.putExtra("taskName", item.getTaskName());
        intent.putExtra("startDate", item.getStartDate());
        intent.putExtra("endDate", item.getEndDate());
        intent.putExtra("devName", item.getDevName());
        intent.putExtra("progressPercent", item.getProgressPercent());
        startActivity(intent);
    }

    // Phương thức xóa item
    private void deleteTask(OngoingDomain_Search item, int position) {
        DatabaseHandler db = new DatabaseHandler(this);
        // Giả sử có phương thức xóa bằng taskName
        db.deleteTaskByName(item.getTaskName());
        // Xóa item khỏi danh sách và thông báo cho adapter
        adapterOngoing_search.getItems().remove(position);
        adapterOngoing_search.notifyItemRemoved(position);
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