package com.example.administrator.demolistapplication.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.demolistapplication.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
