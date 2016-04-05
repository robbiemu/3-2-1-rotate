package xyz.selfenrichment.robertotomas.three_two_one_rotate;
// Created by RobertoTomás on 0004, 4, 4, 2016.

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * The detail fragment contains a textview to show the single item selected of lib/Data data.
 * The data itself is actually passed through the activities and fragments, rather than calling the
 * data source again (although in this case, it would be a trivial performance hit).
 */
public class DetailFragment extends Fragment {
    private String TITLE;
    private int POSITION;
    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        mTextView = (TextView) rootView.findViewById(R.id.textView);

        Bundle arguments = getArguments();
        if (arguments != null) {
            TITLE = arguments.getString(getString(R.string.key_view_item_title));
            POSITION = arguments.getInt(getString(R.string.key_grid_view_position),0);
        } else {
            Log.e(DetailFragment.class.getSimpleName(), " in onCreateView: Called " +
                    "without arguments");
            return rootView;
        }

        mTextView.setText(TITLE);
        mTextView.setTag(R.id.tag_grid_view_item_position, POSITION);

        return rootView;
    }
}
