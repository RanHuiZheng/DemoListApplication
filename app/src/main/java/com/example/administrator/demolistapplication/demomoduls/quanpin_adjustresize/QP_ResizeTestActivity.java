package com.example.administrator.demolistapplication.demomoduls.quanpin_adjustresize;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.demolistapplication.R;
import com.example.administrator.demolistapplication.demomoduls.quanpin_adjustresize.fragment.Test1Fragment;
import com.example.administrator.demolistapplication.demomoduls.quanpin_adjustresize.utils.AndroidBug5497Workaround;
import com.example.administrator.demolistapplication.demomoduls.quanpin_adjustresize.utils.SoftKeyBoardListener;
import com.example.administrator.demolistapplication.demomoduls.quanpin_adjustresize.utils.StatusBarUtils;
import com.example.administrator.demolistapplication.tools.ScreenUtils;

public class QP_ResizeTestActivity extends AppCompatActivity {

    private android.widget.RelativeLayout layout;
    private android.widget.LinearLayout mianview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtils.setWindowStatusTouming(this);//沉浸式状态栏
        setContentView(R.layout.activity_qp__resize_test);
//        AndroidBug5497Workaround.assistActivity(this);

        this.mianview = (LinearLayout) findViewById(R.id.mianview);
        this.layout = (RelativeLayout) findViewById(R.id.layout);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.layout , new Test1Fragment());
        fragmentTransaction.commitAllowingStateLoss();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
