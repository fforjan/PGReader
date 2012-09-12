package com.pgreader;

import com.panzergeneral.UnitEntry;
import com.pgreader.dummy.DummyContent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UnitDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    UnitEntry mItem;

    public UnitDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ItemsMap.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_unit_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.unit_detail)).setText(
                	String.format("%s [%s]", mItem.Name, mItem.Class));

            ((TextView) rootView.findViewById(R.id.attackAir))
            	.setText(String.valueOf(mItem.atk_air));
            ((TextView) rootView.findViewById(R.id.attackHard))
            	.setText(String.valueOf(mItem.atk_hard));
            ((TextView) rootView.findViewById(R.id.attackNaval))
            	.setText(String.valueOf(mItem.atk_naval));
            ((TextView) rootView.findViewById(R.id.attackSoft))
            	.setText(String.valueOf(mItem.atk_soft));

            ((TextView) rootView.findViewById(R.id.defAir))
            	.setText(String.valueOf(mItem.def_air));
            ((TextView) rootView.findViewById(R.id.defClose))
            	.setText(String.valueOf(mItem.def_close));
            ((TextView) rootView.findViewById(R.id.defGround))
            	.setText(String.valueOf(mItem.def_ground));
            ((TextView) rootView.findViewById(R.id.targetType))
            	.setText(String.valueOf(mItem.TargetType));    
        }
        return rootView;
    }
}
