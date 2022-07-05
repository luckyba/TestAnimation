package com.luckyba.testanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestAnimationActivity extends AppCompatActivity {
    private static String TAG = "TestAnimationActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_animation_activity);
        Button button = findViewById(R.id.bnt_anim);
        Button button1 = findViewById(R.id.bnt_anim_1);

        ObjectAnimator animator = ObjectAnimator.ofFloat(button1, "rotation", 0, 360);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animator.start();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
//        animator.addUpdateListener(updated -> {
//            button1.setTranslationX((float) updated.getAnimatedValue());
//        });

        button1.setOnClickListener(v -> {
            animator.start();

        });
        AtomicBoolean check = new AtomicBoolean(false);
        button.setOnClickListener(v -> {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            Log.i(TAG, "onCreate: height "+ height + " width "+ width);
            TranslationXY.translate(button, true, width/2, height/2, 1000);
            int i = 0;
            int j = 0;

//            for ( i = 0; i < width; ) {
//                TranslationXY.translate(button, check.get(), i, j, 1000 );
//                i = i + 10;
//            }
//            for ( j = 0; j < height; j++) {
//                TranslationXY.translate(button, check.get(), i, j, 1000 );
//                j = j + 10;
//            }
//            for ( ; i >= 0; ) {
//                TranslationXY.translate(button, check.get(), i, j, 1000 );
//                i = i -10;
//            }
//            for ( ; j >= 0; ) {
//                TranslationXY.translate(button, check.get(), i, j, 1000 );
//                j = j -10;
//            }
        });

    }

    void runAnimation(View view, Animation animation) {
        synchronized (view) {
            view.startAnimation(animation);
        }
    }
}
