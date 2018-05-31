package com.example.administrator.demolistapplication.demomoduls.quanpin_adjustresize.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.demolistapplication.R;
import com.example.administrator.demolistapplication.demomoduls.quanpin_adjustresize.QP_ResizeTestActivity;
import com.example.administrator.demolistapplication.demomoduls.quanpin_adjustresize.utils.SoftKeyBoardListener;
import com.example.administrator.demolistapplication.demomoduls.quanpin_adjustresize.utils.StatusBarUtils;
import com.example.administrator.demolistapplication.tools.ScreenUtils;

/**
 * Created by Administrator on 2018/2/1.
 */

public class Test1Fragment extends Fragment {


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate( R.layout.activity_qp__resize_test_fragment ,container , false );
            SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
                @Override
                public void keyBoardShow(int height) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.height = ScreenUtils.getScreenHeight(getActivity() ) - height ;
                    view.setLayoutParams(layoutParams);

//                    Log.e("mylog" , layout.getTop() + "/"  );
                }

                @Override
                public void keyBoardHide(int height) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.height = ScreenUtils.getScreenHeight(getActivity() );
                    view.setLayoutParams(layoutParams);
//                    Log.e("mylog" , layout.getTop() + "/"  );
                }
            });
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        WindowManager.LayoutParams attr = getActivity().getWindow().getAttributes();
        attr.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getActivity().getWindow().setAttributes(attr);
    }



}
