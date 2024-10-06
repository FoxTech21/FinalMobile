package android.bignerdranch.projectmanagement.SQLiteOpenHelper;


import android.annotation.SuppressLint;
import android.bignerdranch.projectmanagement.Domain.OngioingDomain;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "ProjectManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "dev_task";
    private static final String TABLE1_NAME = "task";


//Table: devTask
    private static final String KEY_ID_DEV_TASK = "id";
    private static final String KEY_DEV_NAME = "devName";
    private static final String KEY_TASK_ID = "taskId";
    private static final String KEY_START_DATE = "startDate";
    private static final String KEY_END_DATE = "ENDtDate";

//Table: Task
    private static final String KEY_ID_TASK = "id";
    private static final String KEY_TASK_NAME = "taskName";
    private static final String KEY_ESTIMATE_DAY = "estimateDay";
    private static final String KEY_PROGRESS_PERCENT = "progressPercent";
    private static final String KEY_PICTURE = "picture";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_devTask_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT,%s TEXT)",
                TABLE_NAME, KEY_ID_DEV_TASK, KEY_DEV_NAME, KEY_TASK_ID, KEY_START_DATE,KEY_END_DATE);
        db.execSQL(create_devTask_table);
        String create_task_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE1_NAME, KEY_ID_TASK, KEY_TASK_NAME, KEY_ESTIMATE_DAY, KEY_PROGRESS_PERCENT,KEY_PICTURE);
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

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //input -> devTask:
    public long insertDevTask(String devName, String taskId, String startDate, String endDate) {
        // Lấy đối tượng ghi vào database
        SQLiteDatabase db = this.getWritableDatabase();

        // Tạo ContentValues để lưu các giá trị cột
        ContentValues values = new ContentValues();
        values.put(KEY_DEV_NAME, devName); // Gắn tên dev
        values.put(KEY_TASK_ID, taskId); // Gắn task ID
        values.put(KEY_START_DATE, startDate); // Gắn ngày bắt đầu
        values.put(KEY_END_DATE, endDate); // Gắn ngày kết thúc

        // Thực hiện insert và trả về ID của hàng mới được thêm
        long id = db.insert(TABLE_NAME, null, values);

        // Đóng database
        db.close();

        // Trả về ID tự động tăng của bản ghi mới
        return id;
    }


    //input -> Task:
    public long insertDevTask(String taskName, String estimateDay, int progressPercent, String pictureResource) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DEV_NAME, taskName);
        values.put(KEY_TASK_ID, estimateDay);
        values.put(KEY_PROGRESS_PERCENT,progressPercent);
        values.put(KEY_PICTURE, pictureResource);

        // Chèn dữ liệu vào bảng
        long id = db.insert(TABLE1_NAME, null, values);
        db.close();
        return id;
    }

    //Gắn value.
    @SuppressLint("Range")
    public List<OngioingDomain> getTaskDetails() {
        List<OngioingDomain> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + TABLE1_NAME + ".taskName, " + TABLE_NAME + ".startDate, " +
                TABLE1_NAME + ".progressPercent, " + TABLE1_NAME + ".picture " +
                "FROM " + TABLE_NAME + " INNER JOIN " + TABLE1_NAME +
                " ON " + TABLE_NAME + ".taskId = " + TABLE1_NAME + ".id";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String taskName = cursor.getString(cursor.getColumnIndex("taskName"));
                String startDate = cursor.getString(cursor.getColumnIndex("startDate"));
                int progressPercent = cursor.getInt(cursor.getColumnIndex("progressPercent"));
                String picture = cursor.getString(cursor.getColumnIndex("picture"));

                OngioingDomain task = new OngioingDomain(taskName, startDate, progressPercent, picture);
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return taskList;
    }
}
