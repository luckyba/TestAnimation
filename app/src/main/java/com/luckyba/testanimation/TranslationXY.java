package com.luckyba.testanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

public class TranslationXY {
    static void translate (final View view, final boolean status, int x, int y, int duration) {
        ObjectAnimator animX = null;
        ObjectAnimator animY = null;
        if (status) {
            animX = ObjectAnimator.ofFloat(view, "x", x);
            animY = ObjectAnimator.ofFloat(view, "y", y);
        } else {
            animX = ObjectAnimator.ofFloat(view, "x", -x);
            animY = ObjectAnimator.ofFloat(view, "y", -y);
        }

        animX.setDuration(duration);
        animY.setDuration(duration);

        animX.addUpdateListener(updatedAnimation -> {
            float value = (float) updatedAnimation.getAnimatedValue();
            Log.d("translate_", "animX "+ value);

            view.setTranslationX(value);
        });

        animY.addUpdateListener(updatedAnimation -> {
            float value = (float) updatedAnimation.getAnimatedValue();
            Log.d("translate_", "animY "+ value);

            view.setTranslationY(value);
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animX, animY);
        animatorSet.start();
    }
}
