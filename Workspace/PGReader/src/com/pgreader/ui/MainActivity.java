package com.pgreader.ui;

import com.pgreader.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


/**
 * our main activity.
 * @author GeoVah 
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button) findViewById(R.id.unitBrowser);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// start the activity for display the unit browser
            	Intent unitListActivity = new Intent(v.getContext(), UnitListActivity.class);
                startActivity(unitListActivity);
            }
        });
        
        button = (Button) findViewById(R.id.manageResources);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// start the activity for managing resources
            	Intent manageResourcesActivity = 
            			new Intent(v.getContext(), ResourceLoaderActivity.class);
                startActivity(manageResourcesActivity);
            }
        });
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
