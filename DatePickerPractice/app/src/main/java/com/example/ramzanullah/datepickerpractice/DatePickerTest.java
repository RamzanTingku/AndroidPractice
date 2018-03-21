package com.example.ramzanullah.datepickerpractice;

import android.app.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.TimePickerDialog;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Locale;


public class DatePickerTest extends AppCompatActivity implements View.OnClickListener {

    private int year, month, day, hour, minute, year2, month2, day2, hour2, minute2;
    private Calendar calendar, calendar2;
    private Button btnDatePicker, btnDatePicker2, btnTimePicker, btnShowDiff;
    private boolean is24Hours, is24Hours2;
    private SimpleDateFormat sdfDate, sdfDate2, sdfTime;
    private final long threeDays = 3 * 24 * 60 * 60 * 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_test);
        btnDatePicker = (Button) findViewById(R.id.select_date);
        btnDatePicker2 = (Button) findViewById(R.id.select_date2);
        btnTimePicker = (Button) findViewById(R.id.select_time);
        btnShowDiff = (Button) findViewById(R.id.show_diff);


        calendar = Calendar.getInstance(Locale.getDefault());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        is24Hours = false;


        calendar2 = Calendar.getInstance(Locale.getDefault());
        year2 = calendar2.get(Calendar.YEAR);
        month2 = calendar2.get(Calendar.MONTH);
        day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        hour2 = calendar2.get(Calendar.HOUR);
        minute2 = calendar2.get(Calendar.MINUTE);
        is24Hours2 = false;


        btnDatePicker.setOnClickListener(this);
        btnDatePicker2.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case (R.id.select_date):

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, datelistener, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance(Locale.getDefault()).getTimeInMillis());
                datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance(Locale.getDefault()).getTimeInMillis() + threeDays);
                datePickerDialog.show();
                break;

            case (R.id.select_date2):

                DatePickerDialog datePickerDialog2 = new DatePickerDialog(this, datelistener2, year2, month2, day2);
                datePickerDialog2.getDatePicker().setMinDate(Calendar.getInstance(Locale.getDefault()).getTimeInMillis() + threeDays);
                datePickerDialog2.getDatePicker().setMaxDate(Calendar.getInstance(Locale.getDefault()).getTimeInMillis() + threeDays + threeDays);
                datePickerDialog2.show();
                break;


            case (R.id.select_time):
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, timelistener, hour, minute, is24Hours);
                timePickerDialog.show();
                break;
        }
    }

    private DatePickerDialog.OnDateSetListener datelistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            //btnDatePicker.setText(day + "/" + (month+1) + "/" + year);

            /*calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, (month));
            calendar.set(Calendar.DAY_OF_MONTH, day);*/
            calendar.set(year, month, day);

            sdfDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            btnDatePicker.setText(sdfDate.format(calendar.getTime()));

        }
    };

    private DatePickerDialog.OnDateSetListener datelistener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            //btnDatePicker.setText(day + "/" + (month+1) + "/" + year);

            /*calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, (month));
            calendar.set(Calendar.DAY_OF_MONTH, day);*/
            calendar2.set(year,month,day);

            sdfDate2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            btnDatePicker2.setText(sdfDate2.format(calendar2.getTime()));
        }
    };


    private TimePickerDialog.OnTimeSetListener timelistener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {

            /*calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, minute);*/
            calendar.set(0, 0, 0, hour, minute);
            sdfTime = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
            btnTimePicker.setText(sdfTime.format(calendar.getTime()));
        }
    };


    public void dateSelect(View view) {
        long milliSeconds1 = calendar.getTimeInMillis();
        long milliSeconds2 = calendar2.getTimeInMillis();
        long periodSeconds = (milliSeconds2 - milliSeconds1) / 1000;
        if (periodSeconds < 0) {
            periodSeconds *= (-1);
        }
        long elapsedDays = periodSeconds / 60 / 60 / 24;
        String diff = "" + elapsedDays;
        btnShowDiff.setText(diff);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        String storedDate = btnDatePicker.getText().toString();
        String storedDate2 = btnDatePicker2.getText().toString();
        String storedDiff = btnShowDiff.getText().toString();
        String storedTime = btnTimePicker.getText().toString();


        savedInstanceState.putString("date",storedDate);
        savedInstanceState.putString("date2",storedDate2);
        savedInstanceState.putString("diff",storedDiff);
        savedInstanceState.putString("time",storedTime);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String date= savedInstanceState.getString("date");
        String date2= savedInstanceState.getString("date2");
        String diff= savedInstanceState.getString("diff");
        String time= savedInstanceState.getString("time");

        btnDatePicker.setText(date);
        btnDatePicker2.setText(date2);
        btnShowDiff.setText(diff);
        btnTimePicker.setText(time);
    }
}
