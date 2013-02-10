package com.pgreader.ui;

import com.pgreader.R;
import com.pgreader.data.DataRepository;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment information about a terrain.
 */
public class TerrainDetailFragment extends Fragment {

	/**
	 * argument name for terrain.
	 */
    public static final String ARG_TERRAIN_ID = "terrain_id";

    /**
     *  item used when displayed.
     */
    private com.pgreader.data.map.Terrain mItem;

    /**
     * default constructor.
     */
    public TerrainDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if(getArguments().containsKey(ARG_TERRAIN_ID)) {
        	
            mItem = DataRepository.getsTerrainsMap().get(getArguments().getString(ARG_TERRAIN_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_terrain_detail, container, false);
        
        if (mItem != null) {
        	getTextView(rootView, R.id.terrainName).setText(mItem.getTerrainName());
        } 
        
        return rootView;
    }

	
	/**
	 * @param rootView the root view
	 * @param id view id
	 * @return the text view using the id
	 */
	private TextView getTextView(View rootView, int id) {
		return (TextView) rootView.findViewById(id);
	}
}
