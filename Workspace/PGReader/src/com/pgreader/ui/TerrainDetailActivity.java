package com.pgreader.ui;

import com.pgreader.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * Activity for displaying detail about a terrain.
 */
public class TerrainDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_terrain_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(TerrainDetailFragment.ARG_TERRAIN_ID,
                    getIntent().getStringExtra(TerrainDetailFragment.ARG_TERRAIN_ID));
            TerrainDetailFragment fragment = new TerrainDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.terrain_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, TerrainListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
