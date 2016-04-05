package xyz.selfenrichment.robertotomas.three_two_one_rotate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

/**
 * Created by RobertoTomás on 0004, 4, 4, 2016.
 */
public class DetailFragment extends Fragment {
    public static String DETAIL_TITLE = "title";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setupTextView() {
        TextView tv = (TextView) getActivity().findViewById(R.id.textView);
        tv.setText(DETAIL_TITLE);
    }
}
