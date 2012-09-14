package com.pgreader.ui;

import com.pgreader.data.DataRepository;
import com.pgreader.data.UnitEntry;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Display the list of unit.
 */
public class UnitListFragment extends ListFragment {

	/**
	 * persist id for the mActivatedPosition field.
	 */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * current callback.
     */
    private Callbacks mCallbacks = sDummyCallbacks;
    
    /**
     * Position of the current selection is persisted through this member.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * define the call backs interface.
     */
    public interface Callbacks {
    	/**
    	 * called when a new item is selected.
    	 * @param id id of the selected item, null if there is no selection.
    	 */
        void onItemSelected(String id);
    }

    /**
     * Dummy call back when resetting the callback.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
		public void onItemSelected(String id) {
		}
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<UnitEntry>(getActivity(),
                R.layout.simple_list_item_activated_1,
                R.id.text1,
                DataRepository.getsUnits()));
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
        mCallbacks.onItemSelected(DataRepository.getsUnits().get(position).getName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * configure to active on click or not.
     * @param activateOnItemClick if true, a click will select activate an object
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) { 
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    /**
     * update the current item.
     * @param position item position. 
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
