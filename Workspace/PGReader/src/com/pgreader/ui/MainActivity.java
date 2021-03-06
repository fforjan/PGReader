package com.pgreader.ui;

import com.pgreader.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * our main activity.
 * @author GeoVah 
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        View button = (View) findViewById(R.id.unitBrowser);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// start the activity for display the unit browser
            	Intent unitListActivity = new Intent(v.getContext(), UnitListActivity.class);
                startActivity(unitListActivity);
            }
        });
        
        button = (View) findViewById(R.id.manageResources);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// start the activity for managing resources
            	Intent manageResourcesActivity = 
            			new Intent(v.getContext(), ResourceLoaderActivity.class);
                startActivity(manageResourcesActivity);
            }
        });
        
        button = (View) findViewById(R.id.terrainBrowser);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// start the activity for managing resources
            	Intent browseTerrainActivity = 
            			new Intent(v.getContext(), TerrainListActivity.class);
                startActivity(browseTerrainActivity);
            }
        });
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
