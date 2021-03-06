package xyz.selfenrichment.robertotomas.three_two_one_rotate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import xyz.selfenrichment.robertotomas.three_two_one_rotate.lib.LayoutUtil;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (LayoutUtil.twoPanes(this)) {
            Intent intent = getIntent();
            if (intent != null) { // if we came here by intent it is from the detail activity
                int i = intent.getIntExtra( getString(R.string.key_grid_view_position), 0);
                String s = intent.getStringExtra( getString(R.string.key_view_item_title) );
                loadDetailInTwoPane(i, s);
            } else if (savedInstanceState == null) {
                // In two-pane mode, show the detail fragment in this activity by adding or replacing
                // the fragmentcontainer in the xml-layout using a fragment transaction.
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentcontainer_detail,
                                new Fragment(), DetailFragment.class.getSimpleName())
                        .commit();
            }
        }

        /* the id in the <fragment> is to configure an un-instantiated fragment by id, as from the
           main activity's onCreate with:
        SomeFragment someFragment = ((SomeFragment)getSupportFragmentManager()
                .findFragmentById(R.id.this_fragment_id));
        someFragment.prepareSomeStateForRender(someStateValue);
        */
    }

    private void loadDetailInTwoPane(int pos, String title) {
        Bundle args = new Bundle();
        args.putString(getString(R.string.key_view_item_title), title);
        args.putInt(getString(R.string.key_grid_view_position), pos);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentcontainer_detail, fragment,
                        DetailFragment.class.getSimpleName())
                .commit();
    }

    /**
     * MainFragment Callback override - handles click events on gridview items
     * @param v - the calling view containing the data item
     * @param pos - the position of the view from the grid_view
     */
    @Override
    public void onItemSelected(View v, int pos) {
        String selectedText = (String) ((TextView) v).getText();
        // We are splitting the logic here because we don't just need to load the detail fragment
        // with the data. We also need to completely switch activities if it is single-pane
        if (LayoutUtil.twoPanes(this)) {
            loadDetailInTwoPane(pos, selectedText);
        } else {
            Intent intent = new Intent(this, DetailActivity.class)
                    .putExtra(getString(R.string.key_view_item_title), selectedText)
                    .putExtra(getString(R.string.key_grid_view_position), pos);
            startActivity(intent);
        }
    }
}
