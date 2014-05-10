package com.laimiux.sample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.laimiux.drawable.GenericPopoverDrawable;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int backgroundColor = getResources().getColor(R.color.popover_background);
        int borderColor = getResources().getColor(R.color.popover_border);

        GenericPopoverDrawable popoverDrawable = new GenericPopoverDrawable(backgroundColor, borderColor);
        findViewById(R.id.popover_button).setBackground(popoverDrawable);
    }
}
