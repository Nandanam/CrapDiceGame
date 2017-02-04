package com.example.aneeli.dice;

/**
 * Created by aneeli on 3/26/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class about extends Activity {
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

    public void MainActivity(View v){
        Intent startNewActivity = new Intent(this,MainActivity.class);
        //startActivity(new Intent(MyActivity.this, MainActivity.class));
        startActivity(startNewActivity);

    }
}