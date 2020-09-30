package com.example.webapiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    Button logout;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    LinearLayout mainpage;
    RecyclerView rv;

    String[] text={"Cycle Crossing Sign","Ferry Sign","Left Hair Pin Bend Sign","Left Hand Curve Sign","Narrow Bridge Sign",
            "Narrow Road Ahead","Pedestrian Crossing","Right Hair Pin Bend Sign","Right Hand Curve Sign","School Ahead Sign",
            "Slippery Road"};

    Integer[] pics={R.drawable.cyclecrossing,R.drawable.ferry,R.drawable.lefthairpinbend,R.drawable.lefthandcurve,
                R.drawable.narrowbridge,R.drawable.narrowroadahead,R.drawable.pedestriancrossing,R.drawable.righthairpinbend,
            R.drawable.righthandcurve,R.drawable.schoolahead,R.drawable.slipperyroad};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        logout=findViewById(R.id.logout);
        mainpage=findViewById(R.id.mainpage);
        rv=findViewById(R.id.rv);

        pref = getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        editor = pref.edit();

        MyAdapter adapter = new MyAdapter(text,pics,this);

        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.setAdapter(adapter);


//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logout(v);
//            }
//        });
    }

    public void logout(View view){
        //logging out from account
        FirebaseAuth.getInstance().signOut();

        editor.putString("login","no");
        editor.apply();

        Intent i=new Intent(getApplicationContext(),SecondActivity.class);
        i.putExtra("value",1);
        startActivity(i);
        finishAffinity();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout){
            logout(mainpage);
        }
        return super.onOptionsItemSelected(item);
    }

}