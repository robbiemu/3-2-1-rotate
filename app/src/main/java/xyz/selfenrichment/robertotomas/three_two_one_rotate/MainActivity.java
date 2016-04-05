package xyz.selfenrichment.robertotomas.three_two_one_rotate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_detail) != null) { // you are in two-pane mode

            // In two-pane mode, show the detail fragment in this activity by adding or replacing
            // the fragmentcontainer in the xml-layout using a fragment transaction.
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentcontainer_details_fragment,
                                new DetailFragment(), DetailFragment.class.getSimpleName())
                        .commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        GridView gv = (GridView) findViewById(R.id.grid_view);
        if (gv != null) {
            outState.putInt(Grid_View.POSITION, gv.getScrollY());
        }
    }

    //Callback override
    @Override
    public void onItemSelected(int pos) {
        // we should load the detail fragment with this item's gridview text
    }
}
