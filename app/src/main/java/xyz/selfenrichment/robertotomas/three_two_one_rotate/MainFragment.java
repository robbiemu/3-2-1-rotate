package xyz.selfenrichment.robertotomas.three_two_one_rotate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import xyz.selfenrichment.robertotomas.three_two_one_rotate.lib.Data;

/**
 * Created by RobertoTomás on 0004, 4, 4, 2016.
 */
public class MainFragment extends Fragment {
    GridView gridViewSample;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // the following is necessary if we want a menu, since the activity just has this fragment,
        // not even an outer layout
        setHasOptionsMenu(true);
    }

    public void setupGridView(){
        gridViewSample = (GridView) getActivity().findViewById(R.id.fragment_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, Data.TITLES);
        gridViewSample.setAdapter(adapter);

        gridViewSample.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ((Callback) getActivity())
                        .onItemSelected(position);
            }
        });
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
        public void onItemSelected(int pos);
    }
}
