package com.example.exchangeanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private RelativeLayout rl_content;

    private TextView tv1;

    private TextView tv2;

    private ObjectAnimator animator1;

    private ObjectAnimator animator2;

    private Context mContext;

    private int count = 1;

    boolean isFirst = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mContext = MainActivity.this;

        rl_content = (RelativeLayout) findViewById(R.id.rl_content);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirst = false;
                Log.e("tv1点击。。。。。", "onClick ");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);

                startActivityForResult(intent, 101);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tv2点击。。。。。", "onClick ");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);

                startActivityForResult(intent, 102);
            }
        });
    }

    public void switchOnclick(View view) {
        //获取父控件的宽度
        final int fatherW = rl_content.getWidth();
        final float fatherX = rl_content.getX();

        final int tv1W = tv1.getWidth();
        final int tv2W = tv2.getWidth();

        final float tv1X = tv1.getX();
        final float tv2X = tv2.getX();

        //拿到当前的tv1和tv2的值
        final String str1 = tv1.getText().toString();
        final String str2 = tv2.getText().toString();

        final float tv1FromX;
        final float tv1ToX;
        final float tv2FromX;
        final float tv2ToX;

        //v1的第一次移动范围为:从初始位置右移动到v2所在的位置
        tv1FromX = 0;
        tv1ToX = fatherW - tv1W;

        //v2的第一次移动范围为:从初始位置(0)移动到v1所在的位置,这是不能用v1的坐标,而是用v2的坐标的负数(-v2)
        //相对它自动的初始位置是:0
        tv2FromX = 0;
        tv2ToX = -(fatherW - tv2W);

        float[] values3 = {tv1FromX, tv1ToX};
        float[] values4 = {tv2FromX, tv2ToX};
        animator1 = ObjectAnimator.ofFloat(tv1, "translationX", values3);
        animator1.setDuration(400);
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                tv1.setX(0);
                tv1.setText(str2);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator1.start();

        animator2 = ObjectAnimator.ofFloat(tv2, "translationX", values4);
        animator2.setDuration(400);
        animator2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                tv2.setX(fatherW - tv2W);
                tv2.setText(str1);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator2.start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                tv1.setText(data.getStringExtra("city"));
            } else if (requestCode == 102) {
                tv2.setText(data.getStringExtra("city"));
            }
        }
    }


    public void changeDate(View view) {
        //获取控件的文字
        String str = ((Button) view).getText().toString();

        tv1.setText(str);
    }

}
