package com.atguigu.yutianzhu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {
    private ImageView iv_welcome;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        iv_welcome = (ImageView)findViewById(R.id.iv_welcome);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(1000);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(1000);
        AnimationSet animationSet = new AnimationSet(false);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        iv_welcome.startAnimation(animationSet);




        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        },3000);

    }

    private void startMainActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            startMainActivity();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
