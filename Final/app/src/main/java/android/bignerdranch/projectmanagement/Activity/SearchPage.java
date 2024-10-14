package android.bignerdranch.projectmanagement.Activity;

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
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import java.util.List;

public class SearchPage extends AppCompatActivity {

    private ActivitySearchPageBinding bindingsearch;
    private OngoingAdapter_Search adapterOngoing_search;
    private EditText edtTaskName;
    private static final int UPDATE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingsearch = ActivitySearchPageBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(bindingsearch.getRoot());

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        List<OngoingDomain_Search> taskListSearch = databaseHandler.getTaskSearch(String.valueOf(edtTaskName));

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
        edtTaskName = bindingsearch.editSearch; // Lấy EditText từ binding
        edtTaskName.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                // Lấy từ khóa tìm kiếm
                String query = edtTaskName.getText().toString();

                // Gọi phương thức tìm kiếm và cập nhật RecyclerView
                List<OngoingDomain_Search> result = databaseHandler.getTaskSearch(query);
                adapterOngoing_search.updateTaskList(result); // Cập nhật danh sách trong adapter

                return true; // Trả về true để ngăn chặn sự kiện mặc định
            }
            return false;
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
        Intent intent = new Intent(SearchPage.this, Update.class);
        intent.putExtra("taskId", item.getTaskId());
        intent.putExtra("taskName", item.getTaskName());
        intent.putExtra("startDate", item.getStartDate());
        intent.putExtra("endDate", item.getEndDate());
        intent.putExtra("devName", item.getDevName());
        intent.putExtra("progressPercent", item.getProgressPercent());
        startActivityForResult(intent, UPDATE_REQUEST_CODE); // Sử dụng startActivityForResult để nhận lại kết quả
    }

    // Khi trở lại từ Activity cập nhật
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Lấy lại danh sách nhiệm vụ từ database
            DatabaseHandler databaseHandler = new DatabaseHandler(this);
            List<OngoingDomain_Search> updatedTaskList = databaseHandler.getTaskSearch(edtTaskName.getText().toString());
            adapterOngoing_search.updateTaskList(updatedTaskList); // Cập nhật danh sách trong adapter
        }
    }

    // Phương thức xóa item
    // Phương thức xóa item
    private void deleteTask(OngoingDomain_Search item, int position) {
        DatabaseHandler db = new DatabaseHandler(this);
        // Xóa tất cả thông tin liên quan đến nhiệm vụ trong database
        db.deleteTaskByName(item.getTaskName()); // Xóa nhiệm vụ
        adapterOngoing_search.getItems().remove(position); // Xóa khỏi danh sách
        adapterOngoing_search.notifyItemRemoved(position); // Thông báo adapter cập nhật
    }

}