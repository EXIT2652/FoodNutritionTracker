package com.example.exit.cst2335project_foodnutritiontracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;

public class FoodList extends Activity {

    private ListView listView;
    private String foodNameInfo;
    private ImageView buttonAdd;
    private ArrayList<String> listItems;
    private FoodListAdapter adapter;
    private View view;

    private FoodDatabaseHelper foodDBHelper;


    private class List implements Comparable<List> {
        private int trackerId;
        private String item, servings, calories, fat, carbohydrate, date, time;

        public int getTrackerId() {
            return trackerId;
        }

        public String getItem() {
            return item;
        }

        public String getServings() {
            return servings;
        }

        public String getCalories() {
            return calories;
        }

        public String getFat() {
            return fat;
        }

        public String getCarbohydrate() {
            return carbohydrate;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public List(int trackerId, String item, String servings, String calories, String fat, String carbohydrate, String date, String time) {
            this.trackerId = trackerId;
            this.item = item;
            this.servings = servings;
            this.calories = calories;
            this.fat = fat;
            this.carbohydrate = carbohydrate;
            this.date = date;
            this.time = time;
        }

        @Override
        public int compareTo(List list) {
            return(list.getDate() + " " + list.getTime()).compareTo(getDate() + " " + getTime());
        }
    }



    class FoodListAdapter extends ArrayAdapter<List> {

        public FoodListAdapter(Context context, ArrayList<List> list) {
            super(context, R.layout.food_cell_layout, list);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
//        Log.i(ACTIVITY_NAME, "In onCreate()");

        listView = (ListView) findViewById(R.id.foodListView);
        listItems = new ArrayList<String>();




        adapter = new ArrayAdapter<String>(getContext(), listItems);
        buttonAdd = (ImageView) findViewById(R.id.buttonAdd);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FoodList.this, FoodAdd.class);
                startActivityForResult(intent, 10);
            }
        });

        foodDBHelper = new FoodDatabaseHelper(this);
        foodDBHelper.openDatabase();
        displaySQL();


    }
    protected void onActivityResult(int requestCod, int responseCode, Intent data){
        if(responseCode == 1){
            foodNameInfo = data.getStringExtra("name");
            listItems.add(foodNameInfo);
            listView.setAdapter(new ArrayAdapter<String>(this, R.layout.food_cell_layout, listItems));
        }
    }

    private void displaySQL() {
        Cursor cursor = foodDBHelper.getRecords();
        if (cursor.moveToFirst()) {

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        foodDBHelper.closeDatabase();
    }

}
