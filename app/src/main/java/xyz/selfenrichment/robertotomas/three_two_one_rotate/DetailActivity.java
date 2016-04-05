package xyz.selfenrichment.robertotomas.three_two_one_rotate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by RobertoTomás on 0004, 4, 4, 2016.
 */
public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(DetailFragment.DETAIL_TITLE, getIntent().getData());

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentcontainer_details_fragment, fragment)
                    .commit();
        }
    }
}
