package android.bignerdranch.projectmanagement.SQLiteOpenHelper;


import android.annotation.SuppressLint;
import android.bignerdranch.projectmanagement.Domain.OngioingDomain;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String create_devTask_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT,%s TEXT)",
                TABLE_NAME, KEY_ID_DEV_TASK, KEY_DEV_NAME, KEY_TASK_ID, KEY_START_DATE,KEY_END_DATE);
        db.execSQL(create_devTask_table);
        String create_task_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
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


    // Chức năng: thêm, xóa, sửa
    public void addDevTask(String devName, String taskId, String startDate, String endDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DEV_NAME, devName);
        values.put(KEY_TASK_ID, taskId);
        values.put(KEY_START_DATE, startDate);
        values.put(KEY_END_DATE, endDate);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public void addTask(String taskName, String estimateDay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TASK_NAME, taskName);
        values.put(KEY_ESTIMATE_DAY, estimateDay);

        db.insert(TABLE1_NAME, null, values);
        db.close();
    }

    public void deleteDevTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID_DEV_TASK + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE1_NAME, KEY_ID_TASK + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


    public void updateTask(int id, String taskName, String estimateDay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DEV_NAME, taskName);
        values.put(KEY_TASK_ID, estimateDay);

        db.update(TABLE1_NAME, values, KEY_ID_TASK + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateDevTask(int id, String devName, String taskId, String startDate, String endDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DEV_NAME, devName);
        values.put(KEY_TASK_ID, taskId);
        values.put(KEY_START_DATE, startDate);
        values.put(KEY_END_DATE, endDate);

        db.update(TABLE_NAME, values, KEY_ID_DEV_TASK + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //input -> devTask:
    public void insertdevTaskData() {
        SQLiteDatabase db = this.getWritableDatabase();


        // Dữ liệu mẫu
        String[][] sampleData = {
                {"1", "Ramesh", "3", "2024/5/1", "2024/5/3"},
                {"2", "Khilan ", "2", "2024/5/2", "2024/5/4"},
                {"3", "Kaushik ", "1", "2024/4/28", "2024/4/30"},
                {"4", "Kaushik", "4", "2024/5/6", "2024/5/8"},
                {"5", "Superman", "5", "2024/5/3", "2024/5/5"}
        };

        for (String[] data : sampleData) {
            ContentValues values = new ContentValues();
            values.put(KEY_ID_DEV_TASK, Integer.parseInt(data[0]));
            values.put(KEY_DEV_NAME, data[1]);
            values.put(KEY_TASK_ID, data[2]);
            values.put(KEY_START_DATE, data[3]);
            values.put(KEY_END_DATE, data[4]);

            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }

    //ínsert: Task
    public void insertTaskData() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Dữ liệu mẫu
        String[][] sampleData = {
                {"1", "Order list", "5", "40", "@drawable/ongoing1"},
                {"2", "Order detail", "3", "50", "@drawable/ongoing2"},
                {"3", "Product list", "3", "20", "@drawable/ongoing3"},
                {"4", "Product Detail", "3", "78", "@drawable/ongoing4"},
                {"5", "Coupon list", "3", "86", "@drawable/ongoing1"}
        };

        for (String[] data : sampleData) {
            ContentValues values = new ContentValues();
            values.put(KEY_ID_DEV_TASK, Integer.parseInt(data[0]));
            values.put(KEY_DEV_NAME, data[1]);
            values.put(KEY_TASK_ID, data[2]);
            values.put(KEY_START_DATE, data[3]);
            values.put(KEY_END_DATE, data[4]);

            db.insert(TABLE1_NAME, null, values);
        }

        db.close();
    }

    //Gắn value.
    @SuppressLint("Range")
    public List<OngioingDomain> getTaskDetails() {
        List<OngioingDomain> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + TABLE_NAME + ".taskName, " + TABLE1_NAME + ".startDate, " + TABLE1_NAME + ".progressPercent, " + TABLE1_NAME + ".picture " +
                "FROM " + TABLE_NAME + " INNER JOIN " + TABLE1_NAME + " ON " + TABLE_NAME + ".taskId = " + TABLE1_NAME + ".id";
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
