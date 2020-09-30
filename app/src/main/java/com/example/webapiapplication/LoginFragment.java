package com.example.webapiapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {
    EditText emailid,password;
    Button login;
    ProgressBar pb1;

    FirebaseAuth fAuth;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);

        emailid=view.findViewById(R.id.email_id);
        password= view.findViewById(R.id.password);
        login=view.findViewById(R.id.login);
        pb1=view.findViewById(R.id.pb1);

        pref = getActivity().getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        editor = pref.edit();

        fAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Email_id=emailid.getText().toString();
                String Pswd2=password.getText().toString();
                if(Email_id.isEmpty()){
                    emailid.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(Pswd2)){
                    password.setError("Password is Required");
                    return;
                }
                if(Pswd2.length()<8){
                    password.setError("Password must be at least of 8 characters");
                    return;
                }

                pb1.setVisibility(View.VISIBLE);

                //authenticate the user
                fAuth.signInWithEmailAndPassword(Email_id,Pswd2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "Logged in Successfully", Toast.LENGTH_SHORT).show();

                            editor.putString("login","yes");
                            editor.apply();

                            startActivity(new Intent(getActivity(),MainActivity2.class));
                            getActivity().finishAffinity();
                        }
                        else{
                            Toast.makeText(getActivity(), "Error found: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pb1.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
        return  view;
    }
}