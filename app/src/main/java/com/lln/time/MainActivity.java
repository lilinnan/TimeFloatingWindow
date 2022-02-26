package com.lln.time;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Switch;

public class MainActivity extends Activity {
    private Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwitch = findViewById(R.id.switchTime);
        mSwitch.setOnCheckedChangeListener((compoundButton, b) ->
                TimeFloatingWindowUtils.setTimeFloatingWindowIsOn(MainActivity.this, b));
    }


    private void updateSwitch() {
        mSwitch.setChecked(TimeFloatingWindowUtils.isTimeFloatingWindowOn(this));
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateSwitch();
        TimeFloatingWindowUtils.registerSettingsChangedUpdateListener(this,
                this::updateSwitch);
    }

    @Override
    protected void onPause() {
        super.onPause();
        TimeFloatingWindowUtils.unRegisterSettingsChangedUpdateListener(this);
    }
}
