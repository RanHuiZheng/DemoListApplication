package com.example.administrator.demolistapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.demolistapplication.demomoduls.createFileDemo.CreateJavaFileActivity;
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

        onGetQuanxian();
        onInit();

    }


    private void onInit(){

        final ArrayList<String> listTitle = new ArrayList<>();
        listTitle.add( "01 全屏与adjustResize冲突问题处理" );
        listTitle.add( "02 rxjava2 使用" );
        listTitle.add( "03 创建 文件" );


        final ArrayList<Class> listClass = new ArrayList<>();
        listClass.add(  QP_ResizeTestActivity.class);
        listClass.add(  RxjavaBaseActivity.class);
        listClass.add(  CreateJavaFileActivity.class);

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


    /**
     * 6.0  系统 检查运行权限
     */
    private void onGetQuanxian() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE}, 99);
        } else {
            onCallRun();
        }
    }

    private void onCallRun() {
        //初始化数据库

    }
}
