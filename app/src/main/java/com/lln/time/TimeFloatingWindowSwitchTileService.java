package com.lln.time;

import static android.service.quicksettings.Tile.STATE_ACTIVE;
import static android.service.quicksettings.Tile.STATE_INACTIVE;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

@TargetApi(Build.VERSION_CODES.N)
public class TimeFloatingWindowSwitchTileService extends TileService {

    @Override
    public void onStartListening() {
        updateStatus();
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        getQsTile().setState(Tile.STATE_UNAVAILABLE);
        getQsTile().updateTile();
    }

    @Override
    public void onClick() {
        super.onClick();
        Context context = getApplicationContext();
        boolean timeFloatingWindowOn = TimeFloatingWindowUtils.isTimeFloatingWindowOn(context);
        TimeFloatingWindowUtils.setTimeFloatingWindowIsOn(context, !timeFloatingWindowOn);
        updateStatus();
    }

    private void updateStatus() {
        boolean timeFloatingWindowOn = TimeFloatingWindowUtils
                .isTimeFloatingWindowOn(getApplicationContext());
        getQsTile().setState(timeFloatingWindowOn ? STATE_ACTIVE : STATE_INACTIVE);
        getQsTile().updateTile();
    }

}