package com.example.webapiapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                new RegisterFragment().dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        },year,month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.YEAR,year);
//        calendar.set(Calendar.MONTH,month);
//        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = dayOfMonth+"/"+(month+1)+"/"+year;
        new RegisterFragment().dob.setText(currentDateString);
    }
}
