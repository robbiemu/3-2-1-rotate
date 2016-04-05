package xyz.selfenrichment.robertotomas.three_two_one_rotate;
//Created by RobertoTomás on 0004, 4, 4, 2016.

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import xyz.selfenrichment.robertotomas.three_two_one_rotate.lib.Data;

/**
 * Loads the Main activity GridView as a Fragment
 */
public class MainFragment extends Fragment {
    private GridView mGridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // the following is necessary if we want a menu, since the activity just has this fragment,
        // not even an outer layout
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Integer pos = null;
        if (savedInstanceState != null){
            pos = savedInstanceState.getInt(Grid_View.POSITION);
        }

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mGridView = (GridView) rootView.findViewById(R.id.fragment_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, Data.TITLES);
        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ((Callback) getActivity())
                        .onItemSelected(position);
            }
        });

        if(pos != null) {
            mGridView.scrollTo(mGridView.getScrollX(), pos);
        }

        return rootView;
    }

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        void onItemSelected(int pos);
    }
}
