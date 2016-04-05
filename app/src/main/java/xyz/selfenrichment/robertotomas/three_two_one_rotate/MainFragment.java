package xyz.selfenrichment.robertotomas.three_two_one_rotate;
//Created by RobertoTomás on 0004, 4, 4, 2016.

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import xyz.selfenrichment.robertotomas.three_two_one_rotate.lib.Data;
import xyz.selfenrichment.robertotomas.three_two_one_rotate.lib.LayoutUtil;

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
        if (savedInstanceState == null) {
            Intent intent = getActivity().getIntent();
            if(intent != null) {
                pos = intent.getIntExtra(getString(R.string.key_grid_view_position), 0);
            }
        }

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mGridView = (GridView) rootView.findViewById(R.id.grid_view);
        mGridView.setNumColumns(getColumns(mGridView));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, Data.TITLES);
        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ((Callback) getActivity())
                        .onItemSelected(v, position);
            }
        });

        if(pos != null) {
            mGridView.scrollTo(mGridView.getScrollX(), pos);
        }

        return rootView;
    }

    private int getColumns(GridView mGridView) {
        int cols = 2;
        if(LayoutUtil.isTablet(getContext())){
            cols = 3;
        }
        if(LayoutUtil.twoPanes(getContext())){
            cols = 1;
        }
        return cols;
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
        void onItemSelected(View view, int pos);
    }
}
