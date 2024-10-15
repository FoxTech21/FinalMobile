package android.bignerdranch.projectmanagement.SQLiteOpenHelper;

import android.annotation.SuppressLint;
import android.bignerdranch.projectmanagement.Domain.OngioingDomain;
import android.bignerdranch.projectmanagement.Domain.OngoingDomain_Search;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ProjectManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "dev_task";
    private static final String TABLE1_NAME = "task";

    // Table: devTask
    private static final String KEY_ID_DEV_TASK = "id";
    private static final String KEY_DEV_NAME = "devName";
    private static final String KEY_TASK_ID = "taskId";
    private static final String KEY_START_DATE = "startDate";
    private static final String KEY_END_DATE = "ENDtDate";

    // Table: Task
    private static final String KEY_ID_TASK = "taskID";
    private static final String KEY_TASK_NAME = "taskName";
    private static final String KEY_ESTIMATE_DAY = "estimateDay";
    private static final String KEY_PROGRESS_PERCENT = "progressPercent";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_devTask_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, KEY_ID_DEV_TASK, KEY_DEV_NAME, KEY_TASK_ID, KEY_START_DATE, KEY_END_DATE);
        db.execSQL(create_devTask_table);
        String create_task_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)",
                TABLE1_NAME, KEY_ID_TASK, KEY_TASK_NAME, KEY_ESTIMATE_DAY, KEY_PROGRESS_PERCENT);
        db.execSQL(create_task_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_devTask_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_devTask_table);
        String drop_task_table = String.format("DROP TABLE IF EXISTS %s", TABLE1_NAME);
        db.execSQL(drop_task_table);
        onCreate(db);
    }

    // Input -> devTask
    public long insertDevTask(String devName, int taskId, String startDate, String endDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DEV_NAME, devName); // Gắn tên dev
        values.put(KEY_TASK_ID, taskId); // Gắn task ID
        values.put(KEY_START_DATE, startDate); // Gắn ngày bắt đầu
        values.put(KEY_END_DATE, endDate); // Gắn ngày kết thúc

        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    // Input -> Task
    public long insertTask(int taskID, String taskName, int estimateDay, int progressPercent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TASK_ID, taskID);
        values.put(KEY_TASK_NAME, taskName);
        values.put(KEY_ESTIMATE_DAY, estimateDay);
        values.put(KEY_PROGRESS_PERCENT, progressPercent);

        long id = db.insert(TABLE1_NAME, null, values);
        db.close();
        return id;
    }

    // Gắn giá trị
    @SuppressLint("Range")
    public List<OngioingDomain> getTaskDetails() {
        List<OngioingDomain> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + TABLE1_NAME + ".taskName, "
                + TABLE_NAME + ".startDate, "
                + TABLE1_NAME + ".taskID, "
                + TABLE_NAME + ".devName, "
                + TABLE_NAME + ".ENDtDate, "
                + TABLE1_NAME + ".estimateDay, "
                + TABLE1_NAME + ".progressPercent " +

                "FROM " + TABLE_NAME + " INNER JOIN " + TABLE1_NAME +
                " ON " + TABLE_NAME + ".taskId = " + TABLE1_NAME + ".taskID";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int taskID = cursor.getInt(cursor.getColumnIndex("taskID"));
                String taskName = cursor.getString(cursor.getColumnIndex("taskName"));
                String startDate = cursor.getString(cursor.getColumnIndex("startDate"));
                int progressPercent = cursor.getInt(cursor.getColumnIndex("progressPercent"));
                int estimateDay = Integer.parseInt(cursor.getString(cursor.getColumnIndex("estimateDay")));
                String devName = cursor.getString(cursor.getColumnIndex("devName"));
                String endDate = cursor.getString(cursor.getColumnIndex("ENDtDate"));

                OngioingDomain task = new OngioingDomain(taskID, taskName, startDate, endDate, estimateDay, devName, progressPercent);
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return taskList;
    }

    @SuppressLint("Range")
    public List<OngoingDomain_Search> getTaskSearch(String content) {
        List<OngoingDomain_Search> taskListSearch = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + TABLE1_NAME + ".taskName, "
                + TABLE_NAME + ".startDate, "
                + TABLE_NAME + ".ENDtDate, "
                + TABLE_NAME + ".devName, "
                + TABLE1_NAME + ".progressPercent, "
                + TABLE1_NAME + ".taskID " +
                "FROM " + TABLE_NAME + " INNER JOIN " + TABLE1_NAME +
                " ON " + TABLE_NAME + ".taskId = " + TABLE1_NAME + ".taskID" +
                " WHERE " + TABLE1_NAME + ".taskName LIKE ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{"%" + content + "%"});

        if (cursor.moveToFirst()) {
            do {
                int taskId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("taskID")));
                String taskName = cursor.getString(cursor.getColumnIndex("taskName"));
                String startDate = cursor.getString(cursor.getColumnIndex("startDate"));
                String endDate = cursor.getString(cursor.getColumnIndex("ENDtDate"));
                String devName = cursor.getString(cursor.getColumnIndex("devName"));
                int progressPercent = cursor.getInt(cursor.getColumnIndex("progressPercent"));

                OngoingDomain_Search task = new OngoingDomain_Search(taskId, taskName, startDate, progressPercent, endDate, devName);
                taskListSearch.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return taskListSearch;
    }

    // Thêm phương thức getTaskDetail
    @SuppressLint("Range")
    public List<OngioingDomain> getTaskDetail() {
        List<OngioingDomain> taskDetailsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Truy vấn để lấy các thông tin chi tiết từ cả hai bảng
        String selectQuery = "SELECT " +
                TABLE1_NAME + ".taskID, " +
                TABLE1_NAME + ".taskName, " +
                TABLE1_NAME + ".estimateDay, " +
                TABLE1_NAME + ".progressPercent, " +
                TABLE_NAME + ".devName, " +
                TABLE_NAME + ".startDate, " +
                TABLE_NAME + ".ENDtDate " +
                "FROM " + TABLE1_NAME +
                " INNER JOIN " + TABLE_NAME +
                " ON " + TABLE_NAME + ".taskId = " + TABLE1_NAME + ".taskID";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt qua tất cả các hàng trong kết quả truy vấn
        if (cursor.moveToFirst()) {
            do {
                int taskId = cursor.getInt(cursor.getColumnIndex("taskID"));
                String taskName = cursor.getString(cursor.getColumnIndex("taskName"));
                int estimateDay = cursor.getInt(cursor.getColumnIndex("estimateDay"));
                int progressPercent = cursor.getInt(cursor.getColumnIndex("progressPercent"));
                String devName = cursor.getString(cursor.getColumnIndex("devName"));
                String startDate = cursor.getString(cursor.getColumnIndex("startDate"));
                String endDate = cursor.getString(cursor.getColumnIndex("ENDtDate"));

                // Tạo đối tượng OngioingDomain và thêm vào danh sách
                OngioingDomain taskDetail = new OngioingDomain(
                        taskId, taskName, startDate, endDate, estimateDay, devName, progressPercent);
                taskDetailsList.add(taskDetail);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return taskDetailsList;
    }

    public void deleteTaskByName(String taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE1_NAME, "taskName = ?", new String[]{taskName});
        db.close();
    }

    // Hàm cập nhật task trong cơ sở dữ liệu
    public int updateTask(int taskId, String devName, String startDate, String endDate, String taskName, int progressPercent) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Cập nhật bảng task
        ContentValues taskValues = new ContentValues();
        taskValues.put(KEY_TASK_NAME, taskName);
        taskValues.put(KEY_PROGRESS_PERCENT, progressPercent);

        // Cập nhật bảng dev_task
        ContentValues devTaskValues = new ContentValues();
        devTaskValues.put(KEY_DEV_NAME, devName);
        devTaskValues.put(KEY_START_DATE, startDate);
        devTaskValues.put(KEY_END_DATE, endDate);

        // Cập nhật bảng task theo taskId
        int taskUpdate = db.update(TABLE1_NAME, taskValues, KEY_ID_TASK + " = ?", new String[]{String.valueOf(taskId)});

        // Cập nhật bảng dev_task theo taskId
        int devTaskUpdate = db.update(TABLE_NAME, devTaskValues, KEY_TASK_ID + " = ?", new String[]{String.valueOf(taskId)});

        // Đóng database
        db.close();

        // Trả về tổng số dòng đã được cập nhật
        return taskUpdate + devTaskUpdate;
    }
}
