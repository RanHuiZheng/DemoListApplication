package com.example.administrator.demolistapplication.demomoduls.createFileDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.demolistapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class CreateJavaFileActivity extends AppCompatActivity {

    private TextView activitycreatejavafiletv;
//    private MyProcessor myProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_java_file);
        activitycreatejavafiletv = (TextView) findViewById(R.id.activity_create_java_file_tv);

        onOnCtreateJavaFile(getContent());

    }

    private String getContent(){
        try {
            InputStream is = getResources().openRawResource(R.raw.kes);
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String str = "";
            String s;
            while((s = br.readLine()) != null){
                str += s ;
            }
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "{}";
    }

    private void onOnCtreateJavaFile(String data) {

        try {
            JSONObject object = new JSONObject(data);
            Iterator<String> keys = object.keys();
            StringBuffer stringBuffer = new StringBuffer();

            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject entity = object.getJSONObject(key);
                stringBuffer.append("/**\n ");
                stringBuffer.append("* " + entity.getString("remark") + "\n");
                stringBuffer.append("*/\n");
                if ("投资有道会员版-早间财经".equals(key)){
                    stringBuffer.append("String " + "Investment_channel_member_morning_Finance" + " = " + "\"" + key + "\";\n");
                }else if ("投资有道会员版-午间财经".equals(key)){
                    stringBuffer.append("String " + "Investment_path_member_midday_Finance" + " = " + "\"" + key + "\";\n");
                }else if ("盘口摩斯密码(林炜独创)".equals(key)){
                    stringBuffer.append("String " + "Moss_cipher_of_pan_mouth_original_Lin_Wei" + " = " + "\"" + key + "\";\n");
                }else if ("300".equals(key)){
                    stringBuffer.append("String " + "hangQing" + " = " + "\"" + key + "\";\n");
                }else {
                    stringBuffer.append("String " + key + " = " + "\"" + key + "\";\n");
                }

            }
            Log.e("LogTest", stringBuffer.toString());

            activitycreatejavafiletv.setText(stringBuffer.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
