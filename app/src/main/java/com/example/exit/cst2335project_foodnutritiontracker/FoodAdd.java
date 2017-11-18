package com.example.exit.cst2335project_foodnutritiontracker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FoodAdd extends Activity {

    /*    protected static final String ACTIVITY_NAME = "AddItemsActivity";
        private String foodAdd = FoodAddActivity.class.getSimpleName();*/
    private EditText addFoodName;
    private EditText addServings;
    private String addFoodAndServings;
    private EditText addDate;
    private EditText addTime;
    private Button buttonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_add);

        addFoodName = (EditText) findViewById(R.id.addFoodName);
        addServings = (EditText) findViewById(R.id.addServings);
        addDate = (EditText) findViewById(R.id.addDate);
        addTime = (EditText) findViewById(R.id.addTime);
        buttonAdd = (Button) findViewById(R.id.addButton);

        final Calendar myCalendar = Calendar.getInstance();
        final Calendar myCurrentTime = Calendar.getInstance();

        //Pop up date picker dialog after user clicks Date edit text
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String dateFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.CANADA);
                addDate.setText(sdf.format(myCalendar.getTime()));
            }
        };

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FoodAdd.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Pop up time picker dialog after user clicks Time edit text
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hour = myCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = myCurrentTime.get(Calendar.MINUTE);

                TimePickerDialog myTimePicker;
                myTimePicker = new TimePickerDialog(FoodAdd.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        addTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);
                myTimePicker.show();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodAdd.this, FoodList.class);

                if(addServings.getText().toString().equals("1"))
                    addFoodAndServings = addServings.getText().toString() + " " + addFoodName.getText().toString();
                else
                    addFoodAndServings = addServings.getText().toString() + " " + addFoodName.getText().toString() + "s";
                intent.putExtra("name", addFoodAndServings);

                startActivity(intent);//Go back to FoodFragmentListView class after user presses Add button
            }
        });

    }

}
