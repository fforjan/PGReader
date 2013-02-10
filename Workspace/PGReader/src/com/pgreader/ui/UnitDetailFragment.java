package com.pgreader.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pgreader.R;
import com.pgreader.data.DataRepository;
import com.pgreader.data.SoundManager;
import com.pgreader.data.UnitEntry;
import com.pgreader.ui.view.IconsResourceSingleView;

/**
 * fragment displaying the unit detail.
 */
public class UnitDetailFragment extends Fragment {
	
	/** 
	 * Argument name when invoking the fragment defining current unit id.
	 */
    public static final String ARG_UNITID = "unitId";
    
    /**
     * current unit displayed.
     * This is set using the ARG_UNITID
     */
    private UnitEntry mItem;
    
    /**
     * if method return the view of type IconsResourceSingleView.
     * @param rootView view containing the main layout
     * @param id view id
     * @return the new view
     */
    private IconsResourceSingleView getIconView(View rootView, int id) {
    		         
        return ((IconsResourceSingleView) rootView.findViewById(id));
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
        	initIconViews(rootView);
        	
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

            getIconView(rootView, R.id.unitIcon).setIconIndex(mItem.getIconIndex());
            getIconView(rootView, R.id.unitFlag).setIconIndex(mItem.getNation().ordinal());
            
        }
        return rootView;
    }

	/**
	 * @param rootView the root view
	 */
	private void initIconViews(View rootView) {
		getIconView(rootView, R.id.unitIcon).setIconResources(DataRepository.getsTacIcons()); 
		getIconView(rootView, R.id.unitFlag).setIconResources(DataRepository.getFlags()); 
		
		
		getIconView(rootView, R.id.unitIcon).setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	SoundManager.playSound(mItem.getMoveType(), v.getContext());
		    }
		});
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
