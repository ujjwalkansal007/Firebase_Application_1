package com.example.webapiapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Calendar;


public class RegisterFragment extends Fragment {

    EditText name,email,phone,pswd,cnf,dob;
    ProgressBar pb;
    FirebaseAuth fAuth;
    Button regs;

    DatePickerDialog datepicker;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register, container, false);
        name = view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        phone=view.findViewById(R.id.phone);
        pswd= view.findViewById(R.id.pswd);
        cnf=view.findViewById(R.id.cnfpswd);
        dob=view.findViewById(R.id.dob);

        pb=view.findViewById(R.id.prog);

        fAuth= FirebaseAuth.getInstance();
        regs= view.findViewById(R.id.regs);

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getActivity(),MainActivity2.class));
            getActivity().finishAffinity();
        }

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogFragment datePicker= new DatePickerFragment();
//                datePicker.show(getFragmentManager(),"Date Picker");

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.show();
            }
        });


        regs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=name.getText().toString();
                String Email=email.getText().toString().trim();
                String Phone=phone.getText().toString().trim();
                String Pswd=pswd.getText().toString();
                String Cnf=cnf.getText().toString();
                String Dob=dob.getText().toString();

                if(TextUtils.isEmpty(Email)){
                    email.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(Name)){
                    name.setError("Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(Phone)){
                    phone.setError("Phone is Required");
                    return;
                }
                if(TextUtils.isEmpty(Pswd)){
                    pswd.setError("Password is Required");
                    return;
                }
                if(Phone.length()>10 || Phone.length()<10){
                    phone.setError("Incorrect Phone Number");
                    return;
                }
                if(!Cnf.equals(Pswd)){
                    cnf.setError("Password does not match");
                    return;
                }
                if(Pswd.length()<8){
                    pswd.setError("Password must be at least of 8 characters");
                    return;
                }

                pb.setVisibility(View.VISIBLE);
                //Register the user in firebase

                fAuth.createUserWithEmailAndPassword(Email,Pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(),MainActivity2.class));
                            getActivity().finishAffinity();
                        }
                        else{
                            Toast.makeText(getActivity(), "Some Error has been Occured"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        return view;
    }

}