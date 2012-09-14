package com.pgreader.ui;

import com.pgreader.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


/**
 * Unit list activity.
 */
public class UnitListActivity extends FragmentActivity
        implements UnitListFragment.Callbacks {

	/**
	 * true if we are in a two mode pane else false.
	 */
    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_unit_list);

        if (findViewById(R.id.unit_detail_container) != null) {
            mTwoPane = true;
            ((UnitListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.unit_list))
                    .setActivateOnItemClick(true);
        }
    }

    /**
     * call the selected item is changed.
     * @see UnitListFragment.Callbacks interface
     * @param id id of the selected it
     */
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(UnitDetailFragment.ARG_UNITID, id);
            UnitDetailFragment fragment = new UnitDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.unit_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, UnitDetailActivity.class);
            detailIntent.putExtra(UnitDetailFragment.ARG_UNITID, id);
            startActivity(detailIntent);
        }
    }
}
