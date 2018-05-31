package com.example.administrator.demolistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.demolistapplication.demomoduls.quanpin_adjustresize.QP_ResizeTestActivity;
import com.example.administrator.demolistapplication.demomoduls.rxjava_study.RxjavaBaseActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private android.widget.ListView mainlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mainlistview = (ListView) findViewById(R.id.main_listview);

        onInit();

    }


    private void onInit(){

        final ArrayList<String> listTitle = new ArrayList<>();
        listTitle.add( "01 全屏与adjustResize冲突问题处理" );
        listTitle.add( "02 rxjava2 使用" );


        final ArrayList<Class> listClass = new ArrayList<>();
        listClass.add(  QP_ResizeTestActivity.class);
        listClass.add(  RxjavaBaseActivity.class);

        ArrayAdapter simpleAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , listTitle );
        mainlistview.setAdapter(simpleAdapter);
        mainlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class classes = listClass.get(position);
                startActivity(new Intent(MainActivity.this , classes ));
            }
        });

    }



}
