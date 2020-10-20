package com.nikolam.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemPositionIndicatorView extends LinearLayout {


    private final LayoutParams INDICATOR_LAYOUT_PARAM = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
    private final LayoutParams SPACE_LAYOUT_PARAM = new LayoutParams(5, LayoutParams.WRAP_CONTENT);

    private final List<IndicatorView> indicators = new ArrayList<>();

    private int indicator_count = 0;

    private int current = 0;

    public ItemPositionIndicatorView(Context context) {
        super(context);
        init(null);
    }

    public ItemPositionIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ItemPositionIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }



    private void init(@Nullable AttributeSet attrs){
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ItemPositionIndicatorView,
                0, 0);


        try{
            indicator_count = a.getInteger(R.styleable.ItemPositionIndicatorView_indicator_count, 0);
        } finally {
            a.recycle();
        }


        this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        setClipChildren(false);
        setClipToPadding(false);

        setupViews();

        indicators.get(0).setIsCurrent();

    }

    public void next(){
        if(current== indicators.size()-1){
            return;
        }

        indicators.get(current).setIsNotCurrent();
        indicators.get(++current).setIsCurrent();

    }


    public void setupViews(){
        indicators.clear();
        removeAllViews();

        for(int i = 1; i <= indicator_count; i++){
            IndicatorView ind = createIndicator();
            indicators.add(ind);
            addView(ind);

            if(indicator_count > 1 && i != indicator_count){
                View v = createSpace();
                addView(v);
            }
        }

    }


    private IndicatorView createIndicator(){
        IndicatorView ind = new IndicatorView(getContext());
        ind.setLayoutParams(INDICATOR_LAYOUT_PARAM);
        return ind;
    }

    private View createSpace(){
        View v = new View(getContext());
        v.setLayoutParams(SPACE_LAYOUT_PARAM);
        return v;
    }



}
