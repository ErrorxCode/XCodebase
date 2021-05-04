package com.xcode.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnXCodebaseResponse response =  new OnXCodebaseResponse(){
            @Override
            public void onSuccessful(@Nullable Object object) {
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(XCodeException e) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        };
    }
}