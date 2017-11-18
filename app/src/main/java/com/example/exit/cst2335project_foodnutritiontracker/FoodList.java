package com.example.exit.cst2335project_foodnutritiontracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class FoodList extends Activity {

    protected static final String ACTIVITY_NAME = "FoodListViewActivity";

    private ListView listView;
    private String foodNameInfo;

    public static ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        listView = (ListView) findViewById(R.id.foodListView);

        final Button button_add = (Button) findViewById(R.id.buttonAdd);

        button_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FoodList.this, FoodAdd.class);
                startActivity(intent);
            }
        });


        Intent objIntent = getIntent();

        foodNameInfo = objIntent.getStringExtra("name");
        Log.i("111", "message: " + foodNameInfo);

        listItems.add(foodNameInfo);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.food_cell_layout, listItems));




    }

}
