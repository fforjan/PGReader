package com.pgreader.ui;

import com.pgreader.R;
import com.pgreader.data.DataRepository;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * displaying the list for terrain.
 */
public class TerrainListActivity extends FragmentActivity
        implements TerrainListFragment.Callbacks {

	/**
	 * true for two pane mode.
	 */
    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrain_list);

        if (findViewById(R.id.terrain_detail_container) != null) {
            mTwoPane = true;
            ((TerrainListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.terrain_list))
                    .setActivateOnItemClick(true);
        }
        
        if (DataRepository.getsUnits().size() == 0) {
	        AlertDialog alertDialog;
	        alertDialog = new AlertDialog.Builder(this).create();
	        alertDialog.setTitle(getString(R.string.cannotFindTerrains));
	        alertDialog.setMessage(getString(R.string.askForResourceSet));
	        alertDialog.show();
        }
    } 

    /**
     * call back when a item in selected in the list.
     * @param id terrain id
     */
    public void onTerrainSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(TerrainDetailFragment.ARG_TERRAIN_ID, id);
            TerrainDetailFragment fragment = new TerrainDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.terrain_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, TerrainDetailActivity.class);
            detailIntent.putExtra(TerrainDetailFragment.ARG_TERRAIN_ID, id);
            startActivity(detailIntent);
        }
    }
}
