package com.example.aneeli.dice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by aneeli on 3/26/2016.
 */
public class MyActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
    }
    public void MainActivity(View view) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);


    }
    public void about(View v){
        Intent startNewActivity = new Intent(this,about.class);
        //startActivity(new Intent(MyActivity.this, about.class));
        startActivity(startNewActivity);

    }

}
