package com.example.webapiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String url="https://book-brokers.000webhostapp.com/register2.php";
    Button login,reg;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login);
        reg=findViewById(R.id.register);

        pref = getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        editor = pref.edit();

        String login_flag = pref.getString("login", "no");
        if (login_flag.equals("yes")) {
            Intent i=new Intent(MainActivity.this,SecondActivity.class);
            startActivity(i);
            finishAffinity();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                i.putExtra("value",1);
                startActivity(i);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                i.putExtra("value",2);
                startActivity(i);
            }
        });

    }
}