package com.pgreader.ui;

import com.pgreader.R;
import com.pgreader.legacy.LegacyReader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v4.app.NavUtils;

/**
 * select the resources to be displayed.
 * @author GeoVah
 *
 */
public class ResourceLoaderActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_loader);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        ImageButton button = (ImageButton) findViewById(R.id.pgButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	LegacyReader.loadData(v.getContext());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_resource_loader, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
			default:
				break;
        }
        return super.onOptionsItemSelected(item);
    }

}
