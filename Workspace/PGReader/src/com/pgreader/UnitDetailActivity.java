package com.pgreader;

import com.panzergeneral.DataRepository;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * the activity for displaying unit detail.
 * @author FREDERIC FORJAN <GeoVah>
 */
public class UnitDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        DataRepository.Load(getResources());
        
        setContentView(R.layout.activity_unit_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(UnitDetailFragment.ARG_UNITID,
                    getIntent().getStringExtra(UnitDetailFragment.ARG_UNITID));
            UnitDetailFragment fragment = new UnitDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.unit_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, UnitListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
