package com.pgreader.ui;

import com.pgreader.data.DataRepository;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Terrain list fragment class.
 */
public class TerrainListFragment extends ListFragment {

	/**
	 * persisted property name.
	 */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * callback when terrain is selected.
     */
    private Callbacks mCallbacks = sDummyCallbacks;
    
    /**
     * current activated item.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * class back when an item is selected.
     */
    public interface Callbacks {

    	/**
    	 * called when a terrain is selected by the user in the list.
    	 * @param id terrain id
    	 */
        void onTerrainSelected(String id);
    }

    /**
     * default callback.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
       
        public void onTerrainSelected(String id) {
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<com.pgreader.data.map.Terrain>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DataRepository.getsTerrains()));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState
                .containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        mCallbacks.onTerrainSelected(DataRepository.getsTerrains().get(position).getId());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * set the current mode : selection on item click or not.
     * @param activateOnItemClick if true, the user is able to select an terrain.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    /**
     * set the activated position for the list.
     * @param position position 
     */
    public void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
}
