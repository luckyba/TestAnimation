package com.luckyba.testanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;

public class TranslationX {

    public static void translation(final View view, final boolean b, int distance) {
        ValueAnimator animator = null;
        if (b) {
            animator = ValueAnimator.ofFloat(0, distance);
        } else  {
            animator = ValueAnimator.ofFloat(distance, 0);
        }
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                if (!b ){
//                    view.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                if (b) {
                    view.setVisibility(View.VISIBLE);
                }
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

        animator.setTarget(view);
        animator.setDuration(500).start();
        animator.addUpdateListener(updatedAnimation -> {
            float value = (float) updatedAnimation.getAnimatedValue();
            view.setTranslationX(value);
        });
    }
}
