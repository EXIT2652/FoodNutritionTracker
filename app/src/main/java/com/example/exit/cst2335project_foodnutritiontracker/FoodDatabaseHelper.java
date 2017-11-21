package com.example.exit.cst2335project_foodnutritiontracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FoodInfo.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "foodInfo";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FOOD_NAME = "foodName";
    public static final String COLUMN_SERVINGS = "foodServings";
    public static final String COLUMN_CALORIES = "calories";
    public static final String COLUMN_FAT = "fat";
    public static final String COLUMN_CARBOHYDRATE = "carbohydrate";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";

    public SQLiteDatabase database;

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_FOOD_NAME + " TEXT," +
            COLUMN_SERVINGS + " INTEGER," +
            COLUMN_CALORIES + " INTEGER," +
            COLUMN_FAT + " INTEGER," +
            COLUMN_CARBOHYDRATE + " INTEGER," +
            COLUMN_DATE + " TEXT," +
            COLUMN_TIME + " TEXT" +
            ")";

    public FoodDatabaseHelper (Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    public void openDatabase() {
        database = this.getWritableDatabase();
    }

    public void closeDatabase() {
        if(database != null && database.isOpen()){
            database.close();
        }
    }

    public void insertFoodItem(String foodName, String foodServings, String calories, String fat, String carbohydrate, String date, String time) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOOD_NAME, foodName);
        values.put(COLUMN_SERVINGS, Integer.parseInt(foodServings));
        values.put(COLUMN_CALORIES, Integer.parseInt(calories));
        values.put(COLUMN_FAT, Integer.parseInt(fat));
        values.put(COLUMN_CALORIES, Integer.parseInt(carbohydrate));
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        database.insert(TABLE_NAME, null, values);
    }

    public void deleteLastItem(String id) {
        getWritableDatabase().execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id);
    }

    public void updateFoodItem(String id, String foodName, String foodServings, String calories, String fat, String carbohydrate, String date, String time) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOOD_NAME, foodName);
        values.put(COLUMN_SERVINGS, Integer.parseInt(foodServings));
        values.put(COLUMN_CALORIES, Integer.parseInt(calories));
        values.put(COLUMN_FAT, Integer.parseInt(fat));
        values.put(COLUMN_CALORIES, Integer.parseInt(carbohydrate));
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        database.update(TABLE_NAME, values, COLUMN_ID + " = " + id, null);
    }

    public Cursor getRecords() {
        return database.query(TABLE_NAME, null, null, null, null, null, null);
    }
}
