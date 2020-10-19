package com.nikolam.library;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class IndicatorView extends LinearLayout {

    private View indicatorNotSelected;
    private View indicatorSelected;

    private ScaleAnimation scaleUpAnimation;
    private ScaleAnimation scaleDownAnimation;
    private AlphaAnimation alphaAnimation;

    private final int DEFAULT_ANIMATION_DURATION = 2000;

    private boolean isSelected = false;

    public IndicatorView(Context context) {
        super(context);
        init();
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        View view = inflate(getContext(), R.layout.indicator_item, this);
        indicatorNotSelected = view.findViewById(R.id.indicator_notSelected);
        indicatorSelected = view.findViewById(R.id.indicator_selected);

        setupAnimations();
    }

    private void setupAnimations(){
        scaleUpAnimation = new ScaleAnimation(1f, 1.5f, 1f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleUpAnimation.setDuration(250);
        scaleUpAnimation.setInterpolator(new LinearInterpolator());
    //    scaleUpAnimation.setFillAfter(true);
        scaleUpAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                indicatorNotSelected.setVisibility(View.INVISIBLE);
                indicatorSelected.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scaleDownAnimation = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleDownAnimation.setDuration(250);
        scaleDownAnimation.setInterpolator(new LinearInterpolator());
      //  scaleDownAnimation.setFillAfter(true);
        scaleDownAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                indicatorNotSelected.setVisibility(View.VISIBLE);
                indicatorSelected.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        alphaAnimation = new AlphaAnimation(indicatorSelected.getAlpha(), indicatorNotSelected.getAlpha());
        alphaAnimation.setDuration(250);
        alphaAnimation.setInterpolator(new LinearInterpolator());
    }

    public void setIsCurrent() {
        if(isSelected) return;
        isSelected = true;
        indicatorNotSelected.startAnimation(scaleUpAnimation);
    }

    public void setIsNotCurrent(){
        if(!isSelected) return;
        isSelected = false;
        indicatorSelected.startAnimation(alphaAnimation);
        indicatorSelected.startAnimation(scaleDownAnimation);
    }

}
