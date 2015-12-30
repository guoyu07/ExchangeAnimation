package com.example.exchangeanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends Activity {


    private EditText tv_city_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_city_name = (EditText) findViewById(R.id.tv_city_name);
    }

    public void onChange(View view) {
        Intent intent = new Intent();
        intent.putExtra("city", tv_city_name.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

}
