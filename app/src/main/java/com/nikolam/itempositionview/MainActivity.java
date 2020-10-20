package com.nikolam.itempositionview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.nikolam.library.ItemPositionIndicatorView;
import com.nikolam.itempositionview.R;

public class MainActivity extends AppCompatActivity {

    private ItemPositionIndicatorView indicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indicators = findViewById(R.id.indicator);
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicators.next();
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();

       // indicators.setNumberOfIndicators(5);
    }
}