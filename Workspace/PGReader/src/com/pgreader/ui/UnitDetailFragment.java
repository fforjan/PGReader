package com.pgreader.ui;

import java.io.IOException;
import java.io.InputStream;

import com.pgreader.R;
import com.pgreader.data.DataRepository;
import com.pgreader.data.IconResources;
import com.pgreader.data.UnitEntry;
import com.pgreader.legacy.ShpReader;
import com.pgreader.ui.view.PGShpSingleView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * fragment displaying the unit detail.
 */
public class UnitDetailFragment extends Fragment {

	/**
	 * default size for the PGShpSingleView for unit icon.
	 */
	private static final int ICONDEFAULTSIZE = 100;
	
	/** 
	 * Argument name when invoking the fragment defining current unit id.
	 */
    public static final String ARG_UNITID = "unitId";
    
    /**
     * the unit icons information from the SHP file.
     */
    private static IconResources sTacIcons;
    

    /**
     * current unit displayed.
     * This is set using the ARG_UNITID
     */
    private UnitEntry mItem;
    
    /**
     * our view for the unit icon.
     */
    private PGShpSingleView mUnitView;
    
    /**
     * if method return the current rootView.
     * If this object is existing, it will be created.
     * @param rootView view containing the main layout
     * @return the new view
     */
    private PGShpSingleView getUnitView(View rootView) {
    	if (sTacIcons == null) {
        	InputStream tacIconsStream = getResources().openRawResource(R.raw.tacicons);  
        	try {
				sTacIcons = ShpReader.load(tacIconsStream);
        	} catch (IOException e) {
        		System.err.println("Cannot load tacicons");
        	}
        }
        if (mUnitView == null) {
        	android.widget.LinearLayout layout =
        			((android.widget.LinearLayout) rootView.findViewById(R.id.MainUnitLayout));
        	mUnitView = new PGShpSingleView(layout.getContext(), sTacIcons);
        	layout.addView(mUnitView, ICONDEFAULTSIZE, ICONDEFAULTSIZE);	
        }
        return mUnitView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getArguments().containsKey(ARG_UNITID)) {
            mItem = DataRepository.getsUnitsMap().get(getArguments().getString(ARG_UNITID));
        }
        
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_unit_detail, container, false);
        
        if (mItem != null) {
            getTextView(rootView, R.id.unit_detail)
            	.setText(String.format("%s [%s]", mItem.getName(), mItem.getUnitClass()));

            getTextView(rootView, R.id.attackAir)
            	.setText(String.valueOf(mItem.getAtkAir()));
            getTextView(rootView, R.id.attackHard)
            	.setText(String.valueOf(mItem.getAtkHard()));
            getTextView(rootView, R.id.attackNaval)
            	.setText(String.valueOf(mItem.getAtkNaval()));
            getTextView(rootView, R.id.attackSoft)
            	.setText(String.valueOf(mItem.getAtkSoft()));

            getTextView(rootView, R.id.defAir)
            	.setText(String.valueOf(mItem.getDefAir()));
            getTextView(rootView, R.id.defClose)
            	.setText(String.valueOf(mItem.getDefClose()));
            getTextView(rootView, R.id.defGround)
            	.setText(String.valueOf(mItem.getDefGround()));
            getTextView(rootView, R.id.targetType)
            	.setText(String.valueOf(mItem.getTargetType()));  
            

            getTextView(rootView, R.id.nation)
        	.setText(String.valueOf(mItem.getNation()));
            getTextView(rootView, R.id.moveType)
        	.setText(String.valueOf(mItem.getMoveType()));  

            getUnitView(rootView).setIconIndex(mItem.getIconIndex());
            
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
